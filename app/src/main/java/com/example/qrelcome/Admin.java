package com.example.qrelcome;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.UUID;


// Eventually we can probably move the database functionalities to the UserDB class
public class Admin {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = db.collection("UserProfiles");

    /**
     * Thank you chatGPT
     * Gets an ArrayAdapter of each of the users from the collection
     * @return the ArrayAdapter of type UserProfile for filling out the list view
     */
    public AdminUserArrayAdapter getUsers(Context context) {
        ArrayList<UserProfile> users = new ArrayList<UserProfile>();
        usersRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        UserProfile user = new UserProfile();
                        user.setUuid(UUID.fromString(document.getId()));
                        user.setData(document.getData());
                        users.add(user);
                    }
                } else {
                    Log.d("Firestore", "Error getting documents");
                }
            }
        });
        return new AdminUserArrayAdapter(context, users);
    }


}
