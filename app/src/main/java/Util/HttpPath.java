package Util;

public class HttpPath {
//    private static final String IP="192.168.199.209:8000";
//	private static final String IP="172.19.89.112:8000";
	private static final String IP="mandajc.xicp.io:17881";
    private static final String qnyIP="papfvr9g2.bkt.clouddn.com";
    public static String get_post_ArticleList(){
        return "http://"+IP+"/Article/list/";
    }
    public static String getArticleItem(int id){
        return "http://"+IP+"/Article/detail/"+id;
    }
    public static String getPic(String imgAddr){
        return imgAddr;
    }
    public static String getcenterPic(String imgAddr){
        return imgAddr;//"http://"+qnyIP+"/res/images/"+imgAddr;
    }

    public static String Register() {
        return "http://" + IP + "/regNlog/register/";
    }//注册
    public static String Login() {
        return "http://" + IP + "/regNlog/login/";
    }//登录

    public static String changeNickName() {
        return "http://" + IP + "/regNlog/nickname/";
    }

    public static String changePassword() {
        return "http://" + IP + "/regNlog/password/";
    }

    public static String changeHeadImg() {
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

    public static String MyEssayList() {
        return "http://" + IP + "/Article/myarticlelist/";
    }

    public static String CollectArticleList() {
        return "http://" + IP + "/Article/collectarticlelist/";
    }
    public static String CommentPost() {
        return "http://" + IP + "/Article/commentpost/";
    }
    public static String CommentList() {
        return "http://" + IP + "/Article/commentlist/";
    }

    public static String SetFollow() {
        return "http://" + IP + "/Article/setfollow/";
    }
    public static String FollowUserArticleList() {
        return "http://" + IP + "/Article/followuserarticlelist/";
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

    public static String Selfinfo() {
        return "http://" + IP + "/Article/selfinfo/";
    }

}
