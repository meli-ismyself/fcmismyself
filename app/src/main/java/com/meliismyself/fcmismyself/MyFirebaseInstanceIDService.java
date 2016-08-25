package com.meliismyself.fcmismyself;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by aditya.augusta on 6/2/2016.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIDService";
    public static String refreshedToken;
    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        Log.d(TAG, "Trying to get FCM Token ID");
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //RegisterToken(refreshedToken);
        Log.d(TAG, "Refreshed token: " + refreshedToken);
//        SharedPreferences sharedPreferences = getSharedPreferences(Config.PPref, MODE_PRIVATE);
//        sharedPreferences.edit().putString(Config.PFCMToken, refreshedToken).commit();
    }
    // [END refresh_token]

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */

    private void RegisterToken(String token){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Token", token)
                .build();

        // 192.168.1.71/fcm/register.php
        Request request = new Request.Builder()
                .url("http://nyooi.com/meli/register.php")
                .post(body)
                .build();

        try {
            client.newCall(request).execute();
            System.out.println("berhasil +++++++++++++++++++++++++++++++++++ ");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("gagal +++++++++++++++++++++++++++ " + e.toString());
            //Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}