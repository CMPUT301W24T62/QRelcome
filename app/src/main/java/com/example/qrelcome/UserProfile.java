package com.example.qrelcome;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// W3schools (https://www.w3schools.com/java/java_hashmap.asp) consulted for a list of methods for HashMap class

public class UserProfile {
    private UUID uuid;
    private String contact;
    private String name;
    private String imageLink; // stores the link to the image within the "storage" database in firebase
    private String homepage;
    private Boolean geolocationOn;
    private Boolean isAdmin;

    public UserProfile() {

        this.uuid = CacheUUID.getUUIDStored();
        UserDB db = new UserDB();

        this.setData(db.getUserInfo(this.getUIDString()).getData());
    }

    /**
     * Collects the user data for a specific UserProfile object and returns it within a hashmap
     * @return the hashmap with the UserProfile object's information
     */
    public Map<String, Object> getData() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("contact", this.contact);
        hashMap.put("name", this.name);
        hashMap.put("imageLink", this.imageLink);
        hashMap.put("homepage", this.homepage);
        hashMap.put("geolocationOn", this.geolocationOn);
        hashMap.put("isAdmin", this.isAdmin);
        return hashMap;
    }

    /**
     * Sets the UserProfile object's data to that provided in the map
     * @param data the map storing the information to be set as the user information
     */
    public void setData(@NonNull Map<String, Object> data) {
        this.contact = String.valueOf(data.get("contact"));
        this.name = String.valueOf(data.get("name"));
        this.imageLink = String.valueOf(data.get("imageLink"));
        this.homepage = String.valueOf(data.get("homepage"));
        this.geolocationOn = Boolean.valueOf(String.valueOf(data.get("geolocationOn")));
        this.isAdmin = Boolean.valueOf(String.valueOf(data.get("isAdmin")));
    }


    // GET FUNCTIONS

    public String getContact() {
        return contact;
    }

    public String getName() {
        return name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getUIDString() {
        return uuid.toString();
    }
    public Boolean getGeolocationOn() {
        return geolocationOn;
    }
    public Boolean getIsAdmin() {
        return isAdmin;
    }


    // SET FUNCTIONS

    public void setContact(String contact) {
        this.contact = contact;
        updateDB();
    }

    public void setName(String name) {
        this.name = name;
        updateDB();
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
        updateDB();
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
        updateDB();
    }

    public void setGeolocationOn(Boolean geolocationOn) {
        this.geolocationOn = geolocationOn;
        updateDB();
    }

    /**
     * Sets GeolocationOn to False, thereby disabling Geolocation for this userProfile
     */
    public void disableGeolocation() {
        this.geolocationOn = false;
        updateDB();
    }

    /**
     * Sets GeolocationOn to True for this user profile object
     */
    public void enableGeolocation() {
        this.geolocationOn = true;
        updateDB();
    }


    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
        updateDB();
    }

    /**
     * Sets Admin to False, thereby disabling Geolocation for this userProfile
     */
    public void disableAdmin() {
        this.isAdmin = false;
        updateDB();
    }

    /**
     * Sets Admin to True for this user profile object
     */
    public void enableAdmin() {
        this.isAdmin = true;
        updateDB();
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }


    // UPDATE DATABASE FUNCTION

    /**
     * Notifies the UserDB to update and edits the profile with the new information
     */
    public void updateDB() {
        UserDB db = new UserDB();
        db.editUserProfile(this);
    }
}
