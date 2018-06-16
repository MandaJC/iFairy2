package com.mandajc.ifairy2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lyzwj on 2018/5/6.
 */

public class PersonFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.headPortrait)
    ImageView headPortrait;
    @BindView(R.id.person_name)
    TextView person_name;
    @BindView(R.id.numOfFans)   TextView numOfFans;
    @BindView(R.id.numOfAttention)  TextView numOfAttention;
    @BindView(R.id.numOfCollection) TextView numOfCollection;
    @BindView(R.id.numOfEssay)  TextView numOfEssay;
    @BindView(R.id.browseCollection)
    Button browseCollection;
    @BindView(R.id.myEssay) Button myEssay;
    @BindView(R.id.set) Button set;
    @BindView(R.id.exit) Button exit;
    Intent intent;
    Bundle bundle;
    public String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_person, null);
        ButterKnife.bind(this, view);
        bundle = getArguments();
        username = bundle.getString("username");

        headPortrait.setOnClickListener(this);
        person_name.setOnClickListener(this);
        numOfFans.setOnClickListener(this);
        numOfAttention.setOnClickListener(this);
        numOfCollection.setOnClickListener(this);
        numOfEssay.setOnClickListener(this);
        browseCollection.setOnClickListener(this);
        myEssay.setOnClickListener(this);
        set.setOnClickListener(this);
        exit.setOnClickListener(this);

        InitView();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.headPortrait://修改头像
                //ResultActivity的从相册中选图片
                break;
            case R.id.person_name://修改昵称
                //CardTest的对话框
                break;
            case R.id.browseCollection://收藏|浏览
                intent = new Intent(getContext(), CollectActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                break;
            case R.id.myEssay://我的文章
                intent = new Intent(getContext(), MyEssayActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                break;
            case R.id.set://设置中心
//                intent = new Intent(getContext(), )
                intent.putExtra("username", username);
                startActivity(intent);
                break;
            case R.id.exit://退出登录
                intent = new Intent(getContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

    public void InitView(){
        
    }
}
