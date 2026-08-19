package com.example.myapplication;

import android.util.Log;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class PasswordHashing {

    private static final String TAG = "PasswordHashingDemo";

    public static void run() throws Exception {

        String password = "secretPassword123";

        // create random salt
        byte[] salt = createRandomSalt();

        //hash the password together with the salt
        String hash = hashPassword(password, salt);

        //hash it again with the same salt
        String hashAgain = hashPassword(password, salt);

        //hash the same password but with a different salt
        byte[] otherSalt = createRandomSalt();
        String hashWithOtherSalt = hashPassword(password, otherSalt);

        //print everything to Logcat
        Log.d(TAG, "Password              : " + password);
        Log.d(TAG, "Hash (first time)     : " + hash);
        Log.d(TAG, "Hash (same salt again): " + hashAgain);
        Log.d(TAG, "Same salt -> same hash: " + hash.equals(hashAgain));
        Log.d(TAG, "Hash (different salt) : " + hashWithOtherSalt);
        Log.d(TAG, "Different salt -> different hash: " + !hash.equals(hashWithOtherSalt));
    }

    //Creates 16 random bytes to use as salt
    private static byte[] createRandomSalt() {
        byte[] salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }

    //Combines the password and the salt, then runs them through SHA-256
    private static String hashPassword(String password, byte[] salt) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        digest.update(salt);
        //hash the password bytes together with the salt
        byte[] hashBytes = digest.digest(password.getBytes());

        return bytesToHex(hashBytes);
    }

    //Converts raw bytes into a readable hex string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
