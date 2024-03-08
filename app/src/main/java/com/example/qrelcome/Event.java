package com.example.qrelcome;

import com.google.firebase.firestore.GeoPoint;
import com.google.type.Date;

import java.util.HashMap;
import java.util.Map;

public class Event {
    private String EID;
    private String title;
    private String desc;
    private String dateTime;

    private String location;

    public Event(){



    }

    public Event(String title, String description, String date, String l){
        this.title = title;
        this.desc = description;
        this.dateTime= date;
        this.location = l;

    }

    public HashMap<String, Object> getEventData(){
        HashMap<String, Object> data = new HashMap<>();
        data.put("title", title);
        data.put("description", desc);
        data.put("DateTime", dateTime);
        data.put("Location", location);

        return data;

    }

    public void setEventData(Map<String ,Object> data){
        this.location = (String) data.get("location");
        this.desc = (String) data.get("description");
        this.title = (String) data.get("title");
        this.dateTime = (String) data.get("DateTime");
        this.EID = (String) data.get("documentID");

    }


    public void setEID(String id) {
        this.EID = id;
    }

    public String getEID() {
        return EID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
