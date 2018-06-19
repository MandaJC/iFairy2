package model;

/**
 * Created by lyzwj on 2018/6/16.
 */

public class Comment {
    /**
     * id : 2
     * articleId : 13
     * comment : 第一条评论
     * commentuser : mandajc666
     * userphoto : /res/images/images/q1.png
     * nickname : Equus
     * createdate : 2018-06-16T11:22:15.541610Z
     */

    private int id;
    private int articleId;
    private String comment;
    private String commentuser;
    private String userphoto;
    private String nickname;
    private String createdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentuser() {
        return commentuser;
    }

    public void setCommentuser(String commentuser) {
        this.commentuser = commentuser;
    }

    public String getUserphoto() {
        return userphoto;
    }

    public void setUserphoto(String userphoto) {
        this.userphoto = userphoto;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
}
