package ro.pub.cs.systems.eim.exemplucolocviu;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;

public class ProcessingThread extends Thread {

    private Context context;
    private Intent intent;
    private int s1, s2;


    public ProcessingThread(Context context, int s1, int s2) {
        this.context = context;
        //this.intent = myIntent;
        this.s1 = s1;//Integer.parseInt(this.intent.getStringExtra("int1"));
        this.s2 = s2;//Integer.parseInt(this.intent.getStringExtra("int2"));
    }

    @Override
    public void run() {
        while(true){
            sendMessage(1);
            sleep();
            sendMessage(2);
            sleep();
            sendMessage(3);
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    private void sendMessage(int messageType) {
        Intent intent = new Intent();
        switch(messageType) {
            case 1:
                intent.setAction("current_date");
                intent.putExtra("data", new Date(System.currentTimeMillis()).toString());
                break;
            case 2:
                intent.setAction("sum");
                intent.putExtra("data", Integer.toString(this.s1 + this.s2));
                break;
            case 3:
                intent.setAction("average");
                intent.putExtra("data", Integer.toString((this.s1 + this.s2)/2));
                break;
        }
        Log.d("service-message", "sending broadcast for " + intent.getAction());
        context.sendBroadcast(intent);
    }
}