package com.example.qrelcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EventDetailsScreen extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefs";
    private static final String EVENT_NAME_KEY = "EventName";
    private static final String EVENT_LOCATION_KEY = "Location";
    private static final String EVENT_DATE_TIME_KEY = "DateTime";
    private static final String EVENT_DESCRIPTION_KEY = "Description";

    private SharedPreferences sharedPreferences;
    private EditText EventName;
    private EditText Location;
    private EditText DateTime;
    private EditText Description;

    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private ArrayList<String> MilestoneResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details_screen);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        LinearLayout EventPage2 = findViewById(R.id.Event_Page2);
        ImageView Menu = findViewById(R.id.Menu1);
        ImageView BackButton = findViewById(R.id.Back_Button);
        ImageView EventPoster = findViewById(R.id.Event_Poster);
        EditText EventName = findViewById(R.id.Event_Name);
        EditText Location = findViewById(R.id.Event_Location1);
        EditText DateTime = findViewById(R.id.Event_Date_Time1);
        EditText Description = findViewById(R.id.Event_Description1);
        TextView Milestones = findViewById(R.id.Event_Milestones1);
        TextView CreateQR = findViewById(R.id.Event_QR1);
        Button Confirm = findViewById(R.id.Confirm_Button1);
        ImageView ForwardButton = findViewById(R.id.Forward_Button);

        //PopUp Stuff
        View inflatedView = getLayoutInflater().inflate(R.layout.milestone_popup_screen, null);
        CheckBox M100 = inflatedView.findViewById(R.id.Milestone100);
        CheckBox M200 = inflatedView.findViewById(R.id.Milestone200);
        CheckBox M300 = inflatedView.findViewById(R.id.Milestone300);
        CheckBox M400 = inflatedView.findViewById(R.id.Milestone400);
        CheckBox M500 = inflatedView.findViewById(R.id.Milestone500);
        Button Confirm2 = inflatedView.findViewById(R.id.Confirm_Button2);
        MilestoneResult = new ArrayList<>();
        //Popup stuff end

        EventName.setText(sharedPreferences.getString(EVENT_NAME_KEY, ""));
        EventName.setHint("Event Name:");

        Location.setText(sharedPreferences.getString(EVENT_LOCATION_KEY, ""));
        Location.setHint("Location:");

        DateTime.setText(sharedPreferences.getString(EVENT_DATE_TIME_KEY, ""));
        DateTime.setHint("Date / Time:");

        Description.setText(sharedPreferences.getString(EVENT_DESCRIPTION_KEY, ""));
        Description.setHint("Description:");

        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EventDetailsScreen.this, "You clicked on the Dropdown menu", Toast.LENGTH_SHORT).show();

            }
        });
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EventDetailsScreen.this, "You clicked on the Back Button", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(EventDetailsScreen.this, OrganizerHomeScreen.class);
                startActivity(intent);

            }
        });
        EventPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EventDetailsScreen.this, "You clicked on the Event Poster", Toast.LENGTH_SHORT).show();

            }
        });
        Milestones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EventDetailsScreen.this, "You clicked on the Milestones", Toast.LENGTH_SHORT).show();

                EventPage2.setVisibility(View.INVISIBLE);
                popupWindow = new PopupWindow(inflatedView, LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupWindow.showAtLocation(EventPage2, Gravity.BOTTOM,0,0);


            }
        });
        CreateQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EventDetailsScreen.this, "You clicked on the forward button", Toast.LENGTH_SHORT).show();

            }
        });
        ForwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EventDetailsScreen.this, "You clicked on the create promo QR code", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(EventDetailsScreen.this, CreateNewEventScreen.class);
                startActivity(intent);

            }
        });
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredEventName = EventName.getText().toString().trim();
                String enteredEventLocation = Location.getText().toString().trim();
                String enteredEventDateTime = DateTime.getText().toString().trim();
                String enteredEventDescription = Description.getText().toString().trim();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(EVENT_NAME_KEY, enteredEventName);
                editor.putString(EVENT_LOCATION_KEY, enteredEventLocation);
                editor.putString(EVENT_DATE_TIME_KEY, enteredEventDateTime);
                editor.putString(EVENT_DESCRIPTION_KEY, enteredEventDescription);
                editor.apply();


                Toast.makeText(EventDetailsScreen.this, "You clicked to Confirm the details", Toast.LENGTH_SHORT).show();

            }
        });

        //Popup ClickListeners
        M100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (M100.isChecked())
                    MilestoneResult.add("100");
                else
                    MilestoneResult.remove("100");
            }
        });
        M200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (M200.isChecked())
                    MilestoneResult.add("200");
                else
                    MilestoneResult.remove("200");
            }
        });
        M300.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (M300.isChecked())
                    MilestoneResult.add("300");
                else
                    MilestoneResult.remove("300");
            }
        });
        M400.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (M400.isChecked())
                    MilestoneResult.add("400");
                else
                    MilestoneResult.remove("400");
            }
        });
        M500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (M500.isChecked())
                    MilestoneResult.add("500");
                else
                    MilestoneResult.remove("500");
            }
        });

        Confirm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

                getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                // Set the main layout to visible
                EventPage2.setVisibility(View.VISIBLE);
            }
        });


    }

}