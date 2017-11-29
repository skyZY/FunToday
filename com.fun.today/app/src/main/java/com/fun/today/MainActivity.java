package com.fun.today;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.calendar.library.CaledarAdapter;
import com.calendar.library.CalendarBean;
import com.calendar.library.CalendarDateView;
import com.calendar.library.CalendarUtil;
import com.calendar.library.CalendarView;

import java.util.Date;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.BmobUpdateListener;
import cn.bmob.v3.update.BmobUpdateAgent;
import cn.bmob.v3.update.UpdateResponse;
import cn.bmob.v3.update.UpdateStatus;

public class MainActivity extends AppCompatActivity
{
	CalendarDateView mCalendarDateView;
	TextView mTitle;
	ListView mList;
	ImageView mImageBack;
	Context mContext;
	public static String APPID = "4771879591ed65f2a2cb71ce5cfa00d1";
	public final static String TAG = "FunTodayApplication";
	
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_calendar );
		mContext = this;
		//		startActivity( new Intent( this, CalendarActivity.class ) );
		
		Bmob.initialize( this, APPID );
		BmobUpdateAgent.initAppVersion();
		Log.i( "fun", TAG + " onCreate() " );
		BmobUpdateAgent.setUpdateOnlyWifi( false );
		//		BmobUpdateAgent.update( this );
		
		Log.i( "Fun", this.getLocalClassName() + " onCreate()" );
		initView();
		initList();
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
	
	private void initView()
	{
		mTitle = findViewById( R.id.title );
		mCalendarDateView = findViewById( R.id.calendarDateView );
		mList = findViewById( R.id.list );
		mImageBack = findViewById( R.id.back );
		mImageBack.setOnClickListener( new View.OnClickListener()
		{
			@Override
			public void onClick( View view )
			{
				BmobUpdateAgent.update( mContext );
				Toast.makeText( mContext, "点击back", Toast.LENGTH_SHORT ).show();
			}
		} );
		
		mCalendarDateView.setAdapter( new CaledarAdapter()
		{
			@Override
			public View getView(
					View convertView,
					ViewGroup parentView,
					CalendarBean bean )
			{
				
				if( convertView == null )
				{
					convertView = LayoutInflater.from( parentView.getContext() ).inflate( R.layout.list_item_calendar, null );
				}
				
				TextView chinaText = ( TextView )convertView.findViewById( R.id.chinaDateTv );
				TextView text = ( TextView )convertView.findViewById( R.id.dateTv );
				
				text.setText( "" + bean.day );
				if( bean.mothFlag != 0 )
				{
					text.setTextColor( 0xff9299a1 );
				}
				else
				{
					text.setTextColor( 0xff444444 );
				}
				chinaText.setText( bean.chinaDay );
				
				return convertView;
			}
		} );
		
		mCalendarDateView.setOnItemClickListener( new CalendarView.OnItemClickListener()
		{
			@Override
			public void onItemClick(
					View view,
					int postion,
					CalendarBean bean )
			{
				mTitle.setText( bean.year + "/" + bean.moth + "/" + bean.day );
				BmobUpdateAgent.update( MainActivity.this );
			}
		} );
		
		int[] data = CalendarUtil.getYMD( new Date() );
		mTitle.setText( data[ 0 ] + "/" + data[ 1 ] + "/" + data[ 2 ] );
	}
	
	private void initList()
	{
		mList.setAdapter( new BaseAdapter()
		{
			@Override
			public int getCount()
			{
				return 100;
			}
			
			@Override
			public Object getItem( int position )
			{
				return null;
			}
			
			@Override
			public long getItemId( int position )
			{
				return 0;
			}
			
			@Override
			public View getView(
					int position,
					View convertView,
					ViewGroup parent )
			{
				if( convertView == null )
				{
					convertView = LayoutInflater.from( MainActivity.this ).inflate( android.R.layout.simple_list_item_1, null );
				}
				
				TextView textView = ( TextView )convertView;
				textView.setText( "position:" + position );
				
				return convertView;
			}
		} );
		mList.setOnItemClickListener( new AdapterView.OnItemClickListener()
		{
			
			@Override
			public void onItemClick(
					AdapterView< ? > adapterView,
					View view,
					int i,
					long l )
			{
				BmobUpdateAgent.update( MainActivity.this );
				Toast.makeText( MainActivity.this, " 点击listview i = " + i, Toast.LENGTH_SHORT ).show();
			}
		} );
	}
	
}
