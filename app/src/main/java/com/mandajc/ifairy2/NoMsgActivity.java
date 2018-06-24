package com.mandajc.ifairy2;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoMsgActivity extends AppCompatActivity {
    @BindView(R.id.back_everynoti)
    ImageView back_everynoti;
    @BindView(R.id.nomsgtitle)
    TextView nomsgtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_msg);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){actionBar.hide();}
        ButterKnife.bind(this);
        Intent intent = getIntent();
        nomsgtitle.setText(intent.getStringExtra("title"));
        back_everynoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
