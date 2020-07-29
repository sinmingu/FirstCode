package com.mg.firstcode;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import static com.mg.firstcode.RecyleView.imageStatus;

public class CustomDialogDataAdd {


    private Context context;

    public CustomDialogDataAdd(Context context) {
        this.context = context;
    }

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction(final TextView main_label, final ImageView main_image) {

        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.customdialog_dataplus);

        // 커스텀 다이얼로그를 노출한다.
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        final EditText message = (EditText) dlg.findViewById(R.id.mesgase);
        final Button okButton = (Button) dlg.findViewById(R.id.okButton);
        final Button cancelButton = (Button) dlg.findViewById(R.id.cancelButton);
        final ImageView firstImage = (ImageView)dlg.findViewById(R.id.image1);
        final ImageView TwoImage = (ImageView)dlg.findViewById(R.id.image2);
        final ImageView ThreeImage = (ImageView)dlg.findViewById(R.id.image3);
        final CheckBox checkBox1 = (CheckBox)dlg.findViewById(R.id.check1);
        final CheckBox checkBox2 = (CheckBox)dlg.findViewById(R.id.check2);
        final CheckBox checkBox3 = (CheckBox)dlg.findViewById(R.id.check3);

        Glide.with(context).load(R.drawable.baseball).into(firstImage);
        Glide.with(context).load(R.drawable.book).fitCenter().into(TwoImage);
        Glide.with(context).load(R.drawable.cat).into(ThreeImage);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // '확인' 버튼 클릭시 메인 액티비티에서 설정한 main_label에
                // 커스텀 다이얼로그에서 입력한 메시지를 대입한다.
                main_label.setText(message.getText().toString());
                Toast.makeText(context, "\"" +  message.getText().toString() + "\" 을 입력하였습니다.", Toast.LENGTH_SHORT).show();
                Glide.with(context).load(R.drawable.cat).fitCenter().into(main_image);
                // 커스텀 다이얼로그를 종료한다.

                if(checkBox1.isChecked() == true){
                    Glide.with(context).load(R.drawable.baseball).fitCenter().into(main_image);
                    imageStatus = 0;
                }
                else if(checkBox2.isChecked() == true){
                    Glide.with(context).load(R.drawable.book).fitCenter().into(main_image);
                    imageStatus = 1;
                }
                else if(checkBox3.isChecked() == true){
                    Glide.with(context).load(R.drawable.cat).fitCenter().into(main_image);
                    imageStatus = 2;
                }

                dlg.dismiss();

            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "취소 했습니다.", Toast.LENGTH_SHORT).show();
                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();

            }
        });

        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);

            }
        });

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox1.setChecked(false);
                checkBox3.setChecked(false);
            }
        });

        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox1.setChecked(false);
                checkBox2.setChecked(false);
            }
        });
    }


}
