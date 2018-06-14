package Util;

public class HttpPath {
//    private static final String IP="172.19.105.88:8000";
	private static final String IP="192.168.199.209:8000";
//	private static final String IP="mandajc.xicp.io:17881";

    public static String get_post_ArticleList(){
        return "http://"+IP+"/Article/list/";
    }
    public static String getArticleItem(int id){
        return "http://"+IP+"/Article/detail/"+id;
    }
    public static String getPic(String imgAddr){
        return "http://"+IP+imgAddr;
    }

    public static String changeNickName() {
        return "http://" + IP + "/regNlog/nickname/";
    }

    public static String changePassword() {
        return "http://" + IP + "/regNlog/password/";
    }

    public static String changeHeadImg(int id) {
        return "http://" + IP + "/regNlog/headimg/";
    }

    public static String getTagArticle() {
        return "http://" + IP + "/Article/tag/";
    }

    public static String isLike() {
        return "http://" + IP + "/Article/islike/";
    }
    public static String isCollect() {
        return "http://" + IP + "/Article/iscollect/";
    }
    public static String setLike() {
        return "http://" + IP + "/Article/setlike/";
    }
    public static String setCollect() {
        return "http://" + IP + "/Article/setcollect/";
    }

    public static String get_post_ColumnList(){
        return "http://"+IP+"/Column/list/";
    }
    public static String isLikeCol() {
        return "http://" + IP + "/Column/islike/";
    }
    public static String isDislike() {
        return "http://" + IP + "/Column/isdislike/";
    }
    public static String setLikeCol() {
        return "http://" + IP + "/Column/setlike/";
    }
    public static String setDislike() {
        return "http://" + IP + "/Column/setdislike/";
    }
}
