package Util;

public class HttpPath {
	private static final String IP="192.168.199.209:8000";



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



}
