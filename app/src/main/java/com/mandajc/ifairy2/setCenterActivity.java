package com.mandajc.ifairy2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class setCenterActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.back_setcenter)
    ImageView back_setcenter;
    @BindView(R.id.tuisong)
    ImageView tuisong;
    @BindView(R.id.tuisongcontent)
    RelativeLayout tuisongcontent;
    @BindView(R.id.privacyset)
    RelativeLayout privacyset;
    @BindView(R.id.aboutUs)
    TextView aboutUs;
    boolean isTuisong = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_center);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){actionBar.hide();}
        ButterKnife.bind(this);
        back_setcenter.setOnClickListener(this);
        tuisong.setOnClickListener(this);
        tuisongcontent.setOnClickListener(this);
        privacyset.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_setcenter:
                finish();
                break;
            case R.id.tuisong:
                if(isTuisong){
                    tuisong.setBackgroundResource(R.drawable.tuisongoff);
                    isTuisong = false;
                }else {
                    tuisong.setBackgroundResource(R.drawable.tuisongon);
                    isTuisong = true;
                }
                break;
            case R.id.tuisongcontent:
                Intent intent = new Intent(setCenterActivity.this, TuisongActivity.class);
                startActivity(intent);
                break;
            case R.id.privacyset:
                Intent intent2 = new Intent(setCenterActivity.this, PrivacyActivity.class);
                startActivity(intent2);
                break;
            case R.id.aboutUs:
                AboutDialog();
        }
    }
    public void AboutDialog(){
        View view = getLayoutInflater().inflate(R.layout.aboutifairy,null);
        AlertDialog dialog = new AlertDialog.Builder(setCenterActivity.this)
                .setIcon(R.drawable.logo)
                .setTitle("关于小仙女")//设置对话框的标题
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create();
        dialog.show();
    }
}
