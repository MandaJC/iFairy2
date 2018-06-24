package com.mandajc.ifairy2;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TuisongActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.newfansnoti)
    ImageView newfansnoti;
    @BindView(R.id.collectupdate)
    ImageView collectupdate;
    @BindView(R.id.newversion)
    ImageView newversion;
    @BindView(R.id.back_tuisong)    ImageView back_tuisong;
    boolean isnewfans=true, iscollectupdate=true, isnewversion=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuisong);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){actionBar.hide();}
        ButterKnife.bind(this);
        newfansnoti.setOnClickListener(this);
        collectupdate.setOnClickListener(this);
        newversion.setOnClickListener(this);
        back_tuisong.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newfansnoti:
                if(isnewfans){
                    newfansnoti.setBackgroundResource(R.drawable.tuisongoff);
                    isnewfans=false;
                }else {
                    newfansnoti.setBackgroundResource(R.drawable.tuisongon);
                    isnewfans=true;
                }
                break;
            case R.id.collectupdate:
                if(iscollectupdate){
                    collectupdate.setBackgroundResource(R.drawable.tuisongoff);
                    iscollectupdate=false;
                }else {
                    collectupdate.setBackgroundResource(R.drawable.tuisongon);
                    iscollectupdate=true;
                }
                break;
            case R.id.newversion:
                if(isnewversion){
                    newversion.setBackgroundResource(R.drawable.tuisongoff);
                    isnewversion=false;
                }else {
                    newversion.setBackgroundResource(R.drawable.tuisongon);
                    isnewversion=true;
                }
                break;
            case R.id.back_tuisong:
                finish();
        }
    }
}
