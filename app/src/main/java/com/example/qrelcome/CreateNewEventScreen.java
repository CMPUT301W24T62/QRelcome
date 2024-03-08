package com.example.qrelcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CreateNewEventScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_create_events);

        //TextView EventName = findViewById(R.id.Event_Name);
        ImageView Edit = findViewById(R.id.Edit);
        TextView NewQR = findViewById(R.id.New_QR);
        TextView ReuseQR = findViewById(R.id.Reuse_QR);

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateNewEventScreen.this, "You clicked on Edit/Create Event name ", Toast.LENGTH_SHORT).show();
            }
        });

        NewQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateNewEventScreen.this, "You clicked on NewQR Button", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreateNewEventScreen.this, QRCodeGenerator.class);
                startActivity(intent);
            }
        });
        ReuseQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateNewEventScreen.this, "You clicked on Reuse QR button", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
