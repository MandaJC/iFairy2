package model;

/**
 * Created by lyzwj on 2018/6/14.
 */

public class Column {
    /**
     * id : 1
     * title : 小仙女
     * content : 哈哈哈哈或或或或或或或或或或或或或或或或
     * tag : 防晒
     * likenum : 0
     * dislikenum : 0
     * username : mandajc
     * nickname : 钢铁侠
     * userphoto : /res/images/images/q1.png
     * photo1 : /res/images/images/q1.png
     */

    private int id;
    private String title;
    private String content;
    private String tag;
    private int likenum;
    private int dislikenum;
    private String username;
    private String nickname;
    private String userphoto;
    private String photo1;

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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getLikenum() {
        return likenum;
    }

    public void setLikenum(int likenum) {
        this.likenum = likenum;
    }

    public int getDislikenum() {
        return dislikenum;
    }

    public void setDislikenum(int dislikenum) {
        this.dislikenum = dislikenum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
}
