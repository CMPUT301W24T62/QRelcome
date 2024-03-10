package com.example.qrelcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.HashMap;
import java.util.Map;

public class QRCodeGenerator extends AppCompatActivity {

    //Event event;
    //QRCodeInfo qr_code_info;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_qr);
        ImageView qr_image = findViewById(R.id.qrImage);
        Button save_qr = findViewById(R.id.save_qr_button);

        save_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(QRCodeGenerator.this, "You clicked on Save Button", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(QRCodeGenerator.this, AttendeeHomeScreen.class);
                startActivity(intent);    //TODO: Actually save the image
            }
        });

        Intent intent1 = getIntent();
        String id = intent1.getStringExtra("ID");
        HashMap<String, String> map = new HashMap<>();
        map.put("CODE", "CHECK-IN"/**code**/);
        map.put("ID", id);
        String json_data = new Gson().toJson(map);

        Bitmap qrCodeBitmap = QRCodeGenerator.generateQRCode(json_data, 512, 512);
        qr_image.setImageBitmap(qrCodeBitmap);
    }

    /**
     * This encodes the given data in a QR Code
     * @param data The data to be encoded
     * @param width The width of the QR Code
     * @param height The height of the QRCode
     * @return bitmap of the QR Code
     */
    public static Bitmap generateQRCode(String data, int width, int height) {
        try {
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, width, height, hints);

            int matrixWidth = bitMatrix.getWidth();
            int matrixHeight = bitMatrix.getHeight();
            int[] pixels = new int[matrixWidth * matrixHeight];

            for (int y = 0; y < matrixHeight; y++) {
                int offset = y * matrixWidth;
                for (int x = 0; x < matrixWidth; x++) {
                    pixels[offset + x] = bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
                }
            }

            Bitmap bitmap = Bitmap.createBitmap(matrixWidth, matrixHeight, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, matrixWidth, 0, 0, matrixWidth, matrixHeight);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }
}
