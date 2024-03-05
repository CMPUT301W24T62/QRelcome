package com.example.qrelcome;

import org.junit.Test;

import java.util.HashMap;
import java.util.UUID;

public class UserDBTest {

    /**
     * Generates a UserProfile object with filled in fields for use in UserDBTest class
     * @return the userProfile object
     */
    private UserProfile generateTestUserProfile() {
        UserProfile testUser = new UserProfile();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("contact", "apple@apple.com");
        map.put("name", "Steve Jobs");
        map.put("imageLink", "//upload.wikimedia.org/wikipedia/commons/thumb/d/dc/Steve_Jobs_Headshot_2010-CROP_%28cropped_2%29.jpg/220px-Steve_Jobs_Headshot_2010-CROP_%28cropped_2%29.jpg");
        map.put("homepage", "apple.com");
        map.put("geolocationOn", "true");

        testUser.setData(map);
        testUser.setUuid(UUID.randomUUID());
        return testUser;
    }

    @Test
    public void testAddUser() {
        UserDB testDB = new UserDB();
        testDB.addNewUserProfile(generateTestUserProfile());
    }
}
