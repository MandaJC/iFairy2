package com.mandajc.ifairy2;

/**
 * Created by lyzwj on 2018/5/6.
 */

public class MainItem {
    private int img_content, img_user, img_like;
    String title, user_name, like_num;

    public MainItem(int img_content, int img_user, int img_like, String title, String user_name, String like_num){
        this.img_content = img_content;
        this.img_user = img_user;
        this.img_like = img_like;
        this.title = title;
        this.user_name = user_name;
        this.like_num = like_num;
    }

    public int getImg_content() {
        return img_content;
    }

    public int getImg_user() {
        return img_user;
    }

    public int getImg_like() {
        return img_like;
    }

    public String getTitle() {
        return title;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getLike_num() {
        return like_num;
    }

    public void setImg_content(int img_content) {
        this.img_content = img_content;
    }

    public void setImg_user(int img_user) {
        this.img_user = img_user;
    }

    public void setImg_like(int img_like) {
        this.img_like = img_like;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setLike_num(String like_num) {
        this.like_num = like_num;
    }
}
