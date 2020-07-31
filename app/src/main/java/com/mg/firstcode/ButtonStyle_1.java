package com.mg.firstcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.lakue.pagingbutton.LakuePagingButton;
import com.lakue.pagingbutton.OnPageSelectListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import ir.androidexception.datatable.DataTable;
import ir.androidexception.datatable.model.DataTableHeader;
import ir.androidexception.datatable.model.DataTableRow;
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

    LinearLayout Layout_1, Layout_2, Layout_3, Layout_4, Layout_5;

    private LineChart lineChart; // 코로나 데이터


    TextView main_text;
    ViewPager2 viewPager2;
    Handler handler;
    int p = 0;
    TextView test_text, location_text, covid_text;
    ImageView covid_image;

    //나이 조사
    private String url = "http://openapi.data.go.kr/";
    private String key = "FhIbqBYEHWDmIgHDufkWFiRccTmu8Lavedlo%2FnccvmChSLG1QY%2Bd0lQoyYX%2B55JyA34U75DrTvcUt5feJRZ9Ng%3D%3D";

    //코로나19 (지역별 확진자수)
    private String location_url = "http://openapi.data.go.kr/";
    private String location_key = "FhIbqBYEHWDmIgHDufkWFiRccTmu8Lavedlo%2FnccvmChSLG1QY%2Bd0lQoyYX%2B55JyA34U75DrTvcUt5feJRZ9Ng%3D%3D";

    //코로나19 (전체 사람들의 수)
    private String covid19_url = "http://openapi.data.go.kr/";
    private String covid19_key = "FhIbqBYEHWDmIgHDufkWFiRccTmu8Lavedlo%2FnccvmChSLG1QY%2Bd0lQoyYX%2B55JyA34U75DrTvcUt5feJRZ9Ng%3D%3D";


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


        test_text = (TextView) findViewById(R.id.test_text);
        main_text = (TextView)findViewById(R.id.main_text);
        Layout_1 = (LinearLayout) findViewById(R.id.Layout_1);
        Layout_2 = (LinearLayout) findViewById(R.id.Layout_2);
        Layout_3 = (LinearLayout) findViewById(R.id.Layout_3);
        Layout_4 = (LinearLayout) findViewById(R.id.Layout_4);
        Layout_5 = (LinearLayout) findViewById(R.id.Layout_5);
        location_text = (TextView) findViewById(R.id.location_text);
        covid_image = (ImageView) findViewById(R.id.covid_image);
        Glide.with(getApplicationContext()).load(R.drawable.coronavirus).fitCenter().into(covid_image);

        // LakuePagingButton 라이브러리 사용

        Calendar cal = Calendar.getInstance();
        final int num = cal.get(Calendar.DAY_OF_WEEK);
//        String today = weekDay[num];
        final int num2 = cal.get(Calendar.DAY_OF_WEEK) + 1;
        final int month = cal.get(Calendar.MONTH) + 1;
        String String_month = "";
        if (month < 10) {
            String_month = "0" + month;
        } else {
            String_month = String.valueOf(month);
        }
        final String num3 = String.valueOf(cal.get(Calendar.YEAR)) + String_month + String.valueOf(cal.get(Calendar.DATE));

        main_text.setText("코로나 관련 데이터 확인\n("+String.valueOf(cal.get(Calendar.YEAR)) +". "+String_month + ". "+String.valueOf(cal.get(Calendar.DATE))+"기준)");


        //-------------------------------------지역코로나----------------------------------
        int pageNo = 1;
        int numOfRows = 10;
        int startCreateDt = Integer.parseInt(num3);
        int endCreateDt = Integer.parseInt(num3);
        String _type = "json";
        //서버의 json 응답을 간단하게 변환하도록 해주는 작업
        Retrofit Lo_client = new Retrofit.Builder().baseUrl(location_url).addConverterFactory(GsonConverterFactory.create()).client(createOkHttpClient()).build();
        //인터페이스
        Location_Interface location_interface = Lo_client.create(Location_Interface.class);

        //Call
        Call<Location_Repo> location_Repo = location_interface.get_Location(location_key, pageNo, numOfRows, startCreateDt, endCreateDt, _type);
        location_Repo.enqueue(new Callback<Location_Repo>() {
            @Override
            public void onResponse(Call<Location_Repo> call, Response<Location_Repo> response) {
                if (response.isSuccessful()) {
                    Location_Repo repo = response.body();
                    int defCnt = repo.getResponse().getBody().getItems().getItem().get(0).getDefCnt();

                    location_text.setText(defCnt + "");

                    DataTable dataTable_location = findViewById(R.id.data_table_location);

                    DataTableHeader header = new DataTableHeader.Builder()
                            .item("시도명", 1)
                            .item("확진자수", 1)
                            .item("격리환자수", 1)
                            .item("격리해제수", 1)
                            .item("지역발생수", 1)
                            .item("해외유입수", 1)
                            .item("사망자수", 1)
                            .build();

                    final ArrayList<DataTableRow> rows = new ArrayList<>();
                    // define 200 fake rows for table
                    for (int i = 0; i < 18; i++) {

                        DataTableRow row = new DataTableRow.Builder()
                                .value(repo.getResponse().getBody().getItems().getItem().get(i).getGubun())
                                .value(String.valueOf(repo.getResponse().getBody().getItems().getItem().get(i).getDefCnt()))
                                .value(String.valueOf(repo.getResponse().getBody().getItems().getItem().get(i).getIsolingCnt()))
                                .value(String.valueOf(repo.getResponse().getBody().getItems().getItem().get(i).getIsolClearCnt()))
                                .value(String.valueOf(repo.getResponse().getBody().getItems().getItem().get(i).localOccCnt))
                                .value(String.valueOf(repo.getResponse().getBody().getItems().getItem().get(i).getOverFlowCnt()))
                                .value(String.valueOf(repo.getResponse().getBody().getItems().getItem().get(i).getDeathCnt()))
                                .build();
                        rows.add(row);
                    }

                    dataTable_location.setHeaderTextSize(10);
                    dataTable_location.setRowTextSize(8);
                    dataTable_location.setHeader(header);
                    dataTable_location.setRows(rows);
                    dataTable_location.inflate(ButtonStyle_1.this);

                }
            }

            @Override
            public void onFailure(Call<Location_Repo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "지역 데이터 불러오기 실패", Toast.LENGTH_SHORT).show();
            }
        });
//-------------------------------------코로나----------------------------------


        //서버의 json 응답을 간단하게 변환하도록 해주는 작업
        Retrofit Co_client = new Retrofit.Builder().baseUrl(covid19_url).addConverterFactory(GsonConverterFactory.create()).client(createOkHttpClient()).build();
        //인터페이스
        Covid19_Interface covid19_interface = Co_client.create(Covid19_Interface.class);

        //Call
        Call<Covid19_Repo> Covid19_Repo = covid19_interface.get_covid19(covid19_key, pageNo, numOfRows, startCreateDt, endCreateDt, _type);
        Covid19_Repo.enqueue(new Callback<com.mg.firstcode.Covid19_Repo>() {
            @Override
            public void onResponse(Call<Covid19_Repo> call, Response<com.mg.firstcode.Covid19_Repo> response) {
                if (response.isSuccessful()) {
                    com.mg.firstcode.Covid19_Repo covid19_Repo = response.body();

                    int decideCnt = covid19_Repo.getResponse().getBody().getItems().getItem().getDecideCnt(); // 확진자수
                    int clearCnt = covid19_Repo.getResponse().getBody().getItems().getItem().getClearCnt(); // 격리 해제수
                    int accExamCnt = covid19_Repo.getResponse().getBody().getItems().getItem().getAccExamCnt(); // 누적검사수
                    int examCnt = covid19_Repo.getResponse().getBody().getItems().getItem().getExamCnt(); // 검사진행수
                    int resutlNegCnt = covid19_Repo.getResponse().getBody().getItems().getItem().getResutlNegCnt(); // 결과음성수
                    int careCnt = covid19_Repo.getResponse().getBody().getItems().getItem().getCareCnt(); // 치료중 환자수
                    int deathCnt = covid19_Repo.getResponse().getBody().getItems().getItem().getDeathCnt(); // 사망자수


                    lineChart = (LineChart) findViewById(R.id.pet_walk_chart);

                    List<Entry> entries = new ArrayList<>();

                    entries.add(new Entry(0, decideCnt));
                    entries.add(new Entry(1, clearCnt));
                    entries.add(new Entry(2, careCnt));
                    entries.add(new Entry(3, deathCnt));


                    LineDataSet pet_lineDataSet = new LineDataSet(entries, "인원수");
                    pet_lineDataSet.setLineWidth(2); // 선 굵기
                    pet_lineDataSet.setCircleRadius(5); // 곡률
                    pet_lineDataSet.setCircleHoleRadius(3);


                    pet_lineDataSet.setCircleColor(Color.parseColor("#FF3995d3")); // 원 칼러
                    pet_lineDataSet.setCircleColorHole(Color.WHITE); // 원안에 가운데 점
                    pet_lineDataSet.setColor(Color.parseColor("#FF3995d3")); // 선 칼라

                    pet_lineDataSet.setDrawCircleHole(true);
                    pet_lineDataSet.setDrawCircles(true);
                    pet_lineDataSet.setDrawHorizontalHighlightIndicator(false);
                    pet_lineDataSet.setDrawHighlightIndicators(false);
                    pet_lineDataSet.setDrawValues(false);

                    LineData pet_lineData = new LineData(pet_lineDataSet);  // 라인 데이터의 텍스트 컬러 / 사이즈를 설정할 수 있다.
                    lineChart.setData(pet_lineData);

                    XAxis xAxis = lineChart.getXAxis();
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setTextColor(Color.BLACK);
                    xAxis.enableGridDashedLine(8, 24, 0);

                    YAxis yLAxis = lineChart.getAxisLeft();  // Y축 위치
                    yLAxis.setTextColor(Color.BLACK); // Y축 텍스트 칼라

                    YAxis yRAxis = lineChart.getAxisRight();
                    yRAxis.setDrawLabels(false);
                    yRAxis.setDrawAxisLine(false);
                    yRAxis.setDrawGridLines(false);


                    yLAxis.setAxisMaximum((float) decideCnt + 1000); // Y축 최대값 지정

                    Description description = new Description();
                    description.setText("");


                    lineChart.setDoubleTapToZoomEnabled(false);
                    lineChart.setDrawGridBackground(false);
                    lineChart.setDescription(description);
                    lineChart.animateY(2000, Easing.EasingOption.EaseInCubic);
                    lineChart.invalidate();

                    String mDays[] = {"확진자수", "격리해제수", "치료중", "사망자수"};

                    MyMarkerView marker = new MyMarkerView(getApplicationContext(), R.layout.marker_view);
                    marker.setChartView(lineChart);
                    lineChart.setMarker(marker);

                    xAxis.setValueFormatter(new GraphAxisValueFormatter(mDays));


                    // 데이터표

                    DataTable dataTable_covid = findViewById(R.id.data_table_covid);

                    DataTableHeader header = new DataTableHeader.Builder()
                            .item("확진자수", 1)
                            .item("격리해제수", 1)
                            .item("누적검사수", 1)
                            .item("검사진행수", 1)
                            .item("결과음성수", 1)
                            .item("치료중환자", 1)
                            .item("사망자수", 1)
                            .build();


                    final ArrayList<DataTableRow> rows = new ArrayList<>();
                    // define 200 fake rows for table


                    DataTableRow row = new DataTableRow.Builder()
                            .value(String.valueOf(decideCnt))
                            .value(String.valueOf(decideCnt))
                            .value(String.valueOf(accExamCnt))
                            .value(String.valueOf(examCnt))
                            .value(String.valueOf(resutlNegCnt))
                            .value(String.valueOf(careCnt))
                            .value(String.valueOf(deathCnt))
                            .build();
                    rows.add(row);


                    //dataTable.setTypeface();
                    dataTable_covid.setHeaderTextSize(10);
                    dataTable_covid.setRowTextSize(8);
                    dataTable_covid.setHeader(header);
                    dataTable_covid.setRows(rows);
                    dataTable_covid.inflate(ButtonStyle_1.this);

                }
            }

            @Override
            public void onFailure(Call<com.mg.firstcode.Covid19_Repo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "코로나 데이터 불러오기 실패", Toast.LENGTH_SHORT).show();
            }
        });


        //----------------------------------- 통신 ------------------------------------------------

        //서버의 json 응답을 간단하게 변환하도록 해주는 작업
        Retrofit Age_client = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).client(createOkHttpClient()).build();
        //인터페이스
        Age_Interface age_interface = Age_client.create(Age_Interface.class);

        //Call
        Call<Age_Repo> age_repoCall = age_interface.get_age(key, pageNo, numOfRows, startCreateDt, endCreateDt, _type);
        age_repoCall.enqueue(new Callback<Age_Repo>() {
            @Override
            public void onResponse(Call<Age_Repo> call, Response<Age_Repo> response) {
                if (response.isSuccessful()) {
                    Age_Repo age_repo = response.body();

                    DataTable dataTable = findViewById(R.id.data_table);

                    DataTableHeader header = new DataTableHeader.Builder()
                            .item("연령", 1)
                            .item("확진자", 1)
                            .item("확진률", 1)
                            .item("사망자", 1)
                            .item("사망률", 1)
                            .item("치명률", 1)
                            .build();


                    final ArrayList<DataTableRow> rows = new ArrayList<>();
                    // define 200 fake rows for table
                    for (int i = 0; i < 11; i++) {

                        DataTableRow row = new DataTableRow.Builder()
                                .value(age_repo.getResponse().getBody().getItems().getItem().get(i).getGubun())
                                .value(String.valueOf(age_repo.getResponse().getBody().getItems().getItem().get(i).getConfCase()))
                                .value(String.valueOf(age_repo.getResponse().getBody().getItems().getItem().get(i).getConfCaseRate()) + "%")
                                .value(String.valueOf(age_repo.getResponse().getBody().getItems().getItem().get(i).getDeath()))
                                .value(String.valueOf(age_repo.getResponse().getBody().getItems().getItem().get(i).getCriticalRate()) + "%")
                                .value(String.valueOf(age_repo.getResponse().getBody().getItems().getItem().get(i).getDeathRate()) + "%")
                                .build();
                        rows.add(row);
                    }

                    //dataTable.setTypeface();
                    dataTable.setHeaderTextSize(10);
                    dataTable.setRowTextSize(8);
                    dataTable.setHeader(header);
                    dataTable.setRows(rows);
                    dataTable.inflate(ButtonStyle_1.this);

//
//                    test_text.setText(gubun+", "+confCase+", "+confCaseRate+", "+death+", "+criticalRate+", "+deathRate);
//                    test_text.setText(gubun+", "+confCase+", "+confCaseRate);
                }
            }

            @Override
            public void onFailure(Call<Age_Repo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "코로나 데이터 불러오기 실패", Toast.LENGTH_SHORT).show();
            }
        });

        // LakuePagingButton 데이터 처리
        lpb_buttonlist = (LakuePagingButton) findViewById(R.id.lpb_buttonlist);
        //Number of buttons displayed at one time (Default : 5)
        lpb_buttonlist.setPageItemCount(5);
        //Set the total number of page buttons and the current page
        lpb_buttonlist.addBottomPageButton(max_page, 1);
        //Event when we clicked page Listener
        lpb_buttonlist.setOnPageSelectListener(new OnPageSelectListener() {
            //PrevButton Click
            @Override
            public void onPageBefore(int now_page) {
                //When you click the prev button, it resets and draws the button.
                lpb_buttonlist.addBottomPageButton(max_page, now_page);
//                text_page.setText(now_page+"Page");
//                Toast.makeText(getApplicationContext(), ""+now_page, Toast.LENGTH_SHORT).show();
                //Write source code for there page
                //...
                pageChange(now_page);
            }

            @Override
            public void onPageCenter(int now_page) {
//                text_page.setText(now_page+"Page");

//                Toast.makeText(getApplicationContext(), ""+now_page, Toast.LENGTH_SHORT).show();
                //Write source code for there page
                //...
                pageChange(now_page);
            }

            //NextButton Click
            @Override
            public void onPageNext(int now_page) {

                //When you click the next button, it resets and draws the button.
                lpb_buttonlist.addBottomPageButton(max_page, now_page);
//                text_page.setText(now_page+"Page");
//                Toast.makeText(getApplicationContext(), ""+now_page, Toast.LENGTH_SHORT).show();
                //Write source code for there page
                pageChange(now_page);

            }
        });

        // 상단 ViewPager Coding
        viewPager2 = findViewById(R.id.pager);
        ArrayList<DataPage> list = new ArrayList<>();
        list.add(new DataPage(R.drawable.coronavirus_2, "마스크 착용하기"));
        list.add(new DataPage(R.drawable.wash_your_hands, "손씻기"));
        list.add(new DataPage(R.drawable.social_distancing, "거리유지하기"));

        viewPager2.setAdapter(new MyViewPagerAdapter(list));
        viewPager2.setCurrentItem(0);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        handler = new Handler() {

            @Override
            public void handleMessage(@NonNull Message msg) {
                if (p == 0) {
                    viewPager2.setCurrentItem(0);
//                    text_page.setText((p+1)+"");
                    p++;
                } else if (p == 1) {
                    viewPager2.setCurrentItem(1);
//                    text_page.setText((p+1)+"");
                    p++;
                } else if (p == 2) {
                    viewPager2.setCurrentItem(2);
//                    text_page.setText((p+1)+"");
                    p = 0;
                }
            }
        };

        Thread thread = new Thread("My thread") {

            @Override
            public void run() {

                while (true) {

                    Message message = handler.obtainMessage();

                    try {
                        handler.sendEmptyMessage(0);
                        Thread.sleep(2000);


                    } catch (Exception e) {
                        e.getStackTrace();
                    }


                }

            }

        };
        thread.start();


    }

    public class GraphAxisValueFormatter implements IAxisValueFormatter {

        private String[] mValues;

        // 생성자 초기화
        GraphAxisValueFormatter(String[] values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int) value];
        }

    }


    public void pageChange(int now_page) {

        if (now_page == 1) {
            Layout_1.setVisibility(View.VISIBLE);
            Layout_2.setVisibility(View.GONE);
            Layout_3.setVisibility(View.GONE);
            Layout_4.setVisibility(View.GONE);
            Layout_5.setVisibility(View.GONE);
        } else if (now_page == 2) {
            Layout_1.setVisibility(View.GONE);
            Layout_2.setVisibility(View.VISIBLE);
            Layout_3.setVisibility(View.GONE);
            Layout_4.setVisibility(View.GONE);
            Layout_5.setVisibility(View.GONE);
        } else if (now_page == 3) {
            Layout_1.setVisibility(View.GONE);
            Layout_2.setVisibility(View.GONE);
            Layout_3.setVisibility(View.VISIBLE);
            Layout_4.setVisibility(View.GONE);
            Layout_5.setVisibility(View.GONE);
        } else if (now_page == 4){
            Layout_1.setVisibility(View.GONE);
            Layout_2.setVisibility(View.GONE);
            Layout_3.setVisibility(View.GONE);
            Layout_4.setVisibility(View.VISIBLE);
            Layout_5.setVisibility(View.GONE);
        }
        else{
            Layout_1.setVisibility(View.GONE);
            Layout_2.setVisibility(View.GONE);
            Layout_3.setVisibility(View.GONE);
            Layout_4.setVisibility(View.GONE);
            Layout_5.setVisibility(View.VISIBLE);
        }
    }

}