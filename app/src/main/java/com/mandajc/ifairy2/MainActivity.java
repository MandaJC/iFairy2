package com.mandajc.ifairy2;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    public final static int num = 3 ;
    Fragment homeFragment;
    Fragment personFragment;
    Fragment sorttypeFragment;
    CardView cardView;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private RadioGroup radioGroup;
    int img_size = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){actionBar.hide();}
        cardView = (CardView)findViewById(R.id.cardview);
        fragmentManager = getSupportFragmentManager();
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
        ((RadioButton)radioGroup.findViewById(R.id.radio0)).setChecked(true);
        Drawable drawable=getResources().getDrawable(R.drawable.shouye);
        drawable.setBounds(0,0,img_size,img_size);
        ((RadioButton)radioGroup.findViewById(R.id.radio0)).setCompoundDrawables(null,drawable,null,null);
        drawable=getResources().getDrawable(R.drawable.add);
        drawable.setBounds(0,0,img_size + 25,img_size + 25);
        ((RadioButton)radioGroup.findViewById(R.id.radio1)).setCompoundDrawables(null,drawable,null,null);
        drawable=getResources().getDrawable(R.drawable.me);
        drawable.setBounds(0,0,img_size,img_size);
        ((RadioButton)radioGroup.findViewById(R.id.radio2)).setCompoundDrawables(null,drawable,null,null);

        transaction = fragmentManager.beginTransaction();
        Fragment fragment = new HomeFragment();
        transaction.replace(R.id.content, fragment);
        transaction.commit();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio0:
                        transaction = fragmentManager.beginTransaction();
                        Fragment homeFragment = new HomeFragment();
                        transaction.replace(R.id.content, homeFragment);
                        transaction.commit();
                        break;
                    case R.id.radio1:
                        transaction = fragmentManager.beginTransaction();
                        Fragment sortFragment = new SortFragment();
                        transaction.replace(R.id.content, sortFragment);
                        transaction.commit();
                        break;
                    case R.id.radio2:
                        transaction = fragmentManager.beginTransaction();
                        Fragment personFragment = new PersonFragment();
                        transaction.replace(R.id.content, personFragment);
                        transaction.commit();
                        break;
                }

            }
        });
    }

}
