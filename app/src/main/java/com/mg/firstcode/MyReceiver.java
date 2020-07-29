package com.mg.firstcode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("AAA","MyReceiver On");
        String action = intent.getAction();

        if(action.equals("Test")) {
            Toast.makeText(context, action, Toast.LENGTH_SHORT).show();
        }
        else if(action.equals("Test2")){
            Toast.makeText(context, action, Toast.LENGTH_SHORT).show();
        }else if(action.equals("test3")){
            Toast.makeText(context, "정적", Toast.LENGTH_SHORT).show();
        }



    }
}
