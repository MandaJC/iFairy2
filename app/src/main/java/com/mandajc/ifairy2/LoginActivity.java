package com.mandajc.ifairy2;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import Util.HttpPath;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.userPassword)
    EditText userPassword;
    private Button ensureLogin;
    private Button toRegist;
    RequestQueue mQueue;
    String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){actionBar.hide();}
        ButterKnife.bind(this);
        ensureLogin = (Button)findViewById(R.id.ensureLogin);
        toRegist = (Button)findViewById(R.id.toRegist);
        mQueue = Volley.newRequestQueue(this);

        ensureLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestLogin();
            }
        });

        //还没注册，返回注册页面
        toRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void sendRequestLogin() {
        StringRequest request = new StringRequest(Request.Method.POST,
                HttpPath.Login(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // post 提交 重写参数 ，将自动 提交参数
                Map<String,String> map=new HashMap<String, String>();
                map.put("username",userName.getText().toString());
                map.put("password",userPassword.getText().toString());
                return map;
            }
        };
        mQueue.add(request);
    }

    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(response.equals("用户不存在")) {
                    Toast.makeText(LoginActivity.this,"用户不存在！",Toast.LENGTH_SHORT).show();
                }else if(response.equals("登录失败")){
                    Toast.makeText(LoginActivity.this,"密码错误！",Toast.LENGTH_SHORT).show();
                }else if(response.equals("登录成功")){
                    Toast.makeText(LoginActivity.this,"登录成功！",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("start_mode", 1);
                    intent.putExtra("username", userName.getText().toString());
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this,"与服务器失去连接！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//    }
//
//    public void Click(View view) {
//        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//        intent.putExtra("start_mode", 1);
//        intent.putExtra("username", "mandajc666");
//        startActivity(intent);
//    }
}
