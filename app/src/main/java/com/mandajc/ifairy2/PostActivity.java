package com.mandajc.ifairy2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import Adapter.MyPagerAdapter;
import Adapter.MyViewPager;
import Util.HttpPath;
import Util.ScreenUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.Article;
import volleyHttp.GsonRequest;
import volleyHttp.volleyApplication;

public class PostActivity extends AppCompatActivity implements View.OnClickListener{
    RequestQueue mQueue;
    @BindView(R.id.postText)    TextView postText;
    @BindView(R.id.postTitle)    TextView postTitle;
    @BindView(R.id.pagenum)    TextView pageNum;
    @BindView(R.id.img_like)    ImageView imgLike;
    @BindView(R.id.img_collect) ImageView imgCollect;
    @BindView(R.id.text_like)   TextView textLike;
    @BindView(R.id.text_collect)    TextView textCollect;
    @BindView(R.id.btn_left)    LinearLayout btnLeft;
    @BindView(R.id.btn_right)    LinearLayout btnRight;
    @BindView(R.id.back_article)    Button back;
    Article article;
    int pos, id;
    String username;
    boolean addedlike = false, addedcollect = false;

    private ViewPager mViewpager;
    private int[] imgheights;
    private int screenWidth;
    private List<String> urlList = new ArrayList<>();

    private MyPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){actionBar.hide();}
        screenWidth= ScreenUtil.getScreenWidth(this);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        pos = intent.getIntExtra("extra", 1);
        id = intent.getIntExtra("id", 1);
        username = intent.getStringExtra("username");
        article = (Article) getIntent().getSerializableExtra("article");
        Log.e("id", article.getId()+"");
        Log.e("todo username", username+"");
        Log.e("username", article.getUsername()+"");
        postTitle.setText(article.getTitle());
        postText.setText(article.getContent());
        textLike.setText(String.valueOf(article.getLikenum()));
        textCollect.setText(String.valueOf(article.getCollectnum()));
        mQueue = Volley.newRequestQueue(this);
        checkLike(0);
        checkCollect(0);
        InitUrl();
        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_left:
                checkLike(1);
                break;
            case R.id.btn_right:
                checkCollect(1);
                break;
            case R.id.back_article:
                finish();
//                if(addedcollect || addedlike){
//                    sendIntent();
//                }else {
//                    finish();
//                }
                break;
        }
    }


    private void InitUrl(){
        urlList.add(HttpPath.getPic(article.getPhoto1()));
        urlList.add(HttpPath.getPic(article.getPhoto2()));
        urlList.add(HttpPath.getPic(article.getPhoto3()));
        Log.e("InitUrl()", urlList.get(0));
        Log.e("InitUrl()", HttpPath.getPic(article.getPhoto1()));
        initView();
    }

    private void initView(){
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mViewpager.setOffscreenPageLimit(3);
        pageNum.setText(1 + "/" + urlList.size());
        Glide.with(this)
                .load(urlList.get(0))
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        float scale = (float) resource.getHeight() / resource.getWidth();
                        int defaultheight = (int) (scale * screenWidth);
                        initViewPager(defaultheight);
                    }
                });
    }


    //获取第一张图片高度后，给viewpager设置adapter
    private void initViewPager(final int defaultheight) {
        mViewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                if (imgheights == null || imgheights.length != urlList.size()){
                    imgheights = null;
                    imgheights = new int[urlList.size()];}
                return urlList.size();
            }


            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                final ImageView imageView = new ImageView(PostActivity.this);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Glide.with(getApplicationContext())
                        .load(urlList.get(position))
                        .asBitmap()
//                        .listener(mRequestListener)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                if(resource!=null) {
                                    float scale = (float) resource.getHeight() / resource.getWidth();
                                    imgheights[position] = (int) (scale * screenWidth);
                                    imageView.setImageBitmap(resource);
                                }else {
                                    Toast.makeText(PostActivity.this, "图片为空", Toast.LENGTH_LONG).show();
                                }
                            }

//                            @Override
//                            protected void setResource(Bitmap loadedImage) {
//                                if(loadedImage!=null) {
//                                    float scale = (float) loadedImage.getHeight() / loadedImage.getWidth();
//                                    imgheights[position] = (int) (scale * screenWidth);
//                                    imageView.setImageBitmap(loadedImage);
//                                }else {
//                                    Toast.makeText(PostActivity.this, "图片为空", Toast.LENGTH_LONG).show();
//                                }
//                            }
                        });
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });


        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                pageNum.setText(String.valueOf(position + 1) + "/" + urlList.size());
                if (position == imgheights.length - 1) {
                    return;
                }

                //计算ViewPager现在应该的高度,heights[]表示页面高度的数组。
                int height = (int) ((imgheights[position] == 0 ? defaultheight : imgheights[position])
                        * (1 - positionOffset) +
                        (imgheights[position + 1] == 0 ? defaultheight : imgheights[position + 1])
                                * positionOffset);

                //为ViewPager设置高度
                ViewGroup.LayoutParams params = mViewpager.getLayoutParams();
                params.height = height;
                mViewpager.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
//                pageNum.setText(position + "/" + urlList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void checkLike(final int type){
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                HttpPath.isLike(),new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(type == 0){//只check isLike
                    if(response.equals("已点赞")){
                        Glide.with(PostActivity.this).load(R.drawable.liked).into(imgLike);
//                        imgLike.setImageResource(R.drawable.liked);
                    }else {//未点赞
                        Glide.with(PostActivity.this).load(R.drawable.unlike).into(imgLike);
//                        imgLike.setImageResource(R.drawable.unlike);
                    }
                }else if(type == 1){//check完，若未like则setLike，已点赞则不动作
                    if(response.equals("未点赞")){
                        addLike();
                    }
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // post 提交 重写参数 ，将自动 提交参数
                Map<String,String> map=new HashMap<String, String>();
                map.put("articleId",String.valueOf(article.getId()));
                map.put("title",article.getTitle());
                map.put("username",article.getUsername());
                map.put("likeuser",username);
                return map;
            }
        };
        stringRequest.setTag("strPost");
        mQueue.add(stringRequest);
    }

    public void checkCollect(final int type){
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                HttpPath.isCollect(),new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(type == 0){//只check isCollect
                    if(response.equals("已收藏")){
                        Glide.with(PostActivity.this).load(R.drawable.collect).into(imgCollect);
//                        imgLike.setImageResource(R.drawable.liked);
                    }else {//未收藏
                        Glide.with(PostActivity.this).load(R.drawable.uncollect).into(imgCollect);
//                        imgLike.setImageResource(R.drawable.unlike);
                    }
                }else if(type == 1){//check完，若未collect则setCollect，已收藏则不动作
                    if(response.equals("未收藏")){
                        addCollect();
                    }
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // post 提交 重写参数 ，将自动 提交参数
                Map<String,String> map=new HashMap<String, String>();
                map.put("articleId",String.valueOf(article.getId()));
                map.put("title",article.getTitle());
                map.put("username",article.getUsername());
                map.put("collectuser",username);
                return map;
            }
        };
        stringRequest.setTag("strPost");
        mQueue.add(stringRequest);
    }

    public void addLike(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                HttpPath.setLike(),new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("点赞成功")){
                    Glide.with(PostActivity.this).load(R.drawable.liked).into(imgLike);
//                        imgLike.setImageResource(R.drawable.liked);
                    textLike.setText(String.valueOf(Integer.valueOf(textLike.getText().toString())+1));
//                    sendIntent();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // post 提交 重写参数 ，将自动 提交参数
                Map<String,String> map=new HashMap<String, String>();
                map.put("articleId",String.valueOf(article.getId()));
                map.put("title",article.getTitle());
                map.put("username",article.getUsername());
                map.put("likeuser",username);
                return map;
            }
        };
        stringRequest.setTag("strPost");
        mQueue.add(stringRequest);
    }

    public void addCollect(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                HttpPath.setCollect(),new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("收藏成功")){
                    Glide.with(PostActivity.this).load(R.drawable.collect).into(imgCollect);
//                        imgLike.setImageResource(R.drawable.liked);
                    textCollect.setText(String.valueOf(Integer.valueOf(textCollect.getText().toString())+1));
//                    sendIntent();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // post 提交 重写参数 ，将自动 提交参数
                Map<String,String> map=new HashMap<String, String>();
                map.put("articleId",String.valueOf(article.getId()));
                map.put("title",article.getTitle());
                map.put("username",article.getUsername());
                map.put("collectuser",username);
                return map;
            }
        };
        stringRequest.setTag("strPost");
        mQueue.add(stringRequest);
    }

    public void sendIntent(){
        Intent intent = new Intent(PostActivity.this, MainActivity.class);
        intent.putExtra("articleId",article.getId());
        startActivity(intent);
    }
}

