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

import java.util.HashMap;
import java.util.Map;

public class UserDB {
    private FirebaseFirestore db;
    private CollectionReference usersRef;

    public UserDB() {
        db = FirebaseFirestore.getInstance();
        usersRef = db.collection("UserProfiles");
    }

    /**
     * Adds a new userProfile to the database using info from a provided UserProfile object
     * @param user the UserProfile object whose data is being added to the database
     */
    public void addNewUserProfile(UserProfile user) {
        HashMap<String, Object> data = user.getData();
        usersRef
                .document(user.getUIDString())
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("Firestore", "DocumentSnapshot written with ID: ");
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
     * Removes a user from UserProfiles collection of the database by their UID
     * @param UID the UID of the user to be removed
     */
    public void deleteUserProfile(String UID) {
        usersRef
                .document(UID)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("Firestore", "DocumentSnapshot deleted successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Firestore", "DocumentSnapshot deletion failed");
                    }
                });
    }

    /**
     * Removes a user from UserProfiles collection of the database by their userProfile object
     * @param user the UserProfile object whose UID is that of the user to be removed
     */
    public void deleteUserProfile(UserProfile user) {
        String UID = user.getUIDString();
        usersRef
                .document(UID)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("Firestore", "DocumentSnapshot deleted successfully (from UserProfile)");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Firestore", "DocumentSnapshot deletion failed (from UserProfile)");
                    }
                });
    }

    /**
     * One time get info for a particular UserProfile
     * @param UID the String userName of the UserProfile whose information is to be collected
     * @return the UserProfile object with all fields filled in as seen currently in the database
     */
    public UserProfile getUserInfo(String UID) {
        UserProfile returnUser = new UserProfile();
        // The following was adapted from the firebase documentation:
        usersRef.document(UID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> documentData = document.getData();
                        Log.d("Firestore", "DocumentSnapshot data: " + documentData);

                        assert documentData != null;
                        returnUser.setData(documentData);

                    } else {
                        Log.d("Firestore", "No such document for get");
                    }
                } else {
                    Log.d("Firestore", "get failed with ", task.getException());
                }
            }
        });
        return returnUser;
    }

    /**
     * Modifies an existing userProfile in the database using info from a provided UserProfile object
     * This method behaves identically to AddNewUserProfile but just has a modified method name
     * @param user the UserProfile object whose data is being added to the database
     */
    public void editUserProfile(UserProfile user) {
        HashMap<String, Object> data = user.getData();
        usersRef
                .document(user.getUIDString())
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("Firestore", "DocumentSnapshot successfully written");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Firestore", "DocumentSnapshot writing failed");
                    }
                });
    }
}
