package com.mandajc.ifairy2;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.GsonUtils;
import Util.HttpPath;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.Article;
import model.SelfInfo;
import volleyHttp.GsonRequest;

import static android.app.Activity.RESULT_OK;

/**
 * Created by lyzwj on 2018/5/6.
 */

public class PersonFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.headPortrait)
    myImageView headPortrait;
    @BindView(R.id.person_name)
    TextView person_name;
    @BindView(R.id.numOfFans)   TextView numOfFans;
    @BindView(R.id.numOfFollow)  TextView numOfFollow;
    @BindView(R.id.numOfCollection) TextView numOfCollection;
    @BindView(R.id.numOfEssay)  TextView numOfEssay;
    @BindView(R.id.browseCollection)
    Button browseCollection;
    @BindView(R.id.myEssay) Button myEssay;
    @BindView(R.id.set) Button set;
    @BindView(R.id.exit) Button exit;
//    @BindView(R.id.back)    ImageView back;
    Intent intent;
    Bundle bundle;
    public String username;
    RequestQueue mQueue;
    public static final int chooseCode = 22;
    private static final String IMAGE_TYPE = "image/*";
    List<String> paths = new ArrayList<>();
    private EditText editName;
    private ImageView icon;
    private ImageView pen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_person, null);
        icon = (ImageView) view.findViewById(R.id.headPortrait);
        pen = (ImageView)view.findViewById(R.id.changeName);
        ButterKnife.bind(this, view);
        bundle = getArguments();
        username = bundle.getString("username");
        headPortrait.setOnClickListener(this);
        person_name.setOnClickListener(this);
        numOfFans.setOnClickListener(this);
        numOfFollow.setOnClickListener(this);
        numOfCollection.setOnClickListener(this);
        numOfEssay.setOnClickListener(this);
        browseCollection.setOnClickListener(this);
        myEssay.setOnClickListener(this);
        set.setOnClickListener(this);
        exit.setOnClickListener(this);
        pen.setOnClickListener(this);
//        back.setVisibility(View.GONE);
        mQueue = Volley.newRequestQueue(getActivity());
        InitView();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.headPortrait://修改头像
                //ResultActivity的从相册中选图片
                Intent it = new Intent(Intent.ACTION_PICK);
                it.setType("image/*");
                startActivityForResult(it,1);
                break;
            case R.id.changeName://修改昵称
                //CardTest的对话框
                EditNameDialog();
                break;
            case R.id.browseCollection://收藏|浏览
                intent = new Intent(getContext(), CollectActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                break;
            case R.id.myEssay://我的文章
                intent = new Intent(getContext(), MyEssayActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                break;
            case R.id.set://设置中心
                intent = new Intent(getContext(), setCenterActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                break;
            case R.id.exit://退出登录
                intent = new Intent(getContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

    public void EditNameDialog(){
        View view = getLayoutInflater().inflate(R.layout.person_name,null);
        editName = (EditText)view.findViewById(R.id.editName);
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setIcon(icon.getDrawable())
                .setTitle("修改昵称")//设置对话框的标题
                .setView(view)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String content = editName.getText().toString();
                        person_name.setText(content);
                        changeNickname(content);

                    }
                }).create();
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 1){
//            Toast.makeText(getActivity(),data.getData().toString(),Toast.LENGTH_SHORT).show();
            Log.e("PersonFragment",data.getData().toString());
            Cursor cursor = getActivity().getContentResolver().query(data.getData(), null, null, null, null);
            //将cursor指针移动到数据首行
            cursor.moveToFirst();
            //获取字段名为_data的数据
            String imagePath = cursor.getString(cursor.getColumnIndex("_data"));
            headPortrait.setImageURI(data.getData());
            Log.e("onActivityResult: photo", imagePath);
            changePhoto(imagePath);
        }
    }

    public void changePhoto(String imagePath){
        File file = new File(imagePath);
        Log.e("changePhoto: ", file.getAbsolutePath()+" and " +file.getName());
        Log.e("username: ", username);
        // TODO Auto-generated method stub
        HttpUtils http = new HttpUtils();
        com.lidroid.xutils.http.RequestParams params = new com.lidroid.xutils.http.RequestParams("UTF-8");
        params.addBodyParameter("username", username);
        params.addBodyParameter("userphoto", file);
        http.send(HttpRequest.HttpMethod.POST, HttpPath.changeHeadImg(), params,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Log.e("changePhoto error:", arg0.toString()+" "+arg1);
                    }
                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        Toast.makeText(getContext(), arg0.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("changePhoto success:", arg0.result.toString());
                    }
                });

    }

    public void changeNickname(final String content){
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                HttpPath.changeNickName(),new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), "昵称修改成功", Toast.LENGTH_SHORT).show();
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
                map.put("username",username);
                map.put("nickname", content);
                return map;
            }
        };
        stringRequest.setTag("strPost");
        mQueue.add(stringRequest);
    }

    public void InitView(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                HttpPath.Selfinfo(),new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("个人中心:", "成功！");
                final SelfInfo selfInfo;
                selfInfo = GsonUtils.parseJSON(response, SelfInfo.class);
                numOfFans.setText(String.valueOf(selfInfo.getFansnum()));
                numOfFollow.setText(String.valueOf(selfInfo.getFollownum()));
                numOfCollection.setText(String.valueOf(selfInfo.getCollectnum()));
                numOfEssay.setText(String.valueOf(selfInfo.getArticlenum()));
                person_name.setText(String.valueOf(selfInfo.getNickname()));
                Glide.with(getContext())
                        .load(HttpPath.getPic(selfInfo.getHeadimg()))
                        .asBitmap()
                        .listener(new RequestListener<String, Bitmap>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                                Log.e("onException", e+model+target+isFirstResource);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                Log.e("onResourceReady", "model:"+model+" isFirstResource: "+isFirstResource);
                                return false;
                            }
                        })
                        .into(headPortrait);
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
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
        stringRequest.setTag("strPost");
        mQueue.add(stringRequest);
    }

}
