package com.fun.today;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.fun.today.update.FunTodayeUpdateUtils;

/**
 * Created by joy on 2017/12/12.
 */

public class FunTodayService extends Service
{
	private Service mService;
	
	@Override
	public IBinder onBind( Intent intent )
	{
		return null;
	}
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		mService = this;
		
	}
	
	@Override
	public int onStartCommand(
			Intent intent,
			int flags,
			int startId )
	{
		FunTodayeUpdateUtils.checkUpdateApplication( mService );
		return START_NOT_STICKY;
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}
	
}
