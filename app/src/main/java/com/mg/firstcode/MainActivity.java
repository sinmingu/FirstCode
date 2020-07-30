package com.mg.firstcode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    // 테스트 클릭버튼
    Button btn_test1, btn_test2, btn_test3, btn_test4, btn_test5, btn_test6, btn_test7, btn_test8;
    //결과 화면 텍스트
    TextView text1;
    //1번째, 2번째 입력란
    EditText edit_id, edit_pw;
    //확인완료 버튼
    Button btn_login;

    TextView text_weather1;

    //날씨 변수
    String temp;
    String weather;

    // 위치를 알아오기 위한 변수
    double latitude= 36.0;
    double longitude =48.0;

    //일일 날씨
    private String url = "https://api.openweathermap.org";
    private String key = "1966fce57124a1e39ecef2d1eaeb7c0b";
    //로그 확인을 위해
    private static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        return builder.build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_test1 = (Button)findViewById(R.id.btn_test1);
        btn_test2 = (Button)findViewById(R.id.btn_test2);
        btn_test3 = (Button)findViewById(R.id.btn_test3);
        btn_test4 = (Button)findViewById(R.id.btn_test4);
        btn_test5 = (Button)findViewById(R.id.btn_test5);
        btn_test6 = (Button)findViewById(R.id.btn_test6);
        btn_test7 = (Button)findViewById(R.id.btn_test7);
        btn_test8 = (Button)findViewById(R.id.btn_test8);



        text_weather1 = (TextView)findViewById(R.id.text_weather1);


        //서버의 json 응답을 간단하게 변환하도록 해주는 작업
        Retrofit client = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).client(createOkHttpClient()).build();
        //인터페이스
        Weather_Interface interFace = client.create(Weather_Interface.class);

        //Call
        Call<Repo> call = interFace.get_weather(key,Double.valueOf(latitude), Double.valueOf(longitude));
        call.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(Call<Repo> call, Response<Repo> response) {
                if(response.isSuccessful()){
                    Repo repo = response.body();

                    temp= Math.round(((repo.getMain().getTemp()-273.15)*10)/10.0)+"º";
                    weather = repo.getWeather().get(0).getMain();
                    String tempMax = Math.round(((repo.getMain().getTemp_max()-273.15)*10)/10.0)+"";
                    String tempMin = Math.round(((repo.getMain().getTemp_min()-273.15)*10)/10.0)+"";
                    text_weather1.setText(temp+ repo.getWeather().get(0).getMain());

//                    now_temp_maxmin_text.setText("최저 "+tempMin+"º");
//                    now_temp_center_text.setText(" / ");
//                    now_temp_min_text.setText("최고 "+tempMax+"º");


//                    if(repo.getWeather().get(0).getMain().equals("Clouds")){
//                        Glide.with(MainActivity.this).load(R.drawable.cloud).fitCenter().into(now_temp_img);
//                        Glide.with(MainActivity.this).load(R.drawable.cloud).fitCenter().into(view4_now_temp_img);
//
//                    }
//                    else if(repo.getWeather().get(0).getMain().equals("Clear")){
//                        Glide.with(MainActivity.this).load(R.drawable.sun).fitCenter().into(now_temp_img);
//                        Glide.with(MainActivity.this).load(R.drawable.sun).fitCenter().into(view4_now_temp_img);
//                    }
//                    else if(repo.getWeather().get(0).getMain().equals("Rain")){
//                        Glide.with(MainActivity.this).load(R.drawable.rain).fitCenter().into(now_temp_img);
//                        Glide.with(MainActivity.this).load(R.drawable.rain).fitCenter().into(view4_now_temp_img);
//                    }
//                    else if(repo.getWeather().get(0).getMain().equals("Snow")){
//                        Glide.with(MainActivity.this).load(R.drawable.snowing).fitCenter().into(now_temp_img);
//                        Glide.with(MainActivity.this).load(R.drawable.snowing).fitCenter().into(view4_now_temp_img);
//                    }
//                    else if(repo.getWeather().get(0).getMain().equals("Mist")){
//                        Glide.with(MainActivity.this).load(R.drawable.fog).fitCenter().into(now_temp_img);
//                        Glide.with(MainActivity.this).load(R.drawable.fog).fitCenter().into(view4_now_temp_img);
//                    }
//                    else if(repo.getWeather().get(0).getMain().equals("Haze")){
//                        Glide.with(MainActivity.this).load(R.drawable.fog).fitCenter().into(now_temp_img);
//                        Glide.with(MainActivity.this).load(R.drawable.fog).fitCenter().into(view4_now_temp_img);
//                    }
//                    else if(repo.getWeather().get(0).getMain().equals("Fog")){
//                        Glide.with(MainActivity.this).load(R.drawable.fog).fitCenter().into(now_temp_img);
//                        Glide.with(MainActivity.this).load(R.drawable.fog).fitCenter().into(view4_now_temp_img);
//                    }



                    // nofication 알람
//                    nofication_alram();


                }
            }

            @Override
            public void onFailure(Call<Repo> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"일일 날씨 에러",Toast.LENGTH_SHORT).show();
            }
        });


        text1 = (TextView)findViewById(R.id.text1);
        edit_id = (EditText)findViewById(R.id.edit_id);
        edit_pw = (EditText)findViewById(R.id.edit_pw);

        btn_login = (Button)findViewById(R.id.btn_login);

        Thread_Exam1 RunFirst = new Thread_Exam1();
        final Thread thread = new Thread(RunFirst);

        final Thread_Exam2 thread2 = new Thread_Exam2();

        btn_test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HandlerActivity.class);
                startActivity(intent);
//                new BackgroundTask_Exam().execute();
            }
        });
        btn_test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                thread.start();

            }
        });
        btn_test3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), BroadActivity.class);
                startActivity(intent2);
            }
        });
        btn_test4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ServiceActivity.class);
                startActivity(intent);
//                SharedPreferences sf = getSharedPreferences("exFile",MODE_PRIVATE);
//                SharedPreferences.Editor editor = sf.edit();
//                editor.putString("exam",edit_id.getText().toString());
//                editor.commit();

            }
        });
        //데이터 인텐트
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SubActivity.class);
                intent.putExtra("FirstData",edit_id.getText().toString());
                intent.putExtra("TwoData",edit_pw.getText().toString());
                startActivityForResult(intent, 1);
            }
        });

//        SharedPreferences sf = getSharedPreferences("exFile",MODE_PRIVATE);
//        text1.setText(sf.getString("exam","값이 없습니다"));
        btn_test5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
            }
        });
        btn_test6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TextWriter.class);
                startActivity(intent);
            }
        });
        //리사이클뷰
        btn_test7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), RecyleView.class);
                startActivity(intent);

            }
        });
        btn_test8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ButtonStyle_1.class);
                startActivity(intent);

            }
        });

    }

    // 실습코드 2
    class Thread_Exam1 implements Runnable{

        int count = 0;

        @Override
        public void run() {
            try {

                for(int i = 0 ; i<10; i++) {
                    Thread.sleep(500);
                    count++;
                    text1.setText(count + "");
                }

            }
            catch(Exception e){
                e.getStackTrace();
            }
        }
    }

    class Thread_Exam2 extends Thread{

        int count = 0 ;
        Thread_Exam2(){

        }

        @Override
        public void run() {

            try{

                for(int i =0; i<10; i++){
                    Thread.sleep(500);
                    count++;
                    text1.setText(count);
                }

            }
            catch(Exception e){
                e.getStackTrace();
            }

        }
    }

    // 실습코드 1
    class BackgroundTask_Exam extends AsyncTask<Void, Void, String> {

        int count = 0 ;
        @Override
        protected void onPreExecute() {

        }
        // 전달된 URL 사용 작업
        @Override
        protected String doInBackground(Void... voids) {
            try{

            }
            catch(Exception e){
                e.getStackTrace();
            }
            return null;
        }
        // 파일 다운로드 퍼센티지 표시 작업
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            count++;
            text1.setText(count+"");
        }

        @Override
        protected void onPostExecute(String result) {
        // doInBackground 에서 받아온 total 값 사용 장소

            count++;
            text1.setText(count+"");

            try{
                Thread.sleep(500);
                new Two_Asy().execute();
            }
            catch(Exception e) {
                e.getStackTrace();
            }

        }
    }

    class Two_Asy extends AsyncTask<Void, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            text1.setText("20");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Void... voids) {
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode ==RESULT_OK){

            text1.setText(data.getStringExtra("data_result"));

        }
    }

}