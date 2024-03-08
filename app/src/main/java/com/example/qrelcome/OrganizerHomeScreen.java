package com.example.qrelcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OrganizerHomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView LeftIcon = findViewById(R.id.Left_Icon);
        ImageView RightIcon = findViewById(R.id.Right_Icon);
        TextView Title = findViewById(R.id.Toolbar_Title);
        ImageView CreateNewEvent = findViewById(R.id.Create_New_Event);

        LeftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrganizerHomeScreen.this, "You clicked on Dropdown icon", Toast.LENGTH_SHORT).show();
            }
        });
        RightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrganizerHomeScreen.this, "You clicked on Profile icon", Toast.LENGTH_SHORT).show();
            }
        });
        CreateNewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrganizerHomeScreen.this, "You clicked to create a new event", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OrganizerHomeScreen.this, EventDetailsScreen.class);
                startActivity(intent);
            }
        });
    }
}
