package com.mg.firstcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    Button btn_alram, btn_alram_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        btn_alram = (Button)findViewById(R.id.btn_alram);
        btn_alram_delete = (Button)findViewById(R.id.btn_alram_delete);

        btn_alram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            show();


            }
        });

        btn_alram_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManagerCompat.from(getApplicationContext()).cancel(1);
            }
        });


    }

    private void show(){

        Log.d("Test", "알람 테스트중");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");
        builder.setSmallIcon(R.drawable.book);
        builder.setContentTitle("알람");
        builder.setContentText("알람이 생성되었습니다");

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

//        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
//
//        builder.setLargeIcon(largeIcon);
//
//        builder.setColor(Color.RED);
//
//        Uri ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(this,RingtoneManager.TYPE_NOTIFICATION);
//
//        builder.setSound(ringtoneUri);
//
//        long[] vibrate = {0, 100, 200, 300};
//        builder.setVibrate(vibrate);
//        builder.setAutoCancel(true);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(new NotificationChannel("default", "기본채널", NotificationManager.IMPORTANCE_DEFAULT));

            manager.notify(1, builder.build());
        }

    }
}