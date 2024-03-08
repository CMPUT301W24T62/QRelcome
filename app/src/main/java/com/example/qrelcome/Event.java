package com.example.qrelcome;

import com.google.firebase.firestore.GeoPoint;

import java.util.HashMap;
import java.util.Map;

public class Event {
    private String EID;
    private String title;
    private String desc;
    private java.util.Date dateTime;

    private GeoPoint location;

    private HashMap attendance;


    public Event(){



    }

    public Event(String title, String description, java.util.Date date, GeoPoint l){
        this.title = title;
        this.desc = description;
        this.dateTime= date;
        this.location = l;
        this.attendance = new HashMap<String, Integer>();

    }

    public HashMap<String, Object> getEventData(){
        HashMap<String, Object> data = new HashMap<>();
        data.put("Title", title);
        data.put("Description", desc);
        data.put("DateTime", dateTime);
        data.put("Location", location);
        data.put("Attendance", attendance);

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
        return this.EID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        updateDB();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
        updateDB();
    }

    public java.util.Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(java.util.Date dateTime) {
        this.dateTime = dateTime;
        updateDB();
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
        updateDB();
    }


    public void addCheckIn(String UID){
        this.attendance.put(UID, (Integer)this.attendance.getOrDefault(UID, 0) + 1);
        updateDB();
    }

    private void updateDB() {
        EventDB db = new EventDB();

        db.editEvent(this);


    }
}
