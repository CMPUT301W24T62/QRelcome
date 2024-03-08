package com.example.qrelcome;

import com.google.firebase.firestore.GeoPoint;
import com.google.type.Date;

import java.util.HashMap;
import java.util.Map;

public class Event {
    private String EID;
    private String title;
    private String desc;
    private java.util.Date dateTime;

    private GeoPoint location;

    public Event(){



    }

    public Event(String title, String description, java.util.Date date, GeoPoint l){
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
        this.location = (GeoPoint) data.get("location");
        this.desc = (String) data.get("description");
        this.title = (String) data.get("title");
        this.dateTime = (java.util.Date) data.get("DateTime");
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

    public java.util.Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(java.util.Date dateTime) {
        this.dateTime = dateTime;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }
}
