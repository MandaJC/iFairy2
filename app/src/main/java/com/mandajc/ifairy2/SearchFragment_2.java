package com.mandajc.ifairy2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonSyntaxException;

import java.io.Serializable;
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


public class SearchFragment_2 extends Fragment {

    @BindView(R.id.main_view1)
    RecyclerView mainItem;
    public MainViewAdapter adapter2;
    public List<Article> mainItemsList = new ArrayList<>();
    private MainActivity mainActivity;
    RequestQueue mQueue;
    Bundle bundle;

    String search_tag;
    public String username;
    int start_mode = 2;//0-自启动，1-LoginActivity启动，2-SearchActivity启动，


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_1, null);
        ButterKnife.bind(this, view);
        ViewCompat.setElevation(view, 50);
        mQueue = Volley.newRequestQueue(getActivity());
        bundle = getArguments();
        search_tag = bundle.getString("tag");
        username = bundle.getString("username");
        Log.e("sear2", String.valueOf(start_mode));
        Log.e("sear2", search_tag);
        initMainItem();
        return view;
    }

    public void initMainItem() {
//        if(start_mode == 1){//登录界面启动
//            StringRequest request = new StringRequest(Request.Method.GET,
//                    HttpPath.get_post_ArticleList(), new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    try {
//                        mainItemsList = GsonUtils.jsonToArrayList(response, Article.class);
//                        Log.e("searchFragment2:", mainItemsList.get(1).getTitle());
//
//                        adapter2 = new MainViewAdapter(mainItemsList, username);
//                        StaggeredGridLayoutManager layoutManager2 = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//                        mainItem.setAdapter(adapter2);
//                        mainItem.setLayoutManager(layoutManager2);
//                    }catch (JsonSyntaxException e){
//                        e.printStackTrace();
//                    }
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                }
//            });
//            mQueue.add(request);
//        }else if(start_mode == 2){//搜索界面启动
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                HttpPath.getTagArticle(),new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("Tag不存在")){
                    Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                }else {
                    mainItemsList = GsonUtils.jsonToArrayList(response, Article.class);
                    for(int i = 0; i < mainItemsList.size(); i++){
                        Log.e("SearchFrag:", mainItemsList.get(i).getTitle());
                    }

                    adapter2 = new MainViewAdapter(mainItemsList, username);
                    StaggeredGridLayoutManager layoutManager2 = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    mainItem.setAdapter(adapter2);
                    mainItem.setLayoutManager(layoutManager2);
                    Log.e("Fragment1:", mainItemsList.get(1).getTitle());
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("searchFrag", "onErrorResponse: "+error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // post 提交 重写参数 ，将自动 提交参数
                Map<String,String> map=new HashMap<String, String>();
                map.put("tag", search_tag);
                return map;
            }
        };
        stringRequest.setTag("strPost");
        mQueue.add(stringRequest);
//        }
    }
}
