package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import android.util.Base64;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "DAY20Crypto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        aesGenerateKey();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void aesGenerateKey(){
        try {
            //generate the key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            SecretKey secretKey = keyGenerator.generateKey();
            Log.d(TAG, "AES key generated successfully: "  + secretKey.getAlgorithm());

            String plainText = "Hello, this is a secret message";
            //encrypt
            String encryptedText = aesEncrypt(plainText, secretKey);
            Log.d(TAG, "Original text: " + plainText);
            Log.d(TAG, "Encrypted text: "+ encryptedText);

            //decrypt
            String decryptedText = aesDecrypt(encryptedText, secretKey);
            Log.d(TAG, "Decrypted text: " + decryptedText);
            Log.d(TAG, "Decryption matches original? " + decryptedText.equals(plainText));

            //encrypt the same text again, with the same key, and compare
            String encryptedTextAgain = aesEncrypt(plainText, secretKey);
            Log.d(TAG, "Encrypted text (2nd time): " + encryptedTextAgain);
            Log.d(TAG, "Are the two encrypted outputs identical? " + encryptedText.equals(encryptedTextAgain));

        }catch (Exception e){
            Log.e(TAG, "Failed");
        }
    }

    //encryption
    private String aesEncrypt(String plainText, SecretKey key) throws Exception{

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");


        byte[] iv = new byte[12];
        new SecureRandom().nextBytes(iv);
        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, iv);


        cipher.init(Cipher.ENCRYPT_MODE, key, gcmSpec);


        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

        byte[] combined = new byte[iv.length + encryptedBytes.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(encryptedBytes, 0, combined, iv.length, encryptedBytes.length);

        return Base64.encodeToString(combined, Base64.NO_WRAP);
    }

    //decryption
    private String aesDecrypt(String encryptedBase64, SecretKey key) throws Exception {

        byte[] combined = Base64.decode(encryptedBase64, Base64.NO_WRAP);

        byte[] iv = new byte[12];
        System.arraycopy(combined, 0, iv, 0, 12);

        byte[] encryptedBytes = new byte[combined.length - 12];
        System.arraycopy(combined, 12, encryptedBytes, 0, encryptedBytes.length);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, gcmSpec);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

}