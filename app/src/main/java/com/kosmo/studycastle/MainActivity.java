package com.kosmo.studycastle;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    //전역변수
    Button all,entrance_examination,physical_education,etc;
    Intent intent,intent2;
    BoomMenuButton bmb;

    //상단 그라데이션
    ImageView frontActivityBackground = null;
    ImageView uzb = null;
    AnimationDrawable frameAnimation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frontActivityBackground = (ImageView)findViewById(R.id.frontActivityBackground);
        frontActivityBackground.setBackgroundResource(R.drawable.transition);

        frameAnimation = (AnimationDrawable) frontActivityBackground.getBackground();
        frameAnimation .setEnterFadeDuration(1000);
        frameAnimation .setExitFadeDuration(1000);


        frontActivityBackground.postDelayed(new Runnable() {
            public void run() {
                frameAnimation.start();
            }
        }, 200);

        //붐메뉴적용
        bmb = (BoomMenuButton)findViewById(R.id.bmb);
        bmb.setButtonEnum(ButtonEnum.TextInsideCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_2_2);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_2_1);
        for (int i = 0; i < bmb.getButtonPlaceEnum().buttonNumber(); i++) {
            /*
            bmb.addBuilder(new SimpleCircleButton.Builder()
                    .normalImageRes(R.drawable.study_castle));
            */
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder();
            if(i==0){
                builder.normalImageRes(R.drawable.my)
                        .normalText("마이페이지")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                intent2 = new Intent(getApplicationContext(),MyPage.class);

                                startActivity(intent2);
                            }
                        });
            }
            else {
                builder.normalImageRes(R.drawable.icon_home)
                        .normalText("홈으로")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                intent2 = new Intent(getApplicationContext(),MainActivity.class);

                                startActivity(intent2);
                            }
                        });
            }

            bmb.addBuilder(builder);

        }

        all=(Button)findViewById(R.id.all);
        entrance_examination=(Button)findViewById(R.id.entrance_examination);
        physical_education=(Button)findViewById(R.id.physical_education);
        etc=(Button)findViewById(R.id.etc);

        //전체선택시
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(),AcademyList.class);
                intent.putExtra("button_name",all.getText().toString());

                startActivity(intent);
            }
        });

        //입시선택시
        entrance_examination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(),AcademyList.class);
                intent.putExtra("button_name",entrance_examination.getText().toString());

                startActivity(intent);
            }
        });
        //예체능선택시
        physical_education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(),AcademyList.class);
                intent.putExtra("button_name",physical_education.getText().toString());

                startActivity(intent);
            }
        });
        //기타 선택시
        etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(),AcademyList.class);
                intent.putExtra("button_name",etc.getText().toString());

                startActivity(intent);
            }
        });

        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.kosmo.studycastle", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
