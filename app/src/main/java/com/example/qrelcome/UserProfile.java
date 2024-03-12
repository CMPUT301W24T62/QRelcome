package com.example.qrelcome;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// W3schools (https://www.w3schools.com/java/java_hashmap.asp) consulted for a list of methods for HashMap class

public class UserProfile {
    private String uuid;
    //private UUID uuid;
    private String contact;
    private String name;
    private String imageLink; // stores the link to the image within the "storage" database in firebase
    private String homepage;
    private Boolean geolocationOn;
    private Boolean isAdmin;

    //TODO: Add another hashmap that stores signed up events (Follows similar procedure as the attendance hashmap in Event and EventDB class)

    /**
    public UserProfile() {

        this.uuid = CacheUUID.getUUIDStored();
        UserDB db = new UserDB();

        this.setUserData(db.getUserInfo(this.getUIDString()).getData());
    }
     **/

    public UserProfile(){

    }

    public UserProfile(String uuid, @Nullable String contact, String name, @Nullable String imageLink, @Nullable String homepage, Boolean geolocationOn, Boolean isAdmin){
        this.uuid = uuid;
        this.contact = contact;
        this.name = name;
        this.imageLink = imageLink;
        this.homepage = homepage;
        this.geolocationOn = geolocationOn;
        this.isAdmin = isAdmin;
    }

    //I don't think we need this either, not sure. Don't delete
    /**
    public void setUserProfile(Context context){
        this.uuid = CacheUUID.getUUIDFromFile(context).toString();
        UserDB db = new UserDB();

        this.setUserData(db.getUserInfo(this.getUIDString()).getData());
    }
     */


    /**
     * Collects the user data for a specific UserProfile object and returns it within a hashmap
     * @return the hashmap with the UserProfile object's information
     */
    public Map<String, Object> getData() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("uuid", this.uuid);
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
    public void setUserProfileData(@NonNull Map<String, Object> data) {
        this.contact = String.valueOf(data.get("contact"));
        this.name = String.valueOf(data.get("name"));
        this.imageLink = String.valueOf(data.get("imageLink"));
        this.homepage = String.valueOf(data.get("homepage"));
        this.geolocationOn = Boolean.valueOf(String.valueOf(data.get("geolocationOn")));
        this.isAdmin = Boolean.valueOf(String.valueOf(data.get("isAdmin")));
    }

    public void setUserProfile(UserProfile user){
        this.uuid = user.getUID();
        this.contact = user.getContact();
        this.name = user.getName();
        this.imageLink = user.getImageLink();
        this.homepage = user.getHomepage();
        this.geolocationOn = user.getGeolocationOn();
        this.isAdmin = user.getIsAdmin();
    }

    //This will be more useful once signed up events hashmap is created
    public void setUserProfile(String uuid, @Nullable String contact, String name, @Nullable String imageLink, @Nullable String homepage, Boolean geolocationOn, Boolean isAdmin){
        this.uuid = uuid;
        this.contact = contact;
        this.name = name;
        this.imageLink = imageLink;
        this.homepage = homepage;
        this.geolocationOn = geolocationOn;
        this.isAdmin = isAdmin;
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

    //Redundant method
    public String getUIDString() {
        return uuid.toString();
    }
    public Boolean getGeolocationOn() {
        return geolocationOn;
    }
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public String getUID(){ return uuid;}


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

    //Redundant method
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

    //public void setUuid(UUID uuid) {this.uuid = uuid;}
    public void setUuid(String uuid) {this.uuid = uuid;}


    // UPDATE DATABASE FUNCTION

    /**
     * Notifies the UserDB to update and edits the profile with the new information
     */
    public void updateDB() {
        UserDB db = new UserDB();
        db.editUserProfile(this);
    }
}
