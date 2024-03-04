package com.example.qrelcome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class EventDetailsScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_event_details);

        ImageView Menu = findViewById(R.id.Menu);
        ImageView EventPoster = findViewById(R.id.Event_Poster);
        Button EventDetails = findViewById(R.id.Event_Details);
        Button Attendees = findViewById(R.id.Attendees);
        Button SendNotifications = findViewById(R.id.Send_Notifications);
        Button ShareEvent = findViewById(R.id.Share_Event);
        Button Map = findViewById(R.id.Map);
        ImageView Home = findViewById(R.id.Home2);


    }
}
