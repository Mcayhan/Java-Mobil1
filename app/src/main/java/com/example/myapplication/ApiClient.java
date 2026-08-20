package com.example.myapplication;

import android.util.Log;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String TAG = "ApiClientLog";
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private static final String CERTIFICATE_PIN =
            "sha256/fj/LGYZh+mUuNimcCT6b6V6MLFW1SIzcsM4hgwSwVB4=";

    public static Retrofit createClient() {
        CertificatePinner pinner = new CertificatePinner.Builder()
                .add("jsonplaceholder.typicode.com", CERTIFICATE_PIN)
                .build();

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .certificatePinner(pinner)
                .build();

        Log.i(TAG, "OkHttp client created with certificate pinning");

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
