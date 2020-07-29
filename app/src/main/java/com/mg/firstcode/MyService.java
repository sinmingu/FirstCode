package com.mg.firstcode;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {

    private Thread thread;

    public MyService() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Service","StartService");

        if("startForeground".equals(intent.getAction())){
            startForegroundService();
        }
        else if(thread==null){
            thread = new Thread("My Service"){
                @Override
                public void run() {
                   for(int i = 0 ; i<10; i++){
                       try{
                           Thread.sleep(1000);
                       }
                       catch(Exception e){
                           e.getStackTrace();
                       }
                       Log.d("Service","Service진행중"+(i+1));
                   }
                }
            };
            thread.start();
        }


        return START_STICKY;


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(thread!=null){
            Log.d("Service","Service끝");
            thread.interrupt();
            thread = null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    private void startForegroundService(){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");
        builder.setSmallIcon(R.drawable.book);
        builder.setContentTitle("포그라운드 서비스");
        builder.setContentText("포그라운드 서비스 실행중");

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        builder.setContentIntent(pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(new NotificationChannel("default","기본채널",NotificationManager.IMPORTANCE_DEFAULT));
        }

        startForeground(1, builder.build());


    }
}
