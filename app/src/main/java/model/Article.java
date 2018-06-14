package model;

import java.io.Serializable;

/**
 * Created by lyzwj on 2018/5/29.
 */

public class Article implements Serializable{
    /**
     * id : 1
     * title : 撒孩子ed
     * content : hiahia
     * likenum : 2
     * collectnum : 2
     * username : mandajc
     * userphoto : /res/images/imgs/a.jpeg
     * photo1 : /res/images/imgs/i3_XPD5SEm.png
     * photo2 : /res/images/imgs/i2_t67dEzW.png
     * photo3 : /res/images/images/q1.png
     */

    private int id;
    private String title;
    private String content;
    private int likenum;
    private int collectnum;
    private String username;
    private String userphoto;
    private String photo1;
    private String photo2;
    private String photo3;
    /**
     * tag : 防晒
     * nickname : 匿名
     */

    private String tag;
    private String nickname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikenum() {
        return likenum;
    }

    public void setLikenum(int likenum) {
        this.likenum = likenum;
    }

    public int getCollectnum() {
        return collectnum;
    }

    public void setCollectnum(int collectnum) {
        this.collectnum = collectnum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphoto() {
        return userphoto;
    }

    public void setUserphoto(String userphoto) {
        this.userphoto = userphoto;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
