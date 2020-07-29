package com.mg.firstcode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.lakue.pagingbutton.LakuePagingButton;
import com.lakue.pagingbutton.OnPageSelectListener;

public class ButtonStyle_1 extends AppCompatActivity {

    LakuePagingButton lpb_buttonlist;
    int max_page = 20;
    TextView text_page;

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
//                Toast.makeText(getApplicationContext(), ""+now_page, Toast.LENGTH_SHORT).show();
                //Write source code for there page
                //...
            }

            @Override
            public void onPageCenter(int now_page) {
                text_page.setText(now_page+"Page");
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
//                Toast.makeText(getApplicationContext(), ""+now_page, Toast.LENGTH_SHORT).show();
                //Write source code for there page
                //...
            }
        });



    }
}