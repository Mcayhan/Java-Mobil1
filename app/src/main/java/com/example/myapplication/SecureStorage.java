package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

public class SecureStorage {

    private static final String TAG = "SecureStorage";
    private static final String FILE_NAME = "secure_prefs";
    private static final String KEY_NAME = "sample_token";

    public static void run(Context context) {
        try {
            MasterKey masterKey = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            SharedPreferences securePrefs = EncryptedSharedPreferences.create(
                    context,
                    FILE_NAME,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );

            String originalValue = "sensitive-token-12345";

            SharedPreferences.Editor editor = securePrefs.edit();
            editor.putString(KEY_NAME, originalValue);
            editor.apply();

            String savedValue = securePrefs.getString(KEY_NAME, null);

            Log.d(TAG, "Value we saved : " + originalValue);
            Log.d(TAG, "Value we read back : " + savedValue);
            Log.d(TAG, "Are they the same? : " + originalValue.equals(savedValue));

        } catch (Exception e) {
            Log.e(TAG, "Something went wrong", e);
        }
    }
}
