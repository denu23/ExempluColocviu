package ro.pub.cs.systems.eim.exemplucolocviu;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class ColocviuServiceBroadcastReceiver extends BroadcastReceiver {


   // public ColocviuServiceBroadcastReceiver() {
   // }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String data = intent.getStringExtra("data");
        Log.d("service-message", "broadcast message received: " + data);

        /*if ("current_date".equals(action)) {
            Log.d("service-message", "current date received");
            data = intent.getStringExtra("data");
        } else if ("sum".equals(action)) {
            Log.d("service-message", "sum received");
            data = intent.getStringExtra("data");
        } else if ("average".equals(action)) {
            Log.d("service-message", "average received");
            data = intent.getStringExtra("data");
        }*/
        //Log.d("service-message", data);
    }
}