package com.example.qrelcome;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminProfilesScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profiles_list_screen);

        ImageView Menu = findViewById(R.id.Menu_Dropdown2);
        ImageView ProfileIcon = findViewById(R.id.Profile_Icon2);
        ImageView Home = findViewById(R.id.Home2);

        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminProfilesScreen.this, "You clicked on the Menu Dropdown Icon", Toast.LENGTH_SHORT).show();

            }
        });
        ProfileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminProfilesScreen.this, "You clicked on the Profile Icon", Toast.LENGTH_SHORT).show();

            }
        });
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminProfilesScreen.this, "You clicked on the Home Icon", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminProfilesScreen.this, AdminHomeScreen.class);
                startActivity(intent);

            }
        });



    }
}
