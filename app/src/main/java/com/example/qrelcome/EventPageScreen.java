package com.example.qrelcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class EventPageScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_event_page);

        ImageView Menu = findViewById(R.id.Menu);
        ImageView EventPoster = findViewById(R.id.Event_Poster);
        Button EventDetails = findViewById(R.id.Event_Details);
        Button Attendees = findViewById(R.id.Attendees);
        Button SendNotifications = findViewById(R.id.Send_Notifications);
        Button ShareEvent = findViewById(R.id.Share_Event);
        Button Map = findViewById(R.id.Map);
        ImageView Home = findViewById(R.id.Home2);

        EventDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EventPageScreen.this, "You clicked on the Event Details button", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EventPageScreen.this, EventDetailsScreen.class);
                startActivity(intent);

            }
        });


    }
}
