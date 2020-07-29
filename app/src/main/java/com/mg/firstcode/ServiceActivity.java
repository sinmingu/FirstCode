package com.mg.firstcode;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ServiceActivity extends AppCompatActivity {

    Button start_service, end_service, start_IntentService, start_groundService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        start_service = (Button)findViewById(R.id.start_service);
        end_service = (Button)findViewById(R.id.end_service);
        start_IntentService = (Button)findViewById(R.id.start_IntentService);
        start_groundService = (Button)findViewById(R.id.start_groundService);

        start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                startService(intent);
            }
        });

        end_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                stopService(intent);
            }
        });

        start_IntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyIntentService.class);
                startService(intent);
            }
        });

        start_groundService.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.setAction("startForeground");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(intent);
                }

                else{
                    startService(intent);
                }
            }
        });

    }
}