package com.example.myapplication.security;

import android.util.Log;
import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AesManager {

    private static final String TAG = "AesManager";
    private SecretKey key;

    public AesManager() {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(256);
            this.key = generator.generateKey();
            Log.i(TAG, "AES key successfully generated.");
        } catch (Exception e) {
            Log.e(TAG, "Key generation failed: " + e.getMessage());
        }
    }

    public String encryptData(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, this.key);
            byte[] encrypted = cipher.doFinal(plainText.getBytes());
            Log.d(TAG, "Data successfully encrypted.");
            return Base64.encodeToString(encrypted, Base64.DEFAULT);
        } catch (Exception e) {
            Log.e(TAG, "Encryption failed: " + e.getMessage());
            return null;
        }
    }
}
