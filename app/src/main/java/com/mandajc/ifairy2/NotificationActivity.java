package com.mandajc.ifairy2;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.back_notification)
    ImageView back_notification;
    @BindView(R.id.newlikeandcollect)
    RelativeLayout newlikeandcollect;
    @BindView(R.id.newcomments)
    RelativeLayout newcomments;
    @BindView(R.id.newfans)
    RelativeLayout newfans;
    @BindView(R.id.newnotification)
    RelativeLayout newnotification;
    @BindView(R.id.privatemsg)
    RelativeLayout privatemsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){actionBar.hide();}
        ButterKnife.bind(this);
        back_notification.setOnClickListener(this);
        newlikeandcollect.setOnClickListener(this);
        newcomments.setOnClickListener(this);
        newfans.setOnClickListener(this);
        newnotification.setOnClickListener(this);
        privatemsg.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newlikeandcollect:
                Intent intent1 = new Intent(NotificationActivity.this, NoMsgActivity.class);
                intent1.putExtra("title", "点赞和收藏");
                startActivity(intent1);
                break;
            case R.id.newcomments:
                Intent intent2 = new Intent(NotificationActivity.this, NoMsgActivity.class);
                intent2.putExtra("title", "评论");
                startActivity(intent2);
                break;
            case R.id.newfans:
                Intent intent3 = new Intent(NotificationActivity.this, NoMsgActivity.class);
                intent3.putExtra("title", "新的粉丝");
                startActivity(intent3);
                break;
            case R.id.newnotification:
                Intent intent4 = new Intent(NotificationActivity.this, NoMsgActivity.class);
                intent4.putExtra("title", "通知");
                startActivity(intent4);
                break;
            case R.id.privatemsg:
                Intent intent5 = new Intent(NotificationActivity.this, NoMsgActivity.class);
                intent5.putExtra("title", "私信");
                startActivity(intent5);
                break;
            case R.id.back_notification:
                finish();
        }
    }
}
