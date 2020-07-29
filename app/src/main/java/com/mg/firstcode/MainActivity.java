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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    // 테스트 클릭버튼
    Button btn_test1, btn_test2, btn_test3, btn_test4, btn_test5, btn_test6, btn_test7, btn_test8;
    //결과 화면 텍스트
    TextView text1;
    //1번째, 2번째 입력란
    EditText edit_id, edit_pw;
    //확인완료 버튼
    Button btn_login;

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