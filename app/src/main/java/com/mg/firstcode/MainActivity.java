package com.mg.firstcode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    // 테스트 클릭버튼
    Button btn_test1, btn_test2, btn_test3, btn_test4, btn_test5, btn_test6, btn_test7, btn_test8, text_btn;
    //결과 화면 텍스트
    TextView text1;
    //1번째, 2번째 입력란
    EditText edit_id, edit_pw;
    //확인완료 버튼
    Button btn_login;
    // 날씨
    TextView text_weather1;
    //코로나
    TextView text_corona;
    //날씨 변수
    String temp;
    String weather;

    // 위치를 알아오기 위한 변수
    double latitude= 36.0;
    double longitude =48.0;
    String address="";

    ImageView img_local;

    private GpsTracker gpsTracker;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;

    //코로나19 (지역별 확진자수)
    private String location_url = "http://openapi.data.go.kr/";
    private String location_key = "FhIbqBYEHWDmIgHDufkWFiRccTmu8Lavedlo%2FnccvmChSLG1QY%2Bd0lQoyYX%2B55JyA34U75DrTvcUt5feJRZ9Ng%3D%3D";

    //코로나19 (전체 사람들의 수)
    private String covid19_url = "http://openapi.data.go.kr/";
    private String covid19_key = "FhIbqBYEHWDmIgHDufkWFiRccTmu8Lavedlo%2FnccvmChSLG1QY%2Bd0lQoyYX%2B55JyA34U75DrTvcUt5feJRZ9Ng%3D%3D";

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

        img_local = (ImageView)findViewById(R.id.img_local);

        Glide.with(getApplicationContext()).load(R.drawable.local).fitCenter().into(img_local);

        Calendar cal = Calendar.getInstance();
        final int num = cal.get(Calendar.DAY_OF_WEEK);
//        String today = weekDay[num];
        final int num2 = cal.get(Calendar.DAY_OF_WEEK)+1;
        final int month = cal.get(Calendar.MONTH)+1;
        String String_month = "";
        if(month <10){
            String_month = "0"+month;
        }
        else{
            String_month = String.valueOf(month);
        }
        final String num3 = String.valueOf(cal.get(Calendar.YEAR))+String_month+String.valueOf(cal.get(Calendar.DATE));

        final String today = String.valueOf(cal.get(Calendar.YEAR)) +". "+String_month + ". "+String.valueOf(cal.get(Calendar.DATE));
//        String today2 = weekDay[num2];
//        int num3 = cal.get(Calendar.DAY_OF_WEEK)+2;
//        String today3 = weekDay[num3];
//        int num4 = cal.get(Calendar.DAY_OF_WEEK)+3;
//        String today4 = weekDay[num4];
//        int num5 = cal.get(Calendar.DAY_OF_WEEK)+4;
//        String today5 = weekDay[num5];
//        int num6 = cal.get(Calendar.DAY_OF_WEEK)+5;
//        String today6 = weekDay[num6];

        //------------------------ gps 시작

        if (!checkLocationServicesStatus()) {

            showDialogForLocationServiceSetting();
        }else {

            checkRunTimePermission();
        }
        final TextView location_here = (TextView)findViewById(R.id.location_here);
        gpsTracker = new GpsTracker(MainActivity.this);
        latitude = gpsTracker.getLatitude();
        longitude = gpsTracker.getLongitude();
        double week_latitude = gpsTracker.getLatitude();
        double week_longitude = gpsTracker.getLongitude();
        address = getCurrentAddress(latitude, longitude);

//        location_here.setText(address.substring(5,address.indexOf(" ", 13)));
        if(address.equals("주소 미발견")){
            location_here.setText(address);
        }
        else{
            location_here.setText(address.substring(5,address.indexOf(" ", 13)));
        }

        //----------------- gps 끝 --------------------

        //---------------- 날씨 ------------------------
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

                    if (weather.equals("Clouds")) {
                        weather = "구름";
                    } else if (weather.equals("Clear")) {
                        weather = "맑음";
                    } else if (weather.equals("Rain")) {
                        weather = "비";
                    } else if (weather.equals("Snow")) {
                        weather = "눈";
                    } else if(weather.equals("Haze")){
                        weather = "안개";
                    } else if(weather.equals("Mist")){
                        weather = "안개";
                    }

                    text_weather1.setText(today+"\n\n현재기온 - "+temp+ " "+weather);

                }
            }
            @Override
            public void onFailure(Call<Repo> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"일일 날씨 에러",Toast.LENGTH_SHORT).show();
            }
        });

        //----------------------------- 버튼 이벤트 지정 -----------------------------

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

//                thread.start();

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

        nofication_alram();

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
        //-- gps
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                        checkRunTimePermission();
                        return;
                    }
                }

                break;
        }
    }


    //-------------------------- gps 함수 --------------------------------------


    /*
     * ActivityCompat.requestPermissions를 사용한 퍼미션 요청의 결과를 리턴받는 메소드입니다.
     */
    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if ( permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;


            // 모든 퍼미션을 허용했는지 체크합니다.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if ( check_result ) {

                //위치 값을 가져올 수 있음
                ;
            }
            else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {

                    Toast.makeText(MainActivity.this, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                    finish();


                }else {

                    Toast.makeText(MainActivity.this, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();

                }
            }

        }
    }

    void checkRunTimePermission(){

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            // 3.  위치 값을 가져올 수 있음



        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(MainActivity.this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MainActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MainActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

    }

    public String getCurrentAddress( double latitude, double longitude) {

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    7);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }



        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        }

        Address address = addresses.get(0);
        return address.getAddressLine(0).toString()+"\n";

    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 정상적으로 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 하실건가요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        switch (requestCode) {
//
//            case GPS_ENABLE_REQUEST_CODE:
//
//                //사용자가 GPS 활성 시켰는지 검사
//                if (checkLocationServicesStatus()) {
//                    if (checkLocationServicesStatus()) {
//
//                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
//                        checkRunTimePermission();
//                        return;
//                    }
//                }
//
//                break;
//        }
//    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    //----------------------------- gps 함수 끝 -------------------------
    //알람 매니저
    public void nofication_alram(){

        Intent intent = new Intent(MainActivity.this, AlarmReceiver_nofi.class);
        intent.putExtra("address",address);
        intent.putExtra("temp",temp);
        intent.putExtra("weather",weather);
        intent.putExtra("temp2","dada");

        PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);


        // 알람을 받을 시간을 5초 뒤로 설정

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());

//        calendar.add(Calendar.SECOND, 5);

        calendar.set(Calendar.HOUR_OF_DAY, 12); // calendar.set(Calendar.HOUR_OF_DAY, 시간(int)); 저녁10시->22시

        calendar.set(Calendar.MINUTE, 59); //calendar.set(Calendar.MINUTE, 분(int));

        calendar.set(Calendar.SECOND, 59);

//      calendar.set(calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), calendar.get(Calendar.DATE), 18, 57, 0);


        // 알람 매니저에 알람을 등록

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

//        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 200000,sender);


    }


}