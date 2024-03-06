package com.example.qrelcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AttendeeHomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen_attendee);

        ImageView MenuIcon = findViewById(R.id.menu_attendee);
        ImageView ProfileIcon = findViewById(R.id.profile_icon);
        Button BrowseEvents = findViewById(R.id.button_browse_events);
        Button ScanEvents = findViewById(R.id.button_scan_events);

        MenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AttendeeHomeScreen.this, "You clicked on Dropdown icon", Toast.LENGTH_SHORT).show();
            }
        });

        ProfileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AttendeeHomeScreen.this, "You clicked on Profile icon", Toast.LENGTH_SHORT).show();
            }
        });

        BrowseEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AttendeeHomeScreen.this, "You clicked on Browse Events Button", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AttendeeHomeScreen.this, QRCodeGenerator.class);
                startActivity(intent);
            }
        });

        ScanEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AttendeeHomeScreen.this, "You clicked on Scan Events Button", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AttendeeHomeScreen.this, QRCodeScanner.class);
                startActivity(intent);
            }
        });
    }
}
