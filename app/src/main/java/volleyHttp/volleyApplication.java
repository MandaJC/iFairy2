package volleyHttp;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class volleyApplication extends Application {
	/**
	 * 01. 建立  请求队列
	 * 02. 将 请求队列(application_name和网络权限) 加入到 AndroidMain.xml中
	 * 03.
	 */
	private static RequestQueue queue;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		queue=Volley.newRequestQueue(getApplicationContext());
	}
	

	public static RequestQueue getQueue(){
		return queue;
	}
	
	
}
