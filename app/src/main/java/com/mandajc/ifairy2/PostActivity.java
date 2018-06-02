package com.mandajc.ifairy2;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PostActivity extends AppCompatActivity {
    ImageView postImg;
    TextView postText;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        postImg = (ImageView)findViewById(R.id.postImg);
        postText = (TextView)findViewById(R.id.postText);
        //postImg.setImageResource(R.drawable.q8);
        Intent intent = getIntent();
        pos = intent.getIntExtra("extra", 1);
        StringBuilder content = new StringBuilder();
        for(int i = 0; i < 10000; i++){
            content.append(pos);
        }
        postText.setText(content.toString());
        Log.e("PostActivity", "onCreate: " + pos);
        //sendRequestWithOkHttp();
    }
    private  void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://172.19.91.196/get0.json").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGSON(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void parseJSONWithGSON(String jsonData){
        Gson gson = new Gson();
        final List<PostItem> appList = gson.fromJson(jsonData, new TypeToken<List<PostItem>>(){}.getType());
        final PostItem app = appList.get(pos);
        Log.e("PostActivity", "parseJSONWithGSON: " + app.getContent());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    postText.setText(app.getContent());
                }
            });
    }
}
