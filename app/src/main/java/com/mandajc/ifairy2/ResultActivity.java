package com.mandajc.ifairy2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.loopj.android.http.RequestParams;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Util.GsonUtils;
import Util.HttpPath;
import Util.MultiPartStack;
import Util.MultiPartStringRequest;
import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import model.Article;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.relative1)   Button relative1;
    @BindView(R.id.relative2)   Button relative2;
    @BindView(R.id.relative3)   Button relative3;
    @BindView(R.id.relative4)   Button relative4;
    @BindView(R.id.relative5)   Button relative5;
    @BindView(R.id.relative0)   Button relative0;
    @BindView(R.id.tag1)    EditText tag1;
    @BindView(R.id.tag2)    EditText tag2;
    @BindView(R.id.tag3)    EditText tag3;
    @BindColor(R.color.bluegreen)   int bluegreen;
    @BindColor(R.color.blank)   int blank;
    boolean[] isRelative = {false, false, false, false, false, false};
    private ImageView imageView0;
    private ImageView imageView1;
    private ImageView imageView2;

    Button release;
    EditText title, content;

    private Uri imageUri;
    public int id;
    private static final String IMAGE_TYPE = "image/*";
    public static final int TAKE_PHOTO = 1;
    public static final int chooseCode = 22;


    List<String> paths = new ArrayList<>();
    private File[] file;
    static RequestQueue mQueue;
    public String username;
    StringBuilder relativeMsg = new StringBuilder("#");
    String[] relativeTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        Intent intent = getIntent();
        username = intent.getStringExtra("username");//判断==1

        mQueue = Volley.newRequestQueue(this, new MultiPartStack());

        title = (EditText)findViewById(R.id.title);
        content = (EditText)findViewById(R.id.content);
        release = (Button)findViewById(R.id.release);
        release.setOnClickListener(this);

        relativeTip = new String[6];
        relative1.setOnClickListener(this);
        relativeTip[1] = relative1.getText().toString();
        relative2.setOnClickListener(this);
        relativeTip[2] = relative2.getText().toString();
        relative3.setOnClickListener(this);
        relativeTip[3] = relative3.getText().toString();
        relative4.setOnClickListener(this);
        relativeTip[4] = relative4.getText().toString();
        relative5.setOnClickListener(this);
        relativeTip[5] = relative5.getText().toString();
        relative0.setOnClickListener(this);
        relativeTip[0] = relative0.getText().toString();

        imageView0 = (ImageView)findViewById(R.id.picture0);
        imageView1 = (ImageView)findViewById(R.id.picture1);
        imageView2 =(ImageView)findViewById(R.id.picture2);

        imageView0.setOnClickListener(this);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePic();
                id = v.getId();
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePic();
                id = v.getId();
            }
        });
    }


    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.picture0:
                File saveImage = new File(getExternalCacheDir(), "saveImage.jpg");
                try {
                    if (saveImage.exists()) {
                        saveImage.delete();
                    }
                    saveImage.createNewFile();
                    if(paths.size() == 0){
                        paths.add(saveImage.getAbsolutePath());
                    }else {
                        paths.set(0, saveImage.getAbsolutePath());
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUri = FileProvider.getUriForFile(ResultActivity.this, "com.mandajc.ifairy2.fileprovider", saveImage);
                } else {
                    imageUri = Uri.fromFile(saveImage);
                }
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
                break;
            case R.id.relative0:
                if(isRelative[0]){
                    relative0.setBackgroundColor(blank);
                    isRelative[0] = false;
                }else {
                    relative0.setBackgroundColor(bluegreen);
                    isRelative[0] = true;
                }
                break;
            case R.id.relative1:
                if(isRelative[1]){
                    relative1.setBackgroundColor(blank);
                    isRelative[1] = false;
                }else {
                    relative1.setBackgroundColor(bluegreen);
                    isRelative[1] = true;
                }
                break;
            case R.id.relative2:
                if(isRelative[2]){
                    relative2.setBackgroundColor(blank);
                    isRelative[2] = false;
                }else {
                    relative2.setBackgroundColor(bluegreen);
                    isRelative[2] = true;
                }
                break;
            case R.id.relative3:
                if(isRelative[3]){
                    relative3.setBackgroundColor(blank);
                    isRelative[3] = false;
                }else {
                    relative3.setBackgroundColor(bluegreen);
                    isRelative[3] = true;
                }
                break;
            case R.id.relative4:
                if(isRelative[4]){
                    relative4.setBackgroundColor(blank);
                    isRelative[4] = false;
                }else {
                    relative4.setBackgroundColor(bluegreen);
                    isRelative[4] = true;
                }
                break;
            case R.id.relative5:
                if(isRelative[5]){
                    relative5.setBackgroundColor(blank);
                    isRelative[5] = false;
                }else {
                    relative5.setBackgroundColor(bluegreen);
                    isRelative[5] = true;
                }
                break;
            case R.id.release:
                for(int i = 0; i < isRelative.length; i++){
                    if(isRelative[i]){
                        relativeMsg.append(relativeTip[i] + " ");
                    }
                }
                Log.e("onClick: ", relativeMsg.toString());
                showFile();
                showXUtils();
                break;
        }
    }

    public void choosePic(){
        if(ContextCompat.checkSelfPermission(ResultActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ResultActivity.this,new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else{
            openAlbum();
        }
    }
    private void openAlbum(){
        Intent intent0 = new Intent(Intent.ACTION_PICK, null);
        //设置Data和Type属性，前者是URI：表示系统图库的URI,后者是MIME码
        intent0.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_TYPE);
        //启动这个intent所指向的Activity
        startActivityForResult(intent0, chooseCode);
    }

    private void displayImage(String imagePath){
        if(imagePath!=null && id == R.id.picture1){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageView1.setImageBitmap(bitmap);
            if (paths.size() <= 1){
                paths.add(imagePath);
            }else {
                paths.set(1, imagePath);
            }
        }
        else if(imagePath!=null && id == R.id.picture2){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageView2.setImageBitmap(bitmap);
            if (paths.size() <= 2){
                paths.add(imagePath);
            }else {
                paths.set(2, imagePath);
            }
        }
        else
            Toast.makeText(ResultActivity.this,"failed to get image",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String [] permissions,int[] grantResults){
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else{
                    Toast.makeText(this,"You denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        imageView0.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case chooseCode:
                if (resultCode == RESULT_OK) {
                    if (requestCode == chooseCode && data != null) {
                        //相册
                        //通过获取当前应用的contentResolver对象来查询返回的data数据
                        Cursor cursor = this.getContentResolver().query(data.getData(), null, null, null, null);
                        //将cursor指针移动到数据首行
                        cursor.moveToFirst();
                        //获取字段名为_data的数据
                        String imagePath = cursor.getString(cursor.getColumnIndex("_data"));
                        displayImage(imagePath);
                        cursor.close();
                    }
                    break;
                }
            default:
                break;
        }
    }

    private void showFile() {
        // TODO Auto-generated method stub
        switch (paths.size()){
            case 1:
                File file0 = new File(paths.get(0));
                file = new File[] {file0};
                break;
            case 2:
                File file10 = new File(paths.get(0));
                File file1 = new File(paths.get(1));
                file = new File[] {file10, file1};
                break;
            case 3:
                File file20 = new File(paths.get(0));
                File file21 = new File(paths.get(1));
                File file2 = new File(paths.get(2));
                file = new File[] {file20, file21, file2};
                break;
            default:
                break;
        }
//        File file0 = new File(paths.get(0));
//        File file1 = new File(paths.get(1));
//        File file2 = new File(paths.get(2));
//        file = new File[] { file0, file1, file2};
    }

    /****
     * xUtils上传
     *
     */
    private void showXUtils() {
        // TODO Auto-generated method stub
        HttpUtils http = new HttpUtils();
        com.lidroid.xutils.http.RequestParams params = new com.lidroid.xutils.http.RequestParams("UTF-8");
        params.addBodyParameter("username", username);
        params.addBodyParameter("title", title.getText().toString());
        params.addBodyParameter("content", content.getText().toString());
        if(relativeMsg.toString().length()>0){
            params.addBodyParameter("relative", relativeMsg.toString());
        }
//        Log.e("changePhoto: ", file[0].getAbsolutePath()+file[0].getName());
        switch (paths.size()){
            case 1:
                params.addBodyParameter("photo1", file[0]);
                break;
            case 2:
                params.addBodyParameter("photo1", file[0]);
                params.addBodyParameter("photo2", file[1]);
                break;
            case 3:
                params.addBodyParameter("photo1", file[0]);
                params.addBodyParameter("photo2", file[1]);
                params.addBodyParameter("photo3", file[2]);
                break;
            default:
                break;
        }
        String tagone = tag1.getText().toString();
        String tagtwo = tag2.getText().toString();
        String tagthree = tag3.getText().toString();
        if(!tagone.equals("")){
            params.addBodyParameter("tag",tagone);
        }
        if(!tagtwo.equals("")){
            params.addBodyParameter("tag2",tagtwo);
        }
        if(!tagthree.equals("")){
            params.addBodyParameter("tag3",tagthree);
        }
        http.send(com.lidroid.xutils.http.client.HttpRequest.HttpMethod.POST, HttpPath.get_post_ArticleList(), params,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(com.lidroid.xutils.exception.HttpException error, String msg) {
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Log.e("onSuccess: ", responseInfo.result);
                        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                        intent.putExtra("start_mode", 1);
                        intent.putExtra("username", username);
//                        intent.putExtra("relative", relativeMsg.toString());
                        startActivity(intent);
                    }
                });
    }

    /****
     *
     * Volley上传
     *
     * @param files
     * @param params
     * @param responseListener
     * @param errorListener
     */

    public static void addPutUploadFileRequest(final Map<String, File> files,
                                               final Map<String, String> params,
                                               final com.android.volley.Response.Listener<String> responseListener,
                                               final com.android.volley.Response.ErrorListener errorListener) {
//        if (null == url || null == responseListener) {
//            return;
//        }

        MultiPartStringRequest multiPartRequest = new MultiPartStringRequest(
                Request.Method.POST, HttpPath.get_post_ArticleList(), responseListener, errorListener) {

            @Override
            public Map<String, File> getFileUploads() {
                return files;
            }

            @Override
            public Map<String, String> getStringUploads() {
                return params;
            }

        };
        mQueue.add(multiPartRequest);
    }

    com.android.volley.Response.Listener<JSONObject> mResonseListener = new com.android.volley.Response.Listener<JSONObject>() {

        @Override
        public void onResponse(JSONObject response) {
            try {
                Gson gson = new Gson();
                Article article = gson.fromJson(response.toString(), Article.class);
            }catch (JsonSyntaxException e){
                e.printStackTrace();
            }
        }
    };

    com.android.volley.Response.Listener<String> mResonseListenerString = new com.android.volley.Response.Listener<String>() {

        @Override
        public void onResponse(String response) {
            try {
                Gson gson = new Gson();
                Article article = gson.fromJson(response, Article.class);
            }catch (JsonSyntaxException e){
                e.printStackTrace();
            }
        }
    };

    com.android.volley.Response.ErrorListener mErrorListener = new com.android.volley.Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
            if (error != null) {
                if (error.networkResponse != null)
                    Log.i("3", " error "
                            + new String(error.networkResponse.data));
            }
        }
    };
}
