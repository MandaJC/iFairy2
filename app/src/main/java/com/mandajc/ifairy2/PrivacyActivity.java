package com.mandajc.ifairy2;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrivacyActivity extends AppCompatActivity {
    @BindView(R.id.allowfans)
    ImageView allowfans;
    @BindView(R.id.back_privacy)
    ImageView back_privacy;
    boolean allowFans=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){actionBar.hide();}
        ButterKnife.bind(this);
        allowfans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(allowFans){
                    allowfans.setBackgroundResource(R.drawable.tuisongoff);
                    allowFans=false;
                }else {
                    allowfans.setBackgroundResource(R.drawable.tuisongon);
                    allowFans=true;
                }
            }
        });
        back_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
