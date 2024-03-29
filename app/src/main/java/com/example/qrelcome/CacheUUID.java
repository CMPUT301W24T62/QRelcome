package com.example.qrelcome;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

// UUID STUFF
// Android Developers (https://developer.android.com/training/data-storage/app-specific#java) consulted for help using the app specific cache files
// StackOverflow (https://stackoverflow.com/questions/6880990/how-to-test-for-a-file-in-cache) consulted for help checking if file exists
// Baeldung (https://www.baeldung.com/java-uuid) consulted for help generating a UUID with java's build in UUID class
// W3schools (https://www.w3schools.com/java/java_files_create.asp) consulted for help with fileIO and error handling
// ChatGPT (https://chat.openai.com/share/a193e9d1-1268-49dc-a4a3-f8167bdb3f0d) consulted for help reading a file to a byte array

public class CacheUUID {
    private static UUID uuid;
    private static boolean gottenUUID;
    private UserProfile User;
    private UserDB user_db;

    //public CacheUUID() {
    //    this.gottenUUID = false;
    //}

    /**
     * Returns the user's uuid, either from class storage or from
     * @param context the android context from which the app runs, to specify where the file can be collected from
     * @return the UUID, either new or from existing file
     */
    //Redundant
    public static UUID getUUID(Context context) {
        if (gottenUUID) {
            Toast.makeText(context, "Yayyy", Toast.LENGTH_SHORT).show();
            return uuid;
        }
        return getUUIDFromFile(context);
    }

    //Redundant (Created by Allison on Pooja's computer)
    public static UUID getUUIDStored() {
        if (gottenUUID) {
            return uuid;
        } else {
            return null;
        }
    }
    public static UUID getUUIDFromFile(Context context) {
        File cacheDir = new File(context.getCacheDir(), "uuid");
        try {
            //File cacheDir = new File(context.getCacheDir(), "uuid");
            if (!cacheDir.exists()) {
                //cacheDir = File.createTempFile("uuid", null, context.getCacheDir());
                FileWriter write = new FileWriter(cacheDir.getPath());
                String uuid = UUID.randomUUID().toString();
                write.write(uuid);
                //write.write(UUID.randomUUID().toString());
                write.close();
                //Toast.makeText(context, "First time1", Toast.LENGTH_SHORT).show();
                //Sets first time user info
                UserProfile user = new UserProfile(uuid, null, "User "+uuid, null, null, false, false);
                UserDB user_db = new UserDB();
                user_db.addNewUserProfile(user);
                Toast.makeText(context, "First Time User", Toast.LENGTH_SHORT).show();
            }
            else {

                Toast.makeText(context, "Old User", Toast.LENGTH_SHORT).show();
            }
            gottenUUID = true;

            String fileContents = new String(readFileToByteArray(cacheDir.getPath()));
            Toast.makeText(context, fileContents, Toast.LENGTH_LONG).show();
            uuid = UUID.fromString(fileContents);

            return uuid;
        } catch (IOException e) {
            // TODO: figure out how to handle the file not being created and what to do about it
            // generate a random uuid to return and just have the user be temporarily treated brand new user
            return UUID.randomUUID();
        }
    }

    /**
     * This method was written by ChatGPT
     * This method reads the contents of a file to a byte array
     * @param filePath the path to file, as a string, for the information to be read from
     * @return the content of the file as an array of bytes
     */
    public static byte[] readFileToByteArray(String filePath) {
        File file = new File(filePath);
        byte[] byteArray = null;

        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            byteArray = bos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArray;
    }

}
