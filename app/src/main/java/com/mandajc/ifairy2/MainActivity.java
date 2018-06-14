package com.mandajc.ifairy2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Article;

public class MainActivity extends AppCompatActivity{
    public final static int num = 3 ;
    Fragment homeFragment;
    Fragment personFragment;
    Fragment postFragment;
    CardView cardView;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private RadioGroup radioGroup;
    public List<Article> mainItemsList = new ArrayList<>();
    int img_size = 60;
    public String username;

    public String getName() {
        return username;
    }

    int start_mode;//0-自启动，1-LoginActivity启动，2-SearchActivity启动，
    String search_tag;

    //首页和个人页面的逻辑，除了新建文章+之外的逻辑
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        start_mode = intent.getIntExtra("start_mode", 0);
        username = intent.getStringExtra("username");//判断==1
        Toast.makeText(MainActivity.this, "start_mode:"+start_mode, Toast.LENGTH_SHORT).show();
        if(start_mode==2){
            processExtraData();
        }else {
            InitView();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        processExtraData();
    }

    private void processExtraData(){
        Intent intent = getIntent();
        start_mode = getIntent().getIntExtra("start_mode", 0);
        search_tag = getIntent().getStringExtra("tag");//判断==2
        Log.e("NewIntent", "tag: "+ search_tag);
        Log.e("NewIntent", "start_mode: "+ start_mode);
        if(start_mode == 2){
            mainItemsList = (List<Article>) getIntent().getSerializableExtra("data");
            for(int i = 0; i < mainItemsList.size(); i++){
                Log.e("MainActivity:", mainItemsList.get(i).getTitle());
            }
        }
        InitView();
    }

    //search为空=和onCreate一样，搜索全部，Search为tag则传入？
    private void InitView(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){actionBar.hide();}
        cardView = (CardView)findViewById(R.id.cardview);
        fragmentManager = getSupportFragmentManager();
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
        ((RadioButton)radioGroup.findViewById(R.id.radio0)).setChecked(true);
        Drawable drawable=getResources().getDrawable(R.drawable.shouye);
        drawable.setBounds(0,0,img_size,img_size);
        ((RadioButton)radioGroup.findViewById(R.id.radio0)).setCompoundDrawables(null,drawable,null,null);
        drawable=getResources().getDrawable(R.drawable.add1);
        drawable.setBounds(0,0,img_size + 25,img_size + 25);
        ((RadioButton)radioGroup.findViewById(R.id.radio1)).setCompoundDrawables(null,drawable,null,null);
        drawable=getResources().getDrawable(R.drawable.me);
        drawable.setBounds(0,0,img_size,img_size);
        ((RadioButton)radioGroup.findViewById(R.id.radio2)).setCompoundDrawables(null,drawable,null,null);

        InitHome();
//        transaction = fragmentManager.beginTransaction();
//        Fragment fragment = new HomeFragment();
//        Bundle bundle1 = new Bundle();
//        bundle1.putString("username", getName());
//        fragment.setArguments(bundle1);
//        transaction.replace(R.id.content, fragment);
//        transaction.commit();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio0:
                        InitHome();
                        break;
                    case R.id.radio1:
                        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                        intent.putExtra("username", getName());
                        startActivity(intent);
                        break;
                    case R.id.radio2:
                        transaction = fragmentManager.beginTransaction();
                        personFragment = new PersonFragment();
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("username", getName());
                        personFragment.setArguments(bundle2);
                        transaction.replace(R.id.content, personFragment);
                        transaction.commit();
                        break;
                }
            }
        });

    }

    private void InitHome(){
        transaction = fragmentManager.beginTransaction();
        homeFragment = new HomeFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("username", getName());
        bundle1.putInt("start_mode", start_mode);
        Log.e("Main", String.valueOf(start_mode));
        Log.e("main", username );
        if(start_mode == 2){
            bundle1.putString("tag", search_tag);
            bundle1.putSerializable("data", (Serializable)mainItemsList);
        }
        homeFragment.setArguments(bundle1);
        transaction.replace(R.id.content, homeFragment);
        transaction.commit();
    }
}
