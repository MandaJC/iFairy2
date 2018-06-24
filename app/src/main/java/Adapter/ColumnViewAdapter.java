package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.bumptech.glide.Glide;
import com.mandajc.ifairy2.PostActivity;
import com.mandajc.ifairy2.R;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.HttpPath;
import model.Column;

/**
 * Created by lyzwj on 2018/6/14.
 * 专栏Adapter
 */

public class ColumnViewAdapter extends RecyclerView.Adapter<ColumnViewAdapter.ViewHolder> {
    RequestQueue mQueue;
    private List<Column> mItems;
    private Context myContext;
    String username;
    Context mContext;
    int start_mode;//0-自启动，1-LoginActivity启动，2-SearchActivity启动，

    public LikeListener mLikeListener;
    public DislikeListener mDislikeListener;

    public ColumnViewAdapter(List<Column> items, String username, Context context){
        mItems = items;
        this.username = username;
        myContext = context;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_column_author, column_product, column_good, colummn_bad;
        TextView column_author, column_title, good_num, bad_num;
        LinearLayout column_left, column_right;
        int pos;

        public ViewHolder(View view){
            super(view);
            img_column_author = (ImageView)view.findViewById(R.id.img_column_author);
            column_product = (ImageView)view.findViewById(R.id.column_product);
            column_good = (ImageView)view.findViewById(R.id.column_good);
            colummn_bad = (ImageView) view.findViewById(R.id.colummn_bad);

            column_author = (TextView)view.findViewById(R.id.column_author);
            column_title = (TextView)view.findViewById(R.id.column_title);
            good_num = (TextView)view.findViewById(R.id.good_num);
            bad_num = (TextView)view.findViewById(R.id.bad_num);

            column_left = (LinearLayout)view.findViewById(R.id.column_left);
            column_right = (LinearLayout)view.findViewById(R.id.column_right);
            int pos = 0;
        }
    }

    @NonNull
    @Override
    public ColumnViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.main_item3, parent, false);
        final ColumnViewAdapter.ViewHolder holder = new ColumnViewAdapter.ViewHolder(view);
        holder.column_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//在adapter中传入参数
                mLikeListener.onLikeClick(holder.pos);
            }
        });
        holder.column_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//跳转至详细的分享内容，新的activity
                mDislikeListener.onDislikeClick(holder.pos);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ColumnViewAdapter.ViewHolder holder, int position) {
        Column item = mItems.get(position);
        Log.e("onBindViewHoldere: ", item.getPhoto1());
        Glide.with(mContext).load(HttpPath.getPic(item.getPhoto1())).into(holder.column_product);
        Glide.with(mContext).load(HttpPath.getPic(item.getUserphoto())).into(holder.img_column_author);
        holder.column_title.setText(item.getTitle());
        holder.column_author.setText(item.getNickname());
        holder.good_num.setText(String.valueOf(item.getLikenum()));
        holder.bad_num.setText(String.valueOf(item.getDislikenum()));
        holder.pos = position;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public void setOnLikeClickListener (LikeListener  likeListener) {
        this.mLikeListener = likeListener;
    }
    public interface LikeListener {
        public void onLikeClick(int pos);
    }
    public void setOnDislikeClickListener (DislikeListener  dislikeListener) {
        this.mDislikeListener = dislikeListener;
    }
    public interface DislikeListener {
        public void onDislikeClick(int pos);
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
