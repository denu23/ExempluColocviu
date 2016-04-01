package ro.pub.cs.systems.eim.exemplucolocviu;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ColocviuService extends Service {
    private ProcessingThread thread;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int s1 = Integer.parseInt(intent.getStringExtra("int1"));
        int s2 = Integer.parseInt(intent.getStringExtra("int2"));
        thread = new ProcessingThread(this, s1, s2);
        thread.run();
        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true;
    }

    @Override
    public void onRebind(Intent intent) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
