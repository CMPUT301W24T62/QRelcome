package com.example.qrelcome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qrelcome.databinding.HomescreenAttendeeBinding;

import java.util.UUID;

public class AttendeeHomeScreen extends AppCompatActivity {

    private String uid;
    private UserProfile user = new UserProfile();
    private UserDB user_db;
    public Toolbar toolbar;
    SharedPreferences preferences;
    private UserViewModel userViewModel;
    private HomescreenAttendeeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.homescreen_attendee);

        preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String uuidString = preferences.getString("UUID", null);
        if (uuidString != null) {
            uid = uuidString;
        };
        /**
        user_db = new UserDB();
        user_db.getUserInfo(uid, this);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        MutableLiveData<UserProfile> sharedUserData = (MutableLiveData<UserProfile>) userViewModel.getSharedUser();
        UserProfile retrievedUser = sharedUserData.getValue();
        user.setUserProfile(retrievedUser);

        if(user.getIsAdmin()){
            binding.adminToolbar.getRoot().setVisibility(View.VISIBLE);
            binding.defaultToolbar.getRoot().setVisibility(View.GONE);
        }else{
            binding.adminToolbar.getRoot().setVisibility(View.GONE);
            binding.defaultToolbar.getRoot().setVisibility(View.VISIBLE);
        }
            **/
        ImageView MenuIcon = findViewById(R.id.menu_attendee);
        ImageView ProfileIcon = findViewById(R.id.profile_icon);
        Button BrowseEvents = findViewById(R.id.button_browse_events);
        Button ScanEvents = findViewById(R.id.button_scan_events);
        Button AttendeeProfile = findViewById(R.id.button_attendee_profile);
        Button OrganizerProfile = findViewById(R.id.button_organizer_profile);

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

        AttendeeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AttendeeHomeScreen.this, "Switch to Attendee Profile", Toast.LENGTH_SHORT).show();
            }
        });

        OrganizerProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AttendeeHomeScreen.this, "Switch to Organizer Profile", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AttendeeHomeScreen.this, OrganizerHomeScreen.class);
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
