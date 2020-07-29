package com.mg.firstcode;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        for(int i = 0 ; i<5; i++){
            try {
                Log.d("IntentService", "IntentService진행중" + (i + 1));
                Thread.sleep(1000);
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }

    }

}