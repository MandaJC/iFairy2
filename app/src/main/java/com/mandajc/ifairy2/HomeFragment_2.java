package com.mandajc.ifairy2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.mandajc.ifairy2.MainActivity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import Util.GsonUtils;
import Util.HttpPath;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.Article;
import model.MainItem;
import volleyHttp.volleyApplication;

/**
 * Created by lyzwj on 2018/5/6.
 */

public class HomeFragment_2 extends Fragment implements View.OnClickListener{


    @BindView(R.id.main_view1)
    RecyclerView mainItem;
    public MainViewAdapter adapter2;
    public List<MainItem> mainItemsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_home_1, null);
        ButterKnife.bind(this, view);
        ViewCompat.setElevation(view, 50);
//        initMainItem();
//        adapter2 = new MainViewAdapter(mainItemsList);
//        StaggeredGridLayoutManager layoutManager2 = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        mainItem.setAdapter(adapter2);
//        mainItem.setLayoutManager(layoutManager2);


////        search.setOnClickListener(this);
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


    private  void initMainItem(){
//        mainItemsList.add(new MainItem(R.drawable.q0, R.drawable.liai, R.drawable.unlike,
//                "黛珂怎么选？混痘油？空瓶经验分享", "人人都是小仙女", "0"));
//        mainItemsList.add(new MainItem(R.drawable.q1, R.drawable.liai, R.drawable.unlike,
//                "黛珂怎么选？混痘油？空瓶经验分享", "人人都是小仙女", "0"));
//        mainItemsList.add(new MainItem(R.drawable.q2, R.drawable.liai, R.drawable.unlike,
//                "黛珂怎么选？混痘油？空瓶经验分享", "人人都是小仙女", "0"));
//        mainItemsList.add(new MainItem(R.drawable.q3, R.drawable.liai, R.drawable.unlike,
//                "黛珂怎么选？混痘油？空瓶经验分享", "人人都是小仙女", "0"));
//        mainItemsList.add(new MainItem(R.drawable.q4, R.drawable.liai, R.drawable.unlike,
//                "黛珂怎么选？混痘油？空瓶经验分享", "人人都是小仙女", "0"));
//        mainItemsList.add(new MainItem(R.drawable.q5, R.drawable.liai, R.drawable.unlike,
//                "黛珂怎么选？混痘油？空瓶经验分享", "人人都是小仙女", "0"));
//        mainItemsList.add(new MainItem(R.drawable.q6, R.drawable.liai, R.drawable.unlike,
//                "黛珂怎么选？混痘油？空瓶经验分享", "人人都是小仙女", "0"));
//        mainItemsList.add(new MainItem(R.drawable.q7, R.drawable.liai, R.drawable.unlike,
//                "黛珂怎么选？混痘油？空瓶经验分享", "人人都是小仙女", "0"));
//        mainItemsList.add(new MainItem(R.drawable.q8, R.drawable.liai, R.drawable.unlike,
//                "黛珂怎么选？混痘油？空瓶经验分享", "人人都是小仙女", "0"));
//        mainItemsList.add(new MainItem(R.drawable.q9, R.drawable.liai, R.drawable.unlike,
//                "黛珂怎么选？混痘油？空瓶经验分享", "人人都是小仙女", "0"));
    }
}