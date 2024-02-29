package com.example.qrelcome;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
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
    public void addNewUserProfile(UserProfile user) {       // TODO: NEED USER PROFILE CLASS
        HashMap<String, String> data = new HashMap<>();
        data.put("Contact", user.getContactInfo());         // TODO: REPLACE "getContactInfo" WITH APPROPRIATE METHOD FROM USERPROFILE CLASS
        usersRef
                .document(user.getUserName())               // TODO: REPLACE "getUserName" WITH APPROPRIATE METHOD FROM USERPROFILE CLASS
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

    /**
     * Removes a user from UserProfiles collection of the database by their userName
     * @param userName the userName of the user to be removed
     */
    public void deleteUserProfile(String userName) {
        usersRef
                .document(userName)
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
     * @param user the UserProfile object whose userName is that of the user to be removed
     */
    public void deleteUserProfile(UserProfile user) {       // TODO: NEED USER PROFILE CLASS
        String userName = user.getUserName();               // TODO: REPLACE "getUserName" WITH APPROPRIATE METHOD FROM USERPROFILE CLASS
        usersRef
                .document(userName)
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
     * @param userName the String userName of the UserProfile whose information is to be collected
     * @return the UserProfile object with all fields filled in as seen currently in the database
     */
    public UserProfile getUserInfo(String userName) {       // TODO: NEED USER PROFILE CLASS
        UserProfile returnUser = new UserProfile();         // TODO: NEED USER PROFILE CLASS
        // The following was adapted from the firebase documentation:
        usersRef.document(userName).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> documentData = document.getData();
                        Log.d("Firestore", "DocumentSnapshot data: " + documentData);
                        try {
                            returnUser.setContactInfo(documentData.get("Contact"));         // TODO: NEED TO REPLACE "setContactInfo" WITH APPROPRIATE METHOD FROM USERPROFILE CLASS
                        } catch (NullPointerException e) {
                            returnUser.setContactInfo("N/A");                               // TODO: NEED TO REPLACE "setContactInfo" WITH APPROPRIATE METHOD FROM USERPROFILE CLASS
                        }
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
    public void editUserProfile(UserProfile user) {         // TODO: NEED USER PROFILE CLASS
        HashMap<String, String> data = new HashMap<>();
        data.put("Contact", user.getContactInfo());         // TODO: REPLACE "getContactInfo" WITH APPROPRIATE METHOD FROM USERPROFILE CLASS
        usersRef
                .document(user.getUserName())               // TODO: REPLACE "getUserName" WITH APPROPRIATE METHOD FROM USERPROFILE CLASS
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
