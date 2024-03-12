package com.example.qrelcome;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.core.View;

import java.util.HashMap;
import java.util.Map;


public class EventDB extends Event {

    private FirebaseFirestore db;
    private CollectionReference eventsRef;
    //private CollectionReference attendanceRef;
    private Activity activity;
    //private Event returnEvent = new Event();

    public EventDB() {
        db = FirebaseFirestore.getInstance();
        eventsRef = db.collection("Events");
    }

    /**
     * Adds a new Event to the database using info from a provided EventCreator object
     *
     * @param event the EventCreator object whose data is being added to the database
     */
    public void addNewEvent(Event event) {       // TODO: NEED EventCreator CLASS
        HashMap<String, Object> data = event.getEventData(); // Get event details in hashmap form //TODO: create appropriate class in EventCreator class

        eventsRef.document(event.getEID())
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("Firestore", "Successfully created new document");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Firestore", "Failed to make new Document");
                    }
                });

    }


    /**
     * Removes a event from Events collection of the database from a EventManager object
     * @param event the EventManager object whose EID is that of the Event to be removed
     */
    public void deleteEvent(Event event) {       // TODO: Make EventsManager class
        String EID = event.getEID();               // TODO: REPLACE "getEID()" WITH APPROPRIATE METHOD FROM EventsManager CLASS
        eventsRef
                .document(EID)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("Firestore", "DocumentSnapshot deleted successfully (from Events)");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Firestore", "DocumentSnapshot deletion failed (from Events)");
                    }
                });
    }

    /**
     * One time get info for a particular Event
     * @param EID the String EOD of the Event whose information is to be collected
     * @return the EventsManager object with all fields filled in as seen currently in the database
     */
    public void getEventInfo(String EID, LifecycleOwner lifecycleOwner) {
        Event event = new Event();
        // The following was adapted from the firebase documentation:
        //eventsRef.document(EID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
        DocumentReference docRef = eventsRef.document(EID);
        docRef.get().addOnCompleteListener(task -> {
            //@Override
            //public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    Log.d("Firestore", "DocumentSnapshot data: " + document);
                    if (document.exists()) {
                        //Map<String, Object> documentData = document.getData();
                        String dateTime = document.getString("DateTime");
                        String description = document.getString("Description");
                        String eid = document.getString("EID");
                        String location = document.getString("Location");
                        String title = document.getString("Title");
                        HashMap<String, Integer> attendance = (HashMap<String, Integer>) document.get("Attendance");
                        for (HashMap.Entry<String, Integer> entry : attendance.entrySet()) {
                            String key = entry.getKey();
                            Object value = entry.getValue();
                        }
                        event.setEvent(eid, title, description, dateTime, location, attendance);
                        Log.d("Firestore", "Event data: " + event);
                        EventViewModel eventViewModel = new ViewModelProvider((ViewModelStoreOwner) lifecycleOwner).get(EventViewModel.class);
                        eventViewModel.setSharedEvent(event);
                        Log.d("Firestore", "Shared data: " + event);

                        //Log.d("Firestore", "DocumentSnapshot data: " + documentData);

                        //if (documentData != null) {
                            //returnEvent.setEventData(documentData);      // TODO: create setEventData() in EventsManager
                        //}
                    } else {
                        Log.d("Firestore", "No such document for get");
                    }
                } else {
                    Log.d("Firestore", "get failed with ", task.getException());
                }
            //}
        });
        //return returnEvent;
    }

    /**
     * Modifies an existing event in the database using info from a provided EventsManager object
     *
     * @param event the EventsManager object whose data is being added to the database
     */
    public void editEvent(Event event) {
        HashMap<String, Object> data = event.getEventData();
        eventsRef
                .document(event.getEID())
                .set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("Firestore", "Successfuly Updated Event Data" + event.getEID());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Firestore", "Failed to Update Event Data" + event.getEID());
                    }
                });
    }

    public void test(){
        eventsRef.add(new HashMap<String,Object>());
    }
}
