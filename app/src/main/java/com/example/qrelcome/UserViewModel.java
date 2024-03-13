package com.example.qrelcome;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.auth.User;

public class UserViewModel extends ViewModel{
    private MutableLiveData<UserProfile> sharedUserData = new MutableLiveData<>();

    public UserViewModel(){

    }

    public void setSharedUser(UserProfile user){
        sharedUserData.setValue(user);
    }

    public LiveData<UserProfile> getSharedUser(){
        return sharedUserData;
    }
}