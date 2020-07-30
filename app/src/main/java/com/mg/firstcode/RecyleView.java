package com.mg.firstcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyleView extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    Button btn_add, btn_DataAdd;
    ArrayList<FoodInfo> foodInfoArrayList;
    TextView result_userData;
    ImageView result_image;

    public static int imageStatus = 0;

    int ImageArr[] = {R.drawable.baseball, R.drawable.book, R.drawable.cat};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyle_view);

        btn_add = (Button)findViewById(R.id.btn_add);
        btn_DataAdd = (Button)findViewById(R.id.btn_DataAdd);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        result_image = (ImageView)findViewById(R.id.result_image);

        result_userData = (TextView)findViewById(R.id.result_userData);

        foodInfoArrayList = new ArrayList<>();

        // Example Data
        foodInfoArrayList.add(new FoodInfo(R.drawable.book, "임시 데이터"));

        final MyAdapter myAdapter = new MyAdapter(foodInfoArrayList);

        mRecyclerView.setAdapter(myAdapter);

        //데이터 추가버튼
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CustomDialogDataAdd customDialog = new CustomDialogDataAdd(RecyleView.this);
                // 커스텀 다이얼로그를 호출한다.
                // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                customDialog.callFunction(result_userData, result_image);

//                foodInfoArrayList.add(new FoodInfo(R.drawable.book, "8,000원"));
//                myAdapter.notifyDataSetChanged();

            }
        });

        btn_DataAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodInfoArrayList.add(new FoodInfo(ImageArr[imageStatus], result_userData.getText().toString()));
                myAdapter.notifyDataSetChanged();
            }
        });

        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });
    }
}