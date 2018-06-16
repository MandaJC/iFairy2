package com.mandajc.ifairy2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.MainViewAdapter;
import Util.GsonUtils;
import Util.HttpPath;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.Article;
import model.Collect;

public class CollectActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.back_essay)
    ImageView back_essay;
    @BindView(R.id.myWhat)
    TextView myWhat;
    @BindView(R.id.myWhatRecycler)
    RecyclerView myWhatRecycler;
    RequestQueue mQueue;
    public MainViewAdapter adapter2;
    public List<Article> mainItemsList = new ArrayList<>();
    Intent intent;//username
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_essay);
        ButterKnife.bind(this);
        mQueue = Volley.newRequestQueue(this);
        back_essay.setOnClickListener(this);
        intent=getIntent();
        username = intent.getStringExtra("username");
        Log.e("MyListonCreate: ", username);
        myWhat.setText("我的收藏");
        InitView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_essay:
                finish();
                break;
        }
    }

    public void InitView(){
        StringRequest request = new StringRequest(Request.Method.POST,
                HttpPath.CollectArticleList(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    mainItemsList = GsonUtils.jsonToArrayList(response, Article.class);
/////////////////////////////////////////////////////////////////////////////////////////////////////可能要换Adapter
                    adapter2 = new MainViewAdapter(mainItemsList, username);
                    StaggeredGridLayoutManager layoutManager2 = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//                    GridLayoutManager layoutManager2 = new GridLayoutManager(MyEssayActivity.this, 2);
                    myWhatRecycler.setAdapter(adapter2);
                    myWhatRecycler.setLayoutManager(layoutManager2);
                    Log.e("Fragment1:", mainItemsList.get(0).getTitle());
                }catch (JsonSyntaxException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CollectActivity.this,
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // post 提交 重写参数 ，将自动 提交参数
                Map<String,String> map=new HashMap<String, String>();
                map.put("collectuser",username);
                return map;
            }
        };
        mQueue.add(request);
    }
}
