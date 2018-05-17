package com.mandajc.ifairy2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by lyzwj on 2018/5/6.
 * 这里是首页的RecyclerView，就是瀑布流CardView的部分
 */

public class MainViewAdapter extends RecyclerView.Adapter<MainViewAdapter.ViewHolder> {
    private List<MainItem> mItems;
    private Context mContext;
    public MainViewAdapter(List<MainItem> items){
        mItems = items;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_content, img_user, img_like;
        TextView title, user_name, like_num;
        int pos;

        public ViewHolder(View view){
            super(view);
            img_content = (ImageView)view.findViewById(R.id.main_page1);
            img_user = (ImageView)view.findViewById(R.id.user_photo1);
            img_like = (ImageView)view.findViewById(R.id.like_image);
            title = (TextView)view.findViewById(R.id.title1);
            user_name = (TextView)view.findViewById(R.id.user_name1);
            like_num = (TextView)view.findViewById(R.id.like_num);
            int pos = 0;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.main_item1, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.img_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//跳转至详细的分享内容，新的activity
                Intent intent = new Intent(v.getContext(), PostActivity.class);
                intent.putExtra("extra", holder.pos);
                Log.e("MainActivityAdapter", "onClick: " + holder.pos);
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MainItem item = mItems.get(position);
        Glide.with(mContext).load(item.getImg_content()).into(holder.img_content);
        Glide.with(mContext).load(item.getImg_user()).into(holder.img_user);
        Glide.with(mContext).load(item.getImg_like()).into(holder.img_like);
//        holder.img_content.setImageResource(item.getImg_content());
//        holder.img_user.setImageResource(item.getImg_user());
//        holder.img_like.setImageResource(item.getImg_like());
        holder.title.setText(item.getTitle());
        holder.user_name.setText(item.getUser_name());
        holder.like_num.setText(item.getLike_num());
        holder.pos = position;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
