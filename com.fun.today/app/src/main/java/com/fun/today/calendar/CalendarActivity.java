package com.fun.today.calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.calendar.library.CaledarAdapter;
import com.calendar.library.CalendarBean;
import com.calendar.library.CalendarDateView;
import com.calendar.library.CalendarUtil;
import com.calendar.library.CalendarView;
import com.fun.today.R;
import com.fun.today.funtoday.FunTodayActivity;

import java.util.Date;

/**
 * Created by joy on 2017/11/27.
 */

public class CalendarActivity extends Activity
{
	CalendarDateView mCalendarDateView;
	TextView mTitle;
	ListView mList;
	//	ImageView mImageBack;
	Context mContext;
	
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		setContentView( R.layout.activity_calendar );
		mContext = this;
		initView();
		initList();
	}
	
	private void initView()
	{
		mTitle = findViewById( R.id.title );
		mCalendarDateView = findViewById( R.id.calendarDateView );
		mList = findViewById( R.id.list );
	/*	mImageBack = findViewById( R.id.back );
		mImageBack.setOnClickListener( new View.OnClickListener()
		{
			@Override
			public void onClick( View view )
			{
				//				BmobUpdateAgent.update( mContext );
			}
		} );*/
		
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
				String date = bean.year + "/" + bean.moth + "/" + bean.day;
				mTitle.setText( date );
				Intent intent = new Intent( mContext, FunTodayActivity.class );
				intent.putExtra( "date", date );
				startActivity( intent );
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
					convertView = LayoutInflater.from( mContext ).inflate( android.R.layout.simple_list_item_1, null );
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
			
			}
		} );
	}
}
