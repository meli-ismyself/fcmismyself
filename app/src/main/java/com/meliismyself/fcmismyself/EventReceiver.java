package com.meliismyself.fcmismyself;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by meli.oktavia on 24/08/2016.
 */
public class EventReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "onReceive", Toast.LENGTH_LONG).show();
        Log.d("EventReceiver", "++++++++++ EventReceiver ++++++++++");
    }
}
