package com.example.qrelcome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminHomeScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen_admin);

        ImageView Menu = findViewById(R.id.Menu_Dropdown);
        ImageView ProfileIcon = findViewById(R.id.Profile_Icon);
        Button ProfileButton = findViewById(R.id.Profiles_Button);
        Button EventsButton = findViewById(R.id.Events_Button);
        Button ImagesButton = findViewById(R.id.Images_Button);

        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminHomeScreen.this, "You clicked on the Menu Dropdown Icon", Toast.LENGTH_SHORT).show();

            }
        });
        ProfileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminHomeScreen.this, "You clicked on the Profile Icon", Toast.LENGTH_SHORT).show();

            }
        });
        ProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminHomeScreen.this, "You clicked on the Profiles button", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminHomeScreen.this, AdminProfilesScreen.class);
                startActivity(intent);

            }
        });
        EventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminHomeScreen.this, "You clicked on the Events button", Toast.LENGTH_SHORT).show();

            }
        });
        ImagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminHomeScreen.this, "You clicked on the Images button", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
