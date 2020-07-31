package com.mg.firstcode;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlarmReceiver_nofi extends BroadcastReceiver {

    GpsTracker gpsTracker;
    double latitude;
    double longitude;
    String address;
    String temp;
    String weather;

    // openweather 을 위한 url과 키
    private String url = "http://api.openweathermap.org";
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
    public void onReceive(final Context context, Intent intent) {

        gpsTracker = new GpsTracker(context);

        latitude = gpsTracker.getLatitude();
        longitude = gpsTracker.getLongitude();

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());

        List<Address> addresses = null;

        try {

            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    7);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(context, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();

        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(context, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();

        }

        if (addresses == null || addresses.size() == 0) {

            Toast.makeText(context, "주소 미발견", Toast.LENGTH_LONG).show();

        }

        Address address2 = addresses.get(0);
        address = address2.getAddressLine(0).toString();

        // ----------------------

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

                    temp = Math.round(((repo.getMain().getTemp()-273.15)*10)/10.0)+"º";
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

                    String arr[] = address.split(" ");

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default");
                    builder.setSmallIcon(R.drawable.coronavirus);

                    builder.setContentTitle("코로나 조심하세요");

                    builder.setContentText(arr[1] + " " + arr[2] + " " + arr[3] + " - 기온 : " + temp + " 날씨 : " + weather);

                    builder.setColor(Color.rgb(57, 149, 211));

                    // 사용자가 탭을 클릭하면 자동 제거
                    builder.setAutoCancel(true);

                    builder.setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0));

                    // 알림 표시
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
                    }

                    // id값은
                    // 정의해야하는 각 알림의 고유한 int값
                    notificationManager.notify(1, builder.build());

                }
            }

            @Override
            public void onFailure(Call<Repo> call, Throwable t) {
                Toast.makeText(context,"일일 날씨 에러",Toast.LENGTH_SHORT).show();
            }
        });




    }






}
