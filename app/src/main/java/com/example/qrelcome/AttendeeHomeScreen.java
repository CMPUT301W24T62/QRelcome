package com.example.qrelcome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class AttendeeHomeScreen extends AppCompatActivity {

    String uid;
    private UserProfile user;
    private UserDB user_db;
    public Toolbar toolbar;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen_attendee);
        Toast.makeText(AttendeeHomeScreen.this, "First time7", Toast.LENGTH_SHORT).show();

        preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String uuidString = preferences.getString("UUID", null);
        if (uuidString != null) {
            uid = uuidString;
        };

        user_db = new UserDB();
        user = user_db.getUserInfo(uid);
        Toast.makeText(AttendeeHomeScreen.this, "First time6", Toast.LENGTH_SHORT).show();

        //CheckAdmin();
        /**
        if(user.getIsAdmin()){
            toolbar = findViewById(R.id.admin_toolbar);
        }else{
            toolbar = findViewById(R.id.default_toolbar);
        }
        setSupportActionBar(toolbar);
         **/

        ImageView MenuIcon = findViewById(R.id.menu_attendee);
        ImageView ProfileIcon = findViewById(R.id.profile_icon);
        Button BrowseEvents = findViewById(R.id.button_browse_events);
        Button ScanEvents = findViewById(R.id.button_scan_events);

        MenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AttendeeHomeScreen.this, "You clicked on Menu icon", Toast.LENGTH_SHORT).show();
            }
        });

        ProfileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AttendeeHomeScreen.this, "You clicked on Profile Icon", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(AttendeeHomeScreen.this, UserActivity.class);
                //startActivity(intent);
            }
        });

        BrowseEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AttendeeHomeScreen.this, "You clicked on Browse Events Button", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AttendeeHomeScreen.this, OrganizerHomeScreen.class);
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

    /**
    public void CheckAdmin(){
        Toolbar toolbar;
        if(default_user.getAdmin()){
            toolbar = findViewById(R.id.admin_toolbar);
        }else{
            toolbar = findViewById(R.id.default_toolbar);
        }
        setSupportActionBar(toolbar);
    }
     **/
}
