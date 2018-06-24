package com.mandajc.ifairy2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import Adapter.ColumnViewAdapter;
import Util.GsonUtils;
import Util.HttpPath;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.Column;
import View.PagerFragment;

/**
 * Created by lyzwj on 2018/5/6.
 */

public class HomeFragment_3 extends PagerFragment {
    @BindView(R.id.main_view3)
    RecyclerView mainItem;
    public ColumnViewAdapter adapter2;
    public List<Column> mainItemsList = new ArrayList<>();
    private MainActivity mainActivity;
    RequestQueue mQueue;
    Bundle bundle;

    public String username;
    int start_mode = 3;//0-自启动，1-LoginActivity启动，2-SearchActivity启动，

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_home_3, null);
        ButterKnife.bind(this, view);
        ViewCompat.setElevation(view, 50);
        mQueue = Volley.newRequestQueue(getActivity());
//        start_mode = bundle.getInt("start_mode", 0);
        Log.e("Home3", String.valueOf(start_mode));
        Log.e("Home3", String.valueOf(username));
        initMainItem();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    public void initMainItem() {
        Log.e("bundleRe3: ", username);
        if(start_mode == 3){//登录界面启动
            StringRequest request = new StringRequest(Request.Method.GET,
                    HttpPath.get_post_ColumnList(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("专栏: ", "成功!");
                    try {
                        mainItemsList = GsonUtils.jsonToArrayList(response, Column.class);
                        if(mainItemsList.size()>0){
                            Log.e("Fragment3:", mainItemsList.get(0).getTitle());
                            adapter2 = new ColumnViewAdapter(mainItemsList, username, getContext());
                            adapter2.setOnLikeClickListener(new ColumnViewAdapter.LikeListener() {
                                @Override
                                public void onLikeClick(int pos) {
                                    checkLike(pos);
                                }
                            });
                            adapter2.setOnDislikeClickListener(new ColumnViewAdapter.DislikeListener() {
                                @Override
                                public void onDislikeClick(int pos) {
                                    checkDislike(pos);
                                }
                            });
                            LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
                            mainItem.setAdapter(adapter2);
                            mainItem.setLayoutManager(layoutManager2);
                        }
                    }catch (JsonSyntaxException e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            mQueue.add(request);
        }
    }

    @Override
    public void reUser(String username) {
        this.username = username;
    };


    public void checkLike(final int pos){
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                HttpPath.isLikeCol(),new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("未点赞")){
                    addLike(pos);
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // post 提交 重写参数 ，将自动 提交参数
                Map<String,String> map=new HashMap<String, String>();
                map.put("columnId",String.valueOf(mainItemsList.get(pos).getId()));
                map.put("title",mainItemsList.get(pos).getTitle());
                map.put("username",mainItemsList.get(pos).getUsername());
                map.put("likeuser",username);
                return map;
            }
        };
        stringRequest.setTag("strPost");
        mQueue.add(stringRequest);
    }

    public void checkDislike(final int pos){
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                HttpPath.isDislike(),new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("未踩")){
                    addDislike(pos);
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // post 提交 重写参数 ，将自动 提交参数
                Map<String,String> map=new HashMap<String, String>();
                map.put("columnId",String.valueOf(mainItemsList.get(pos).getId()));
                map.put("title",mainItemsList.get(pos).getTitle());
                map.put("username",mainItemsList.get(pos).getUsername());
                map.put("dislikeuser",username);
                return map;
            }
        };
        stringRequest.setTag("strPost");
        mQueue.add(stringRequest);
    }

    public void addLike(final int pos){
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                HttpPath.setLikeCol(),new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("种草: ", "成功！");
                if(response.equals("点赞成功")){
                    mainItemsList.get(pos).setLikenum(mainItemsList.get(pos).getLikenum()+1);
                    adapter2.notifyItemChanged(pos);
//                    ((LinearLayoutManager)mainItem.getLayoutManager()).scrollToPositionWithOffset(pos, 0);
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // post 提交 重写参数 ，将自动 提交参数
                Map<String,String> map=new HashMap<String, String>();
                map.put("columnId",String.valueOf(mainItemsList.get(pos).getId()));
                map.put("title",mainItemsList.get(pos).getTitle());
                map.put("username",mainItemsList.get(pos).getUsername());
                map.put("nickname", mainItemsList.get(pos).getNickname());
                map.put("likeuser",username);
                return map;
            }
        };
        stringRequest.setTag("strPost");
        mQueue.add(stringRequest);
    }

    public void addDislike(final int pos){
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                HttpPath.setDislike(),new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("拔草: ", "成功！");
                if(response.equals("踩成功")){
//                    Log.e("onResponse3: ", mainItemsList.get(pos).getLikenum()+"");
                    mainItemsList.get(pos).setDislikenum(mainItemsList.get(pos).getDislikenum()+1);
                    adapter2.notifyItemChanged(pos);
//                    ((LinearLayoutManager)mainItem.getLayoutManager()).scrollToPositionWithOffset(pos, 0);
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // post 提交 重写参数 ，将自动 提交参数
                Map<String,String> map=new HashMap<String, String>();
                map.put("columnId",String.valueOf(mainItemsList.get(pos).getId()));
                map.put("title",mainItemsList.get(pos).getTitle());
                map.put("username",mainItemsList.get(pos).getUsername());
                map.put("nickname", mainItemsList.get(pos).getNickname());
                map.put("dislikeuser",username);
                return map;
            }
        };
        stringRequest.setTag("strPost");
        mQueue.add(stringRequest);
    }

}
