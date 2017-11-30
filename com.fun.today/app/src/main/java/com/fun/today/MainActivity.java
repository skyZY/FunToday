package com.fun.today;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.BmobUpdateListener;
import cn.bmob.v3.update.BmobUpdateAgent;
import cn.bmob.v3.update.UpdateResponse;
import cn.bmob.v3.update.UpdateStatus;

public class MainActivity extends AppCompatActivity
{

	Context mContext;
	public static String APPID = "4771879591ed65f2a2cb71ce5cfa00d1";
	public final static String TAG = "FunTodayApplication";
	
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		mContext = this;
		//		startActivity( new Intent( this, CalendarActivity.class ) );
		
		Bmob.initialize( this, APPID );
		BmobUpdateAgent.initAppVersion();
		Log.i( "fun", TAG + " onCreate() " );
		BmobUpdateAgent.setUpdateOnlyWifi( false );
		//		BmobUpdateAgent.update( this );
		
		Log.i( "Fun", this.getLocalClassName() + " onCreate()" );
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
					Toast.makeText( MainActivity.this, "版本无更新", Toast.LENGTH_SHORT ).show();
				}
				else if( updateStatus == UpdateStatus.EmptyField )
				{
					//此提示只是提醒开发者关注那些必填项，测试成功后，无需对用户提示
					Toast.makeText( MainActivity.this, "请检查你AppVersion表的必填项，1、target_size（文件大小）是否填写；2、path或者android_url两者必填其中一项。", Toast.LENGTH_SHORT ).show();
				}
				else if( updateStatus == UpdateStatus.IGNORED )
				{
					Toast.makeText( MainActivity.this, "该版本已被忽略更新", Toast.LENGTH_SHORT ).show();
				}
				else if( updateStatus == UpdateStatus.ErrorSizeFormat )
				{
					Toast.makeText( MainActivity.this, "请检查target_size填写的格式，请使用file.length()方法获取apk大小。", Toast.LENGTH_SHORT ).show();
				}
				else if( updateStatus == UpdateStatus.TimeOut )
				{
					Toast.makeText( MainActivity.this, "查询出错或查询超时", Toast.LENGTH_SHORT ).show();
				}
				Log.i( "Fun", "MainActivity onCreate() -->>onUpdateReturned() updateStatus = " + updateStatus + " ,updateResponse = " + updateResponse.exception );
			}
		} );
	}
	

	
}
