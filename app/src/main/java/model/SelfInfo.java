package model;

/**
 * Created by lyzwj on 2018/6/19.
 */

public class SelfInfo {
    /**
     * fansnum : 0
     * follownum : 3
     * collectnum : 4
     * articlenum : 3
     * nickname : Equus
     * headimg : images/q1.png
     */

    private int fansnum;
    private int follownum;
    private int collectnum;
    private int articlenum;
    private String nickname;
    private String headimg;

    public int getFansnum() {
        return fansnum;
    }

    public void setFansnum(int fansnum) {
        this.fansnum = fansnum;
    }

    public int getFollownum() {
        return follownum;
    }

    public void setFollownum(int follownum) {
        this.follownum = follownum;
    }

    public int getCollectnum() {
        return collectnum;
    }

    public void setCollectnum(int collectnum) {
        this.collectnum = collectnum;
    }

    public int getArticlenum() {
        return articlenum;
    }

    public void setArticlenum(int articlenum) {
        this.articlenum = articlenum;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }
}
