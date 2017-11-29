package com.fun.today;

import android.app.Application;

/**
 * Created by joy on 2017/11/29.
 */

public class FunTodayApplication extends Application
{
	public final static String TAG = "FunTodayApplication";
	/**
	 * SDK初始化也可以放到Application中
	 */
	public static String APPID = "4771879591ed65f2a2cb71ce5cfa00d1";
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		
		//		Bmob.initialize( this, APPID );
		//		BmobUpdateAgent.initAppVersion();
		//		Log.i( "fun", TAG + " onCreate() " );
	}
}
