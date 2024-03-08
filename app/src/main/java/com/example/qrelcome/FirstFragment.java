package com.example.qrelcome;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.qrelcome.databinding.FragmentFirstBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.GeoPoint;
import com.google.type.DateTime;
import com.google.type.DateTime.Builder;
import com.google.type.DateTimeOrBuilder;

import org.checkerframework.checker.units.qual.C;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class FirstFragment extends Fragment {
    public Event event;
    public EventDB db;
    public Button pickDateButton;
    public Button pickTimeButton;
    public  Button createEventButton;

    private FragmentFirstBinding binding;

    private Date date;
    final Calendar cal = Calendar.getInstance();


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pickDateButton = view.findViewById(R.id.pickDateButton);
        pickTimeButton = view.findViewById(R.id.pickTimeButton);
        createEventButton = view.findViewById(R.id.createEventButton);
        db = new EventDB();
        pickDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDatePickerDialog();


            }
        });

        pickTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimePickerDialog();
            }
        });

        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addNewEvent(new Event("TEST 123", "Description", date, new GeoPoint(0,0)));
            }
        });


    }

    private void openDatePickerDialog(){
        DatePickerDialog dialog = new DatePickerDialog(requireActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                date = cal.getTime();



            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

        dialog.show();
    }

    private void openTimePickerDialog(){
        TimePickerDialog dialog = new TimePickerDialog(requireActivity(), (TimePickerDialog.OnTimeSetListener) (view, hourOfDay, minute) -> {
            cal.set(Calendar.HOUR, hourOfDay);
            cal.set(Calendar.MINUTE, minute);
            date = cal.getTime();
        }, 12, 0, false);

        dialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}