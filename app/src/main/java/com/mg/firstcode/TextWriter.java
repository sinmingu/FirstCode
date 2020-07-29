package com.mg.firstcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vdx.designertoast.DesignerToast;

import java.util.Random;
import java.util.logging.LogRecord;

public class TextWriter extends AppCompatActivity {

    Button btn_test1, btn_test2, btn_test3, btn_test4, btn_test5, btn_test6, btn_test7, btn_test8;
    ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9;
    Handler handler;
    TextView text_score;
    int score = 0;
    int user_status = 0;
    Button btn_start, btn_end;
    int rnd = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_writer);

        btn_test1 = (Button)findViewById(R.id.btn_test1);
        btn_test2 = (Button)findViewById(R.id.btn_test2);
        btn_test3 = (Button)findViewById(R.id.btn_test3);
        btn_test4 = (Button)findViewById(R.id.btn_test4);
        btn_test5 = (Button)findViewById(R.id.btn_test5);
        btn_test6 = (Button)findViewById(R.id.btn_test6);
        btn_test7 = (Button)findViewById(R.id.btn_test7);
        btn_test8 = (Button)findViewById(R.id.btn_test8);
        imageView1 = (ImageView)findViewById(R.id.image1);
        imageView2 = (ImageView)findViewById(R.id.image2);
        imageView3 = (ImageView)findViewById(R.id.image3);
        imageView4 = (ImageView)findViewById(R.id.image4);
        imageView5 = (ImageView)findViewById(R.id.image5);
        imageView6 = (ImageView)findViewById(R.id.image6);
        imageView7 = (ImageView)findViewById(R.id.image7);
        imageView8 = (ImageView)findViewById(R.id.image8);
        imageView9 = (ImageView)findViewById(R.id.image9);
        text_score = (TextView)findViewById(R.id.text_score);
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_end = (Button)findViewById(R.id.btn_end);

        handler = new Handler(){

            public void stop(){

            }

            @Override
            public void handleMessage(@NonNull Message msg) {

                switch (msg.what) {

                    case 0:
                        text_score.setText(score+"");
                        if(rnd == 1){
                            Glide.with(getApplicationContext()).load(R.drawable.duck).fitCenter().into(imageView1);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView2);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView3);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView4);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView5);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView6);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView7);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView8);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView9);
                        }
                        else if(rnd == 2){
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView1);
                            Glide.with(getApplicationContext()).load(R.drawable.duck).fitCenter().into(imageView2);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView3);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView4);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView5);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView6);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView7);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView8);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView9);
                        }
                        else if(rnd == 3){
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView1);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView2);
                            Glide.with(getApplicationContext()).load(R.drawable.duck).fitCenter().into(imageView3);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView4);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView5);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView6);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView7);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView8);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView9);
                        }
                        else if(rnd == 4){
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView1);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView2);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView3);
                            Glide.with(getApplicationContext()).load(R.drawable.duck).fitCenter().into(imageView4);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView5);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView6);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView7);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView8);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView9);
                        }
                        else if(rnd == 5){
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView1);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView2);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView3);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView4);
                            Glide.with(getApplicationContext()).load(R.drawable.duck).fitCenter().into(imageView5);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView6);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView7);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView8);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView9);
                        }
                        else if(rnd == 6){
                            Glide.with(TextWriter.this).load(R.drawable.baseball).fitCenter().into(imageView1);
                            Glide.with(TextWriter.this).load(R.drawable.baseball).fitCenter().into(imageView2);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView3);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView4);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView5);
                            Glide.with(getApplicationContext()).load(R.drawable.duck).fitCenter().into(imageView6);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView7);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView8);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView9);
                        }
                        else if(rnd == 7){
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView1);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView2);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView3);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView4);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView5);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView6);
                            Glide.with(getApplicationContext()).load(R.drawable.duck).fitCenter().into(imageView7);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView8);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView9);
                        }
                        else if(rnd == 8){
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView1);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView2);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView3);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView4);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView5);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView6);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView7);
                            Glide.with(getApplicationContext()).load(R.drawable.duck).fitCenter().into(imageView8);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView9);
                        }
                        else if(rnd == 9){
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView1);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView2);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView3);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView4);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView5);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView6);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView7);
                            Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView8);
                            Glide.with(getApplicationContext()).load(R.drawable.duck).fitCenter().into(imageView9);
                        }
                        break;
                    case 1:
                        break;
                    default:
                        break;

                }

            }
        };

        btn_test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DesignerToast.defaultToast(getApplicationContext(),"Default Toast",Gravity.CENTER,Toast.LENGTH_SHORT);
            }
        });

        btn_test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DesignerToast.Success(getApplicationContext(), "Success Toast", Gravity.CENTER, Toast.LENGTH_SHORT);
            }
        });

        btn_test3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DesignerToast.Error(getApplicationContext(), "Error Toast", Gravity.CENTER, Toast.LENGTH_SHORT);
            }
        });

        btn_test4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DesignerToast.Warning(getApplicationContext(),"Warning Toast",Gravity.CENTER,Toast.LENGTH_SHORT);
            }
        });

        btn_test5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DesignerToast.Success(getApplicationContext(), "Success", "Hello this is demo success!",Gravity.CENTER, Toast.LENGTH_SHORT,DesignerToast.STYLE_DARK);
            }
        });

        btn_test6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DesignerToast.Error(getApplicationContext(), "Error", "Hello this is demo error!",Gravity.CENTER, Toast.LENGTH_SHORT,DesignerToast.STYLE_DARK);
            }
        });

        btn_test7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DesignerToast.Warning(getApplicationContext(), "Warning", "Hello this is demo warning!",Gravity.CENTER, Toast.LENGTH_SHORT,DesignerToast.STYLE_DARK);
            }
        });

        btn_test8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DesignerToast.Info(getApplicationContext(), "Info", "Hello this is demo info!",Gravity.CENTER, Toast.LENGTH_SHORT,DesignerToast.STYLE_DARK);
            }
        });
        Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView1);
        Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView2);
        Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView3);
        Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView4);
        Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView5);
        Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView6);
        Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView7);
        Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView8);
        Glide.with(getApplicationContext()).load(R.drawable.baseball).fitCenter().into(imageView9);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread("Start_Game"){

                    @Override
                    public void run() {

                        for(int i = 0; i < 10; i++){

                            Message message = handler.obtainMessage();
                            message.what = 0;
                            handler.sendMessage(message);

                            user_status = 0;
                            Random random = new Random();
                            rnd = random.nextInt(9)+1;

                            if(user_status == rnd){
                                score ++;
                            }

                            try{
                                Thread.sleep(1000);
                            }
                            catch(Exception e){
                                e.getStackTrace();
                            }
                        }

                    }

                };
                thread.start();
            }
        });

        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(getApplicationContext()).load(R.drawable.duck).fitCenter().into(imageView9);
            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_status = 1;
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_status = 2;
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_status = 3;
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_status = 4;
            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_status = 5;
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_status = 6;
            }
        });
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_status = 7;
            }
        });
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_status = 8;
            }
        });
        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_status = 9;
            }
        });


    }

    class Game_thread extends Thread{

        int game_status = 0;

        public Game_thread() {

        }

        public void Game_stop(){
            game_status = 1;
        }

        @Override
        public void run() {

            for(int i = 0; i<10; i++){



            }

        }
    }
}