package com.mandajc.ifairy2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import View.PagerFragment;

/**
 * Created by lyzwj on 2018/5/6.
 */

public class HomeFragment_1 extends PagerFragment implements View.OnClickListener{
    @BindView(R.id.main_view1)
    RecyclerView mainItem;
    public MainViewAdapter adapter2;
    public List<Article> mainItemsList = new ArrayList<>();
    private MainActivity mainActivity;
    RequestQueue mQueue;
    String username;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_home_1, null);
        ButterKnife.bind(this, view);
        ViewCompat.setElevation(view, 50);
        mQueue = Volley.newRequestQueue(getActivity());

        initMainItem();
//        search.setOnClickListener(this);
        Log.e("//", "HomeFragment_1");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        mainItemsList.remove(0);
        adapter2.notifyItemRemoved(0);
    }

    /**
     * Called when a fragment is first attached to its context.
     * {@link #onCreate(Bundle)} will be called after this.
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity)context;
    }

    public void initMainItem() {
        Log.e("home1: ", username);
        StringRequest request = new StringRequest(Request.Method.POST,
                HttpPath.FollowUserArticleList(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("获取关注用户文章: ", "成功！");
                try {
                    mainItemsList = GsonUtils.jsonToArrayList(response, Article.class);
                    if(mainItemsList.size()>0){
                        adapter2 = new MainViewAdapter(mainItemsList, username);
                        StaggeredGridLayoutManager layoutManager2 = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                        mainItem.setAdapter(adapter2);
                        mainItem.setLayoutManager(layoutManager2);
                        Log.e("Fragment1:", mainItemsList.get(0).getTitle());
                    }
                }catch (JsonSyntaxException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // post 提交 重写参数 ，将自动 提交参数
                Map<String,String> map=new HashMap<String, String>();
                map.put("username",username);
                return map;
            }
        };
        mQueue.add(request);
    }

    @Override
    public void reBundle(Bundle bundle) {
        this.bundle = bundle;
    };
    @Override
    public void reUser(String username) {
        this.username = username;
    };
    @Override
    public void reData(List<Article> articleList) {
        mainItemsList = articleList;
    };
}

