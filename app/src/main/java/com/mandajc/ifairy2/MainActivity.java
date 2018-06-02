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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import Util.GsonUtils;
import Util.HttpPath;
import model.Article;
import model.MainItem;
import volleyHttp.volleyApplication;

public class MainActivity extends AppCompatActivity {

    public final static int num = 3 ;
    Fragment homeFragment;
    Fragment personFragment;
    Fragment sorttypeFragment;
    CardView cardView;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private RadioGroup radioGroup;
    public List<MainItem> mainItemsList = new ArrayList<>();
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
        drawable=getResources().getDrawable(R.drawable.add1);
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




    public List<MainItem> ArticleList(){
        StringRequest request = new StringRequest(Request.Method.GET,
                HttpPath.get_post_ArticleList(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    mainItemsList = GsonUtils.jsonToArrayList(response, MainItem.class);

                }catch (JsonSyntaxException e){

                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        request.setTag("strReqGet");
        volleyApplication.getQueue().add(request);
        return mainItemsList;
    }
}
