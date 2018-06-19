package com.mandajc.ifairy2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class setCenterActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.back_setcenter)
    ImageView back_setcenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_center);
        ButterKnife.bind(this);
        back_setcenter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_setcenter:
                finish();
                break;
        }
    }
}
