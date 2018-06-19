package com.mandajc.ifairy2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import Adapter.MainViewAdapter;
import Util.GsonUtils;
import Util.HttpPath;
import model.Article;

public class RegisterActivity extends AppCompatActivity {
    private Button ensureRegist;
    private EditText reUserName;
    private EditText reNickName;
    private EditText rePassWord;
    private EditText reEnsurePW;
    private String pass1;
    private String pass2;
    private String name;
    RequestQueue mQueue;
    String TAG = "RegesterActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ensureRegist = (Button)findViewById(R.id.ensureRegist);
        reUserName = (EditText)findViewById(R.id.reUserName);
        rePassWord = (EditText)findViewById(R.id.rePassword);
        reEnsurePW = (EditText)findViewById(R.id.reEnsurePW);
        reNickName = (EditText)findViewById(R.id.reNickName);
        mQueue = Volley.newRequestQueue(this);

        //注册后返回登录界面
        ensureRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestRegister();

            }
        });

    }

    public void sendRequestRegister(){
        StringRequest request = new StringRequest(Request.Method.POST,
                HttpPath.Register(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this,
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // post 提交 重写参数 ，将自动 提交参数
                Map<String,String> map=new HashMap<String, String>();
                map.put("username",reUserName.getText().toString());
                map.put("nickname",reNickName.getText().toString());
                map.put("password",rePassWord.getText().toString());
                return map;
            }
        };
        mQueue.add(request);
    }

    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(response.equals("注册成功")){
                    Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                    Intent it1 = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(it1);
                }else if (response.equals("用户名重复")){
                    Toast.makeText(RegisterActivity.this,"用户名重复！",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(RegisterActivity.this,"与服务器失去连接！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
