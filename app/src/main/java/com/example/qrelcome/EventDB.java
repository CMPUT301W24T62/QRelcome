package com.example.qrelcome;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.EventManager;

import java.util.HashMap;
import java.util.Map;


public class EventDB {

    private FirebaseFirestore db;
    private CollectionReference eventsRef;

    public EventDB() {
        db = FirebaseFirestore.getInstance();
        eventsRef = db.collection("Events");
    }

    /**
     * Adds a new Event to the database using info from a provided EventCreator object
     * @param event the EventCreator object whose data is being added to the database
     */
    public void addNewEvent(EventCreator event) {       // TODO: NEED EventCreator CLASS
        HashMap<String, String> data = event.getHashMap(); // Get event details in hashmap form //TODO: create appropriate class in EventCreator class

        eventsRef
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Firestore", "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Firestore", "DocumentSnapshot writing failed");
                    }
                });

    }


    /**
     * Removes a event from Events collection of the database from a EventManager object
     * @param event the EventManager object whose EID is that of the Event to be removed
     */
    public void deleteEvent(EventsManager event) {       // TODO: Make EventsManager class
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
    public EventsManager getEventInfo(String EID) {       
        EventsManager returnEvent = new EventsManager();         
        // The following was adapted from the firebase documentation:
        eventsRef.document(EID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> documentData = document.getData();
                        Log.d("Firestore", "DocumentSnapshot data: " + documentData);
                        returnEvent.setEventData(documentData);         // TODO: create setEventData() in EventsManager

                    } else {
                        Log.d("Firestore", "No such document for get");
                    }
                } else {
                    Log.d("Firestore", "get failed with ", task.getException());
                }
            }
        });
        return returnEvent;
    }

    /**
     * Modifies an existing event in the database using info from a provided EventsManager object
     *
     * @param event the EventsManager object whose data is being added to the database
     */
    public void editEvent(EventsManager event) {         
        HashMap<String, String> data = event.getEventData();       
        eventsRef
                .document(event.getEID())
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Firestore", "DocumentSnapshot Edited with ID: " + documentReference.getId() );

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Firestore", "DocumentSnapshot Edit Failed");
                    }
                });
    }
}
