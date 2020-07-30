package com.mg.firstcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lakue.pagingbutton.LakuePagingButton;
import com.lakue.pagingbutton.OnPageSelectListener;

import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ButtonStyle_1 extends AppCompatActivity {

    LakuePagingButton lpb_buttonlist;
    int max_page = 20;
    TextView text_page;
    ViewPager2 viewPager2;
    Handler handler;
    int p = 0;
    TextView test_text;

    //나이 조사
    private String url = "http://openapi.data.go.kr/";
    private String key = "FhIbqBYEHWDmIgHDufkWFiRccTmu8Lavedlo%2FnccvmChSLG1QY%2Bd0lQoyYX%2B55JyA34U75DrTvcUt5feJRZ9Ng%3D%3D";
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
        setContentView(R.layout.activity_button_style_1);

        text_page = (TextView)findViewById(R.id.text_page);
        test_text = (TextView)findViewById(R.id.test_text);
        // LakuePagingButton 라이브러리 사용

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

        int pageNo = 1;
        int numOfRows = 10;
        int startCreateDt = Integer.parseInt(num3);
        int endCreateDt = Integer.parseInt(num3);
        String _type = "json";

        //서버의 json 응답을 간단하게 변환하도록 해주는 작업
        Retrofit Age_client = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).client(createOkHttpClient()).build();
        //인터페이스
        Age_Interface age_interface = Age_client.create(Age_Interface.class);

        //Call
        Call<Age_Repo> age_repoCall = age_interface.get_age(key,pageNo, numOfRows,startCreateDt, endCreateDt, _type);
        age_repoCall.enqueue(new Callback<Age_Repo>() {
            @Override
            public void onResponse(Call<Age_Repo> call, Response<Age_Repo> response) {
                if(response.isSuccessful()){
                    Age_Repo age_repo = response.body();

                    String gubun = age_repo.getResponse().getBody().getItems().getItem().get(0).getGubun();
                    int confCase = age_repo.getResponse().getBody().getItems().getItem().get(0).confCase;
                    double confCaseRate = age_repo.getResponse().getBody().getItems().getItem().get(0).confCaseRate;
                    int death =  age_repo.getResponse().getBody().getItems().getItem().get(0).getDeath();
                    double criticalRate = age_repo.getResponse().getBody().getItems().getItem().get(0).getCriticalRate();
                    double deathRate = age_repo.getResponse().getBody().getItems().getItem().get(0).getDeathRate();
//
                    test_text.setText(gubun+", "+confCase+", "+confCaseRate+", "+death+", "+criticalRate+", "+deathRate);
//                    test_text.setText(gubun+", "+confCase+", "+confCaseRate);
                }
            }

            @Override
            public void onFailure(Call<Age_Repo> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"코로나 데이터 불러오기 실패",Toast.LENGTH_SHORT).show();
            }
        });


       lpb_buttonlist = (LakuePagingButton)findViewById(R.id.lpb_buttonlist);

        //Number of buttons displayed at one time (Default : 5)
        lpb_buttonlist.setPageItemCount(5);

        //Set the total number of page buttons and the current page
        lpb_buttonlist.addBottomPageButton(max_page,1);


        //Event when we clicked page Listener
        lpb_buttonlist.setOnPageSelectListener(new OnPageSelectListener() {
            //PrevButton Click
            @Override
            public void onPageBefore(int now_page) {
                //When you click the prev button, it resets and draws the button.
                lpb_buttonlist.addBottomPageButton(max_page,now_page);
                text_page.setText(now_page+"Page");
                viewPager2.setCurrentItem(now_page-1);
//                Toast.makeText(getApplicationContext(), ""+now_page, Toast.LENGTH_SHORT).show();
                //Write source code for there page
                //...
            }

            @Override
            public void onPageCenter(int now_page) {
                text_page.setText(now_page+"Page");
                viewPager2.setCurrentItem(now_page-1);
//                Toast.makeText(getApplicationContext(), ""+now_page, Toast.LENGTH_SHORT).show();
                //Write source code for there page
                //...
            }

            //NextButton Click
            @Override
            public void onPageNext(int now_page) {
                //When you click the next button, it resets and draws the button.
                lpb_buttonlist.addBottomPageButton(max_page,now_page);
                text_page.setText(now_page+"Page");
                viewPager2.setCurrentItem(now_page-1);  
//                Toast.makeText(getApplicationContext(), ""+now_page, Toast.LENGTH_SHORT).show();
                //Write source code for there page
                //...
            }
        });

        viewPager2 = findViewById(R.id.pager);
        ArrayList<DataPage> list = new ArrayList<>();
        list.add(new DataPage(android.R.color.black,"1 Page"));
        list.add(new DataPage(android.R.color.holo_red_light, "2 Page"));
        list.add(new DataPage(android.R.color.holo_green_dark, "3 Page"));
        list.add(new DataPage(android.R.color.holo_orange_dark, "4 Page"));
        list.add(new DataPage(android.R.color.holo_blue_light, "5 Page"));
        list.add(new DataPage(android.R.color.holo_blue_bright, "6 Page"));

        viewPager2.setAdapter(new MyViewPagerAdapter(list));
        viewPager2.setCurrentItem(0);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        handler = new Handler(){

            @Override
            public void handleMessage(@NonNull Message msg) {
                if(p==0){
                    viewPager2.setCurrentItem(0);
                    text_page.setText((p+1)+"");
                    p++;
                }
                else if(p==1){
                    viewPager2.setCurrentItem(1);
                    text_page.setText((p+1)+"");
                    p++;
                }
                else if(p==2){
                    viewPager2.setCurrentItem(2);
                    text_page.setText((p+1)+"");    
                    p=0;
                }
            }
        };

        Thread thread = new Thread("My thread"){

            @Override
            public void run() {

                while(true){

                    Message message = handler.obtainMessage();

                    try{
                        handler.sendEmptyMessage(0);
                        Thread.sleep(2000);


                    }
                    catch(Exception e){
                        e.getStackTrace();
                    }


                }

            }

        };
        thread.start();

//        btnToggle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (viewPager2.getOrientation() == ViewPager2.ORIENTATION_VERTICAL) {
//                    btnToggle.setText("가로로 슬라이드");
//                    viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
//                }else {
//                    btnToggle.setText("세로로 슬라이드");
//                    viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
//                }
//            }

    }
}