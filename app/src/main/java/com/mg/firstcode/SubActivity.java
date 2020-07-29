package com.mg.firstcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    TextView text_data1, text_data2, result;
    Button result_data, cal_1, cal_2, cal_3, cal_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        text_data1 = (TextView)findViewById(R.id.text_data1);
        text_data2 = (TextView)findViewById(R.id.text_data2);
        result_data =(Button)findViewById(R.id.result_data);
        cal_1 = (Button)findViewById(R.id.cal_1);
        cal_2 = (Button)findViewById(R.id.cal_2);
        cal_3 = (Button)findViewById(R.id.cal_3);
        cal_4 = (Button)findViewById(R.id.cal_4);
        result = (TextView)findViewById(R.id.result);

        final Intent intent = getIntent();
        text_data1.setText(intent.getStringExtra("FirstData"));
        text_data2.setText(intent.getStringExtra("TwoData"));



        cal_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText((Integer.parseInt(text_data1.getText().toString())+Integer.parseInt(text_data2.getText().toString()))+"");
            }
        });

        cal_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText(Integer.parseInt(text_data1.getText().toString())-Integer.parseInt(text_data2.getText().toString())+"");
            }
        });

        cal_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText(Integer.parseInt(text_data1.getText().toString())*Integer.parseInt(text_data2.getText().toString())+"");
            }
        });

        cal_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText(Integer.parseInt(text_data1.getText().toString())/Integer.parseInt(text_data2.getText().toString())+"");
            }
        });

        result_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                intent.putExtra("data_result",result.getText().toString());
                setResult(-1 , intent);
                finish();

            }
        });


    }
}