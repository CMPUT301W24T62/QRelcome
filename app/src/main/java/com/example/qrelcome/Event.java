package com.example.qrelcome;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import android.content.SharedPreferences;

public class Event {
    private String EID;
    private String title;
    private String desc;
    private String dateTime;
    private String location;
    private HashMap<String, Integer> attendance;

    public Event(){

    }

    public Event(String title, String description, String date, String location){
        this.EID = UUID.randomUUID().toString();
        this.title = title;
        this.desc = description;
        this.dateTime= date;
        this.location = location;
        this.attendance = new HashMap<String, Integer>();
        this.attendance.put("this has to be changed", 1);     //Find a better solution to fix the bug

    }

    public HashMap<String, Object> getEventData(){
        HashMap<String, Object> data = new HashMap<>();
        data.put("EID", EID);
        data.put("Title", title);
        data.put("Description", desc);
        data.put("DateTime", dateTime);
        data.put("Location", location);
        data.put("Attendance", attendance);

        return data;

    }

    //Not necessary
    public void setEventData(Map<String ,Object> data){
        this.location = (String) data.get("location");
        this.desc = (String) data.get("description");
        this.title = (String) data.get("title");
        this.dateTime = (String) data.get("DateTime");
        this.EID = (String) data.get("documentID");
    }

//    public void setEID(String id) {
//        this.EID = id;
//
//    }

    public String getEID() {
        return this.EID;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
        updateDB();
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
        updateDB();
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
        updateDB();
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
        updateDB();
    }

    public HashMap getAttendance() {return this.attendance;}

    public void setAttendance(HashMap attendance){
        this.attendance = attendance;
        updateDB();
    }

    public void setFirstAttendee(String uid){
        this.attendance = new HashMap<String, Integer>();
        this.attendance.put(uid, 1);
        updateDB();
    }

    public void setEvent(Event event){
        setTitle(event.getTitle());
        setDesc(event.getDesc());
        setDateTime(event.getDateTime());
        setLocation(event.getLocation());
        setAttendance(event.getAttendance());
    }

    public void setEvent(String title, String description, String dateTime, String location, @Nullable HashMap<String, Integer> attendance){
        this.title = title;
        this.desc = description;
        this.dateTime= dateTime;
        this.location = location;
        this.attendance = attendance;
    }

    public void addCheckIn(String UID){
        //if (this.attendance==null){
            //this.attendance = new HashMap<String, Integer>();
        //}
        this.attendance.put(UID, (Integer)this.attendance.getOrDefault(UID, 0) + 1);
        updateDB();
    }

    private void updateDB() {
        EventDB db = new EventDB();

        db.editEvent(this);


    }
}
