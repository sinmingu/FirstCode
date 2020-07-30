package com.mg.firstcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       HandlerActivity extends AppCompatActivity {

    Button btn_handler, btn_handler2, btn_handler3, btn_handler4;
    TextView text_handler;

    Thread thread;
    Thread_Handler thread_handler;
    int count = 0;
    int handler_status = 0;
    Handler handler;
    int time_count = 10;

    Handler Nohandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        btn_handler  = (Button)findViewById(R.id.btn_handler);
        text_handler = (TextView)findViewById(R.id.text_handler);
        btn_handler2= (Button)findViewById(R.id.btn_handler2);
        btn_handler3 = (Button)findViewById(R.id.btn_handler3);
        btn_handler4 = (Button)findViewById(R.id.btn_handler4);

        handler = new Handler(){

            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case 0:
                        text_handler.setText(count+", "+msg.obj);
                        break;
                    case 1:
                        thread_handler.stop_thread();
//                        thread_handler.interrupt();
                        break;
                    default:
                        break;
                }
            }

        };
        thread_handler = new Thread_Handler();

        Nohandler = new Handler(){

            @Override
            public void handleMessage(@NonNull Message msg) {
                switch(msg.what){
                    case 0:
                        break;
                    case 1:
                        Log.d("Two", "두번째 핸들러 : 1");
                        break;
                    default:
                        break;
                }

            }
        };

        btn_handler3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Thread example = new Thread("MyThread"){

                    @Override
                    public void run() {

                        for(int i = 0; i <10; i++){
                            Log.d("Two", "두번째 핸들러"+i);
                            Message message = Nohandler.obtainMessage();
                            message.what = 0;
                            String str = new String("Hello");
                            message.obj = str;

                            Nohandler.sendMessage(message);
//                            Nohandler.sendEmptyMessage(0);

                            try{
                                Thread.sleep(1000);
                            }
                            catch (Exception e){
                                e.getStackTrace();
                            }

                        }

                    }
                };
                example.start();



            }
        });

        btn_handler4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nohandler.sendEmptyMessage(1);
            }
        });

//        thread = new Thread(new Runnable() {
//            int status = 0;
//
//            public void stop_thread(){
//                status = 1;
//            }
//            @Override
//            public void run() {
//                if(status == 0){
//                    count++;
//                }
//
//                try{
//                    Thread.sleep(1000);
//                }
//                catch(Exception e){
//                    e.getStackTrace();
//                }
//
//            }
//        });

//         핸들러 첫번째 값 전송
        btn_handler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread_handler = new Thread_Handler();
                thread_handler.start();

            }
        });

        //핸들러 두번째 값 전송
        btn_handler2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                handler.sendEmptyMessage(1);


            }
        });
    }

    class Thread_Handler extends Thread{

        int status = 0;
        public void stop_thread(){
            status = 1;
        }

        public void start_thread(){
            status = 0;
        }
        @Override
        public void run() {

            while(status == 0){

                Log.d("Handler", "핸들러 작동중"+count);

                count++;
                // 메시지 얻어오기
                Message message = handler.obtainMessage();
                message.what = 0;
                message.arg1 = 2;
                message.arg2 = 5;
                String str = new String("핸들러 작동");
                message.obj = str;
                handler.sendMessage(message);

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.getStackTrace();
                }

            }

        }
    }



}