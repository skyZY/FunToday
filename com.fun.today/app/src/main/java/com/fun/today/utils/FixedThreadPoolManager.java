package com.fun.today.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolManager
{
	
	private static ExecutorService fixedThreadPool = null;
	private static final String TAG = "FixedThreadPoolManager";
	
	public FixedThreadPoolManager()
	{
	}
	
	private static class FixedThreadPoolManagerHolder
	{
		
		public static FixedThreadPoolManager mManager = new FixedThreadPoolManager();
	}
	
	public static FixedThreadPoolManager getInstance()
	{
		if( null == fixedThreadPool )
		{
			fixedThreadPool = Executors.newFixedThreadPool( 3 );
		}
		return FixedThreadPoolManagerHolder.mManager;
	}
	
	private static List< String > mThreadTaskList = new ArrayList< String >();
	
	public void sumbitRunnable(
			Runnable runnadble,
			String tag )
	{
		LogUtils.i( TAG + " sumbitRunnable mThreadTaskList = " + mThreadTaskList + " tag = " + tag );
		if( null != mThreadTaskList && !mThreadTaskList.contains( tag ) )
		{
			mThreadTaskList.add( tag );
			LogUtils.i( TAG + " sumbitRunnable mThreadTaskList = " + mThreadTaskList );
			fixedThreadPool.execute( runnadble );
		}
	}
	
	public void sumbitRunnable( Runnable runnadble )
	{
		LogUtils.i( TAG + " sumbitRunnable mThreadTaskList = " + mThreadTaskList );
		fixedThreadPool.execute( runnadble );
	}
	
	public void removeRunnable( String tag )
	{
		if( null != mThreadTaskList && mThreadTaskList.contains( tag ) )
		{
			mThreadTaskList.remove( tag );
			LogUtils.i( TAG + " removeRunnable mThreadTaskList = " + mThreadTaskList + " tag = " + tag );
		}
	}
}
