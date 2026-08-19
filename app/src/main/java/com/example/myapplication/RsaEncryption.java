package com.example.myapplication;

import android.util.Log;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.PublicKey;
import android.util.Base64;

import javax.crypto.Cipher;

public class RsaEncryption {
    private static final String TAG = "RsaEncryption";

    public static void run() throws Exception{

        String originalText = "This message will be encrypted with RSA";

        //create key pair public,private key
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        //encrypt the text using public key
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(originalText.getBytes("UTF-8"));

        //
        String encryptedTextReadable = Base64.encodeToString(encryptedBytes, Base64.DEFAULT);

        //decrypt the text using the PRIVATE key
        cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        String decryptedText = new String(decryptedBytes, "UTF-8");

        Log.d(TAG, "Original text  : " + originalText);
        Log.d(TAG, "Encrypted text : " + encryptedTextReadable);
        Log.d(TAG, "Decrypted text : " + decryptedText);
        Log.d(TAG, "Did it work?   : " + originalText.equals(decryptedText));
    }


}
