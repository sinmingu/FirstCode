package com.mg.firstcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BroadActivity extends AppCompatActivity {
    BroadcastReceiver br;
    Button btn_start, btn_start2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad);
        Log.d("AAA", "BroadActivity : onCreate()");

        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start2= (Button)findViewById(R.id.btn_start2);

        final Intent intent2 = new Intent();
        intent2.setAction("Test");
        final Intent intent3 = new Intent();
        intent3.setAction("Test2");

        br = new MyReceiver();

        IntentFilter filter = new IntentFilter();
        filter.addAction("Test");
        filter.addAction("Test2");

        this.registerReceiver(br,filter);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent("test3");
                intent.setAction("test3");

//                sendBroadcast(intent);
                sendBroadcast(intent);

            }
        });

        btn_start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOrderedBroadcast(intent3, null);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(br);
    }

}