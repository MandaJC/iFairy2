package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mandajc.ifairy2.PostActivity;
import com.mandajc.ifairy2.R;

import java.io.Serializable;
import java.util.List;

import Util.HttpPath;
import model.Collect;
import model.Comment;

/**
 * Created by lyzwj on 2018/6/16.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<Comment> mItems;
    private Context mContext;
    String username;
    int start_mode;//0-自启动，1-LoginActivity启动，2-SearchActivity启动，
    public CommentAdapter(List<Comment> items, String username){
        mItems = items;
        this.username = username;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_comment;
        TextView user_comment, content_comment;
        int pos;

        public ViewHolder(View view){
            super(view);
            img_comment = (ImageView)view.findViewById(R.id.img_comment);
            user_comment = (TextView)view.findViewById(R.id.user_comment);
            content_comment = (TextView)view.findViewById(R.id.content_comment);
            int pos = 0;
        }
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.main_item2, parent, false);
        CommentAdapter.ViewHolder holder = new CommentAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        Comment item = mItems.get(position);
        Glide.with(mContext).load(HttpPath.getPic(item.getUserphoto())).into(holder.img_comment);
        holder.user_comment.setText(item.getNickname());
        holder.content_comment.setText(item.getComment());
        holder.pos = position;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void update(List<Comment> itemList){
        mItems = itemList;
        notifyDataSetChanged();
    }
}
