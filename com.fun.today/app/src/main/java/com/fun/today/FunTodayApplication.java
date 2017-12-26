package com.fun.today;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.fun.today.utils.Constants;
import com.fun.today.utils.LogUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.game.UMGameAgent;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.update.BmobUpdateAgent;

/**
 * Created by joy on 2017/11/29.
 */

public class FunTodayApplication extends Application
{
	public final static String TAG = "FunTodayApplication";
	/*
	 * Global request queue for Volley
	 */
	private RequestQueue mRequestQueue;
	
	/*
	 * A singleton instance of the application class for easy access in other
	 * places
	 */
	private static FunTodayApplication sInstance;
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		Bmob.initialize( this, Constants.BMOB_APPID );
		BmobUpdateAgent.initAppVersion( this );
		startService( new Intent( this, FunTodayService.class ).setPackage( this.getPackageName() ) );
		Thread.setDefaultUncaughtExceptionHandler( restartHandler );
		sInstance = this;
		initUM( this );
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
	
	public static synchronized FunTodayApplication getsInstance()
	{
		return sInstance;
	}
	
	/**
	 * @return The Volley Request queue, the queue will be created if it is null
	 */
	public RequestQueue getRequestQueue()
	{
		// lazy initialize the request queue, the queue instance will be
		// created when it is accessed for the first time
		if( mRequestQueue == null )
		{
			// 1
			// 2
			synchronized( FunTodayApplication.class )
			{
				if( mRequestQueue == null )
				{
					mRequestQueue = Volley.newRequestQueue( getApplicationContext() );
				}
			}
		}
		return mRequestQueue;
	}
	
	/**
	 * Adds the specified request to the global queue, if tag is specified then
	 * it is used else Default TAG is used.
	 */
	public < T > void addToRequestQueue(
			Request< T > req,
			String tag )
	{
		// set the default tag if tag is empty
		req.setTag( TextUtils.isEmpty( tag ) ? TAG : tag );
		
		VolleyLog.d( "Adding request to queue: %s", req.getUrl() );
		
		getRequestQueue().add( req );
	}
	
	/**
	 * Adds the specified request to the global queue using the Default TAG.
	 */
	public < T > void addToRequestQueue( Request< T > req )
	{
		// set the default tag if tag is empty
		req.setTag( TAG );
		getRequestQueue().add( req );
	}
	
	/**
	 * Cancels all pending requests by the specified TAG, it is important to
	 * specify a TAG so that the pending/ongoing requests can be cancelled.
	 */
	public void cancelPendingRequests( Object tag )
	{
		if( mRequestQueue != null )
		{
			mRequestQueue.cancelAll( tag );
		}
	}
	
	public void initUM( Context context )
	{
		MobclickAgent.onKillProcess( context );
		UMGameAgent.init( context );
		UMGameAgent.setDebugMode( true );//设置debug模式
		MobclickAgent.enableEncrypt( false );//设置日志加密
		MobclickAgent.openActivityDurationTrack( false );
		MobclickAgent.setScenarioType( context, MobclickAgent.EScenarioType.E_UM_NORMAL );
	}
}
