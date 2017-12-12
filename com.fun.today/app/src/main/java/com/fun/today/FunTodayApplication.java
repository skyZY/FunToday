package com.fun.today;

import android.app.Application;
import android.content.Intent;

import com.fun.today.utils.Constants;
import com.fun.today.utils.LogUtils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.update.BmobUpdateAgent;

/**
 * Created by joy on 2017/11/29.
 */

public class FunTodayApplication extends Application
{
	public final static String TAG = "FunTodayApplication";
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		Bmob.initialize( this, Constants.BMOB_APPID );
		BmobUpdateAgent.initAppVersion();
		startService( new Intent( this, FunTodayService.class ).setPackage( this.getPackageName() ) );
		//		Thread.setDefaultUncaughtExceptionHandler( restartHandler );
	}
	
	// 创建服务用于捕获崩溃异常
	private Thread.UncaughtExceptionHandler restartHandler = new Thread.UncaughtExceptionHandler()
	{
		
		public void uncaughtException(
				Thread thread,
				Throwable ex )
		{
			LogUtils.i( " FunTodayApplication UncaughtExceptionHandler to restart app " );
			LogUtils.i( " FunTodayApplication UncaughtExceptionHandler exception : " + ex.toString() );
			ex.printStackTrace();
		}
	};
}
