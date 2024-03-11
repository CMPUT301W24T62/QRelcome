package com.example.qrelcome;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EventViewModel extends ViewModel{
    private MutableLiveData<Event> sharedEventData = new MutableLiveData<>();

    public EventViewModel(){

    }

    public void setSharedEvent(Event event){
        sharedEventData.setValue(event);
    }

    public LiveData<Event> getSharedEvent(){
        return sharedEventData;
    }
}
