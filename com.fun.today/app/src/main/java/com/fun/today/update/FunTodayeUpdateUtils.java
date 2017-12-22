package com.fun.today.update;

import android.content.Context;
import android.util.Log;

import com.fun.today.db.DBConfig;
import com.fun.today.utils.Constants;
import com.fun.today.utils.DateAndTimeUtils;
import com.fun.today.utils.LogUtils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.BmobUpdateListener;
import cn.bmob.v3.update.BmobUpdateAgent;
import cn.bmob.v3.update.UpdateResponse;
import cn.bmob.v3.update.UpdateStatus;

/**
 * Created by joy on 2017/12/12.
 */

public class FunTodayeUpdateUtils
{
	public final static String TAG = FunTodayeUpdateUtils.class.getName();
	public final static String DB_KEY_CURRENTDATE = "lastUpdateDate";
	
	public static void checkUpdateApplication( Context context )
	{
		try
		{
			String lastUpdateDate = DBConfig.getInstance( context ).getKpshString( DB_KEY_CURRENTDATE );
			String currentDate = DateAndTimeUtils.getStringCurrentDay();
			if( currentDate.equals( lastUpdateDate ) )
			{
				LogUtils.i( TAG + " checkUpdateApplication() has check update " );
				return;
			}
			doBmobUpdate( context );
		}
		catch( Exception e )
		{
			LogUtils.e( TAG + " checkUpdateApplication() exception : " + e.toString() );
		}
	}
	
	private static void doBmobUpdate( Context context )
	{
		try
		{
			//FIXME：验证下面两句是否需要
			Bmob.initialize( context, Constants.BMOB_APPID );
			BmobUpdateAgent.initAppVersion( context );
			
			BmobUpdateAgent.setUpdateOnlyWifi( false );
			BmobUpdateAgent.update( context );
			BmobUpdateAgent.setUpdateListener( new BmobUpdateListener()
			{
				@Override
				public void onUpdateReturned(
						int updateStatus,
						UpdateResponse updateResponse )
				{
					if( updateStatus == UpdateStatus.Yes )
					{
						//版本有更新
						Log.i( "Fun", "MainActivity onCreate() -->>onUpdateReturned() need to update " );
					}
					else if( updateStatus == UpdateStatus.No )
					{
						LogUtils.i( TAG + " doBmobUpdate() 版本无更新" );
					}
					else if( updateStatus == UpdateStatus.ErrorSizeFormat )
					{
						//此提示只是提醒开发者关注那些必填项，测试成功后，无需对用户提示
						LogUtils.i( TAG + " doBmobUpdate() 请检查你AppVersion表的必填项，1、target_size（文件大小）是否填写；2、path或者android_url两者必填其中一项。" );
					}
					else if( updateStatus == UpdateStatus.IGNORED )
					{
						LogUtils.i( TAG + " doBmobUpdate() 该版本已被忽略更新" );
					}
					else if( updateStatus == UpdateStatus.No )
					{
						LogUtils.i( TAG + " doBmobUpdate() 请检查target_size填写的格式，请使用file.length()方法获取apk大小。" );
					}
					else if( updateStatus == UpdateStatus.TimeOut )
					{
						LogUtils.i( TAG + " doBmobUpdate() 查询出错或查询超时" );
					}
					LogUtils.i( TAG + "MainActivity onCreate() -->>onUpdateReturned() updateStatus = " + updateStatus + " ,updateResponse = " + updateResponse.updateLog );
				}
			} );
		}
		catch( Exception e )
		{
			LogUtils.i( TAG + " doBmobUpdate() exception : " + e.toString() );
		}
	}
	
}
