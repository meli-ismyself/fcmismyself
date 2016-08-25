package com.meliismyself.fcmismyself;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by aditya.augusta on 6/2/2016.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

        sendNotification(
                remoteMessage.getNotification().getTitle(),
                remoteMessage.getNotification().getBody(),
                remoteMessage.getNotification().getColor()
        );

        Log.d("sendNotification",  remoteMessage.getNotification().getTitle());
        //PlaySound();
        //onMessageReceived will be called when ever you receive new message from server.. (app in background and foreground )
//        Log.d("FCM", "From: " + remoteMessage.getFrom());
//
//        if(remoteMessage.getNotification()!=null){
//            Log.d("FCM", "Notification Message Body: " + remoteMessage.getNotification().getBody());
//        }
//
//        if(remoteMessage.getData().containsKey("post_id") && remoteMessage.getData().containsKey("post_title")){
//            Log.d("Post ID",remoteMessage.getData().get("post_id").toString());
//            Log.d("Post Title",remoteMessage.getData().get("post_title").toString());
//            // eg. Server Send Structure data:{"post_id":"12345","post_title":"A Blog Post"}
//        }
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
        */
    private void sendNotification(String messageTitle, String messageBody, String color) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Uri theSound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.kalimba);

        System.out.println("theSound +++++++++++++++++++ " + theSound);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setColor(Color.RED)
                .setSound(theSound)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private void PlaySound(){
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.kalimba);
        mp.start();
    }
}