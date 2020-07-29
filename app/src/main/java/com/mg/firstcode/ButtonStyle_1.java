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

public class ButtonStyle_1 extends AppCompatActivity {

    LakuePagingButton lpb_buttonlist;
    int max_page = 20;
    TextView text_page;
    ViewPager2 viewPager2;
    Handler handler;
    int p = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_style_1);

        text_page = (TextView)findViewById(R.id.text_page);

        // LakuePagingButton 라이브러리 사용

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