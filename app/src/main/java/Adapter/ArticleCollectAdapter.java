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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mandajc.ifairy2.PostActivity;
import com.mandajc.ifairy2.R;

import java.io.Serializable;
import java.util.List;

import Util.HttpPath;
import model.Article;
import model.Collect;

/**
 * Created by lyzwj on 2018/6/15.
 */

public class ArticleCollectAdapter extends RecyclerView.Adapter<ArticleCollectAdapter.ViewHolder> {
    private List<Collect> mItems;
    private Context mContext;
    String username;
    int start_mode;//0-自启动，1-LoginActivity启动，2-SearchActivity启动，
    public ArticleCollectAdapter(List<Collect> items, String username){
        mItems = items;
        this.username = username;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_content, img_user, img_like, img_collect;
        TextView title, user_name, like_num, collect_num;
        int pos;

        public ViewHolder(View view){
            super(view);
            img_content = (ImageView)view.findViewById(R.id.main_page1);
            img_user = (ImageView)view.findViewById(R.id.user_photo1);
            img_like = (ImageView)view.findViewById(R.id.like_image);
            title = (TextView)view.findViewById(R.id.title1);
            user_name = (TextView)view.findViewById(R.id.user_name1);
            like_num = (TextView)view.findViewById(R.id.like_num);
            collect_num = (TextView)view.findViewById(R.id.collect_num);
            int pos = 0;
        }
    }

    @NonNull
    @Override
    public ArticleCollectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.main_item1, parent, false);
        final ArticleCollectAdapter.ViewHolder holder = new ArticleCollectAdapter.ViewHolder(view);
        holder.img_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//跳转至详细的分享内容，新的activity
                Intent intent = new Intent(v.getContext(), PostActivity.class);
                intent.putExtra("extra", holder.pos);
                intent.putExtra("id", mItems.get(holder.pos).getId());
                intent.putExtra("article", (Serializable)mItems.get(holder.pos));
                intent.putExtra("username", username);
                Log.e("adapter+username ", username+"");
                Log.e("MainActivityAdapter", "onClick: " + holder.title);
                Log.e("onBindViewHolder", mItems.get(holder.pos).getUsername());
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleCollectAdapter.ViewHolder holder, int position) {
        Collect item = mItems.get(position);
        holder.title.setText(item.getTitle());
        holder.user_name.setText(item.getNickname());
        holder.pos = position;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
