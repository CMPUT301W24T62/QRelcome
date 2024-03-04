package com.example.qrelcome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.core.app.ActivityCompat;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class QRCodeScanner extends AppCompatActivity implements View.OnClickListener {
    Button scanButton;
    private static final String TAG = "MLKit Barcode";
    private static final int PERMISSION_CODE = 1001;
    private static final String CAMERA_PERMISSION = Manifest.permission.CAMERA;
    private PreviewView previewView;
    private CameraSelector cameraSelector;
    private ProcessCameraProvider cameraProvider;
    private Preview previewUseCase;
    private ImageAnalysis analysisUseCase;

    /**
     * This sets the <code>ContentView</code>, <code>previewView</code> and
     * an <code>onClickListener</code> for the scan button when the activity is created
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);
        scanButton = findViewById(R.id.button_scan);
        scanButton.setOnClickListener(this);
        previewView = findViewById(R.id.preview_view);
    }

    /**
     * This calls <code>startCamera</code> when an activity enters
     * the resumed state and starts interacting with the user
     */
    @Override
    protected void onResume() {
        super.onResume();
        startCamera();
    }

    /**
     * This calls <code>setupCamera</code> if the user has granted permission to access camera
     * else it calls <code>getPermissions</code> to get permission from the user to access camera
     */
    public void startCamera() {
        if(ContextCompat.checkSelfPermission(this, CAMERA_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
            setupCamera();
        } else {
            getPermissions();
        }
    }

    /**
     * This gets permissions from the user to access camera
     */
    private void getPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA_PERMISSION}, PERMISSION_CODE);
    }

    /**
     * Loops through each <code>grantResults</code> and if any of the permissions are not granted,
     * it displays a toast to notify the user that the permissions were not granted
     * If all permissions are granted it calls <code>setupCamera</code>
     * @param requestCode The request code passed in <code>requestPermissions</code>
     * @param permissions A list of the requested permissions
     * @param grantResults A list of The grant results for the corresponding permissions
     *                     which is either <code>PERMISSION_GRANTED</code> or <code>PERMISSION_DENIED</code>
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, int[] grantResults) {
        for (int r : grantResults) {
            if (r == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        if (requestCode == PERMISSION_CODE) {
            setupCamera();
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * This sets a <code>ListenableFuture</code> listener,
     * selects a required camera lens and
     * binds all camera use cases
     */
    private void setupCamera() {
        final ListenableFuture<ProcessCameraProvider> cameraProviderFuture =
                ProcessCameraProvider.getInstance(this);

        int lensFacing = CameraSelector.LENS_FACING_BACK;
        cameraSelector = new CameraSelector.Builder().requireLensFacing(lensFacing).build();

        cameraProviderFuture.addListener(() -> {
            try {
                cameraProvider = cameraProviderFuture.get();
                bindAllCameraUseCases();
            } catch (ExecutionException | InterruptedException e) {
                Log.e(TAG, "cameraProviderFuture.addListener Error", e);
            }
        }, ContextCompat.getMainExecutor(this));
    }

    /**
     * This unbinds all use cases before rebinding
     * Calls <code>bindPreviewUseCase</code> and <code>bindAnalysisUseCase</code>
     */
    private void bindAllCameraUseCases() {
        if (cameraProvider != null) {
            cameraProvider.unbindAll();
            bindPreviewUseCase();
            bindAnalysisUseCase();
        }
    }

    /**
     * This binds the preview use case to the camera
     */
    private void bindPreviewUseCase() {
        if (cameraProvider == null) {
            return;
        }

        if (previewUseCase != null) {
            cameraProvider.unbind(previewUseCase);
        }

        Preview.Builder builder = new Preview.Builder();
        builder.setTargetRotation(getRotation());

        previewUseCase = builder.build();
        previewUseCase.setSurfaceProvider(previewView.getSurfaceProvider());

        try {
            cameraProvider
                    .bindToLifecycle(this, cameraSelector, previewUseCase);
        } catch (Exception e) {
            Log.e(TAG, "Error when bind preview", e);
        }
    }

    /**
     * This connects the analyzer to the camera
     */
    private void bindAnalysisUseCase() {
        if (cameraProvider == null) {
            return;
        }

        if (analysisUseCase != null) {
            cameraProvider.unbind(analysisUseCase);
        }

        Executor cameraExecutor = Executors.newSingleThreadExecutor();

        ImageAnalysis.Builder builder = new ImageAnalysis.Builder();
        builder.setTargetRotation(getRotation());

        analysisUseCase = builder.build();
        analysisUseCase.setAnalyzer(cameraExecutor, this::analyze);

        try {
            cameraProvider
                    .bindToLifecycle(this, cameraSelector, analysisUseCase);
        } catch (Exception e) {
            Log.e(TAG, "Error when bind analysis", e);
        }
    }

    /**
     * This gets the angle by which an image must be rotated given
     * the device's current orientation.
     */
    protected int getRotation() throws NullPointerException {
        return previewView.getDisplay().getRotation();
    }

    /**
     * This configures the <code>BarcodeScanner</code> to read only QR codes,
     * analyzes and processes the QR code
     * @param image  The <code>ImageProxy</code> which is a wrapper for <code>media.Image</code>
     */
    @SuppressLint("UnsafeOptInUsageError")
    private void analyze(@NonNull ImageProxy image) {
        if (image.getImage() == null) return;

        InputImage inputImage = InputImage.fromMediaImage(
                image.getImage(),
                image.getImageInfo().getRotationDegrees()
        );

        BarcodeScannerOptions options = new BarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
                .build();

        BarcodeScanner QRScanner = BarcodeScanning.getClient(options);

        QRScanner.process(inputImage)
                .addOnSuccessListener(this::onSuccess)
                .addOnFailureListener(e -> Log.e(TAG, "QRCode process failure", e))
                .addOnCompleteListener(task -> image.close());
    }

    /**
     * TODO: java docs
     * @param qrcodes The list of qrcodes scanned?
     */
    private void onSuccess(List<Barcode> qrcodes) {            //TODO: Return raw value of QR Code
        if (qrcodes.size() > 0) {
            Toast.makeText(this, qrcodes.get(0).getRawValue(), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    /**
     * This calls <code>startCamera</code> when the start button is clicked
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        startCamera();
    }

}

