package com.mandajc.ifairy2;

import android.content.Intent;
import android.opengl.ETC1;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.GsonUtils;
import Util.HttpPath;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.Article;
import volleyHttp.volleyApplication;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.edit_search)
    EditText edit_search;
    @BindView(R.id.btn_search)
    Button btn_search;
    @BindView(R.id.back_search)
    Button back_search;
    String username;
//    public List<Article> mainItemsList = new ArrayList<>();
    RequestQueue mQueue;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    Fragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mQueue = Volley.newRequestQueue(this);
        Intent intent=getIntent();
        username = intent.getStringExtra("username");
        btn_search.setOnClickListener(this);
        back_search.setOnClickListener(this);
        InitEdit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_search:
                InitView();
//        postTag();
                break;
            case R.id.back_search:
                finish();
                break;
        }
    }

    private void InitEdit(){
        edit_search.setText("防晒");
        edit_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void InitView() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        searchFragment = new SearchFragment_2();
        Bundle bundle1 = new Bundle();
        bundle1.putString("username", username);
        bundle1.putString("tag", edit_search.getText().toString());
//        bundle1.putSerializable("data", (Serializable)mainItemsList);
        searchFragment.setArguments(bundle1);
        transaction.replace(R.id.search_content, searchFragment);
        transaction.commit();
    }

//    private void postTag(){
//        StringRequest stringRequest=new StringRequest(Request.Method.POST,
//                HttpPath.getTagArticle(),new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if(response.equals("Tag不存在")){
//                    Toast.makeText(SearchActivity.this, response, Toast.LENGTH_SHORT).show();
//                }else {
//                    mainItemsList = GsonUtils.jsonToArrayList(response, Article.class);
//                    for(int i = 0; i < mainItemsList.size(); i++){
//                        Log.e("SearchActivity:", mainItemsList.get(i).getTitle());
//                    }
//                    Intent intent = new Intent(SearchActivity.this, MainActivity.class);
//                    Bundle bundle = new Bundle();
//                    //bundle.putSerializable("redLists", (Serializable) redLists);
//                    intent.putExtra("start_mode", 2);
//                    intent.putExtra("username", username);
//                    intent.putExtra("tag", edit_search.getText().toString());
//                    Log.e("SerachActivity", "onClick: "+edit_search.getText() );
//                    intent.putExtra("data", (Serializable)mainItemsList);
//                    startActivity(intent);
//                }
//            }
//        },new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //³ö´í
//                Toast.makeText(getApplicationContext(),
//                        error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                // post 提交 重写参数 ，将自动 提交参数
//                Map<String,String> map=new HashMap<String, String>();
//                map.put("tag", edit_search.getText().toString());
//                return map;
//            }
//        };
//        stringRequest.setTag("strPost");
//        mQueue.add(stringRequest);
//    }
}
