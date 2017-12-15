package com.fun.today.funtoday;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fun.today.R;
import com.fun.today.db.DBConfig;
import com.fun.today.funtoday.bean.FunTodayBean;
import com.fun.today.funtoday.presenter.FunTodayPresenterImpl;
import com.fun.today.funtoday.view.FunTodayAdapter;
import com.fun.today.funtoday.view.FunTodayView;
import com.fun.today.utils.Constants;
import com.fun.today.utils.DateAndTimeUtils;
import com.fun.today.utils.LogUtils;
import com.mvp.library.view.AbstractMvpActivitiy;

import java.util.List;

/**
 * Created by joy on 2017/12/12.
 */

public class FunTodayActivity extends AbstractMvpActivitiy< FunTodayView, FunTodayPresenterImpl > implements FunTodayView
{
	FunTodayPresenterImpl funTodayPresenterImpl;
	private ImageView mImgeBtnBack;
	private TextView mTextTitle;
	private ListView mList;
	private ProgressBar mPb;
	private FunTodayAdapter mFunTodayAdapter;
	private Context mContext;
	
	Handler mHandler = new Handler( Looper.getMainLooper() );
	
	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		setContentView( R.layout.activity_funtoday );
		mContext = this;
		
		initView();
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		String currentKey = DateAndTimeUtils.getCurrentMonth() + "-" + DateAndTimeUtils.getCurrentDay();
		String lastSaveKey = DBConfig.getInstance( mContext ).getKpshString( Constants.KEY_DB_SAVE_LAST_KEY );
		if( currentKey.equals( lastSaveKey ) )
		{
			LogUtils.i( "onResume() currentKey = lastSaveKey " + currentKey );
			String data = DBConfig.getInstance( mContext ).getKpshString( Constants.DB_SAVE_LAST_DATA_KEY );
			List< FunTodayBean > dataList = FunTodayUtils.analyseRequestData( data );
			if( dataList.size() > 0 )
			{
				mFunTodayAdapter = new FunTodayAdapter( dataList );
				mHandler.post( new Runnable()
				{
					@Override
					public void run()
					{
						mList.setAdapter( mFunTodayAdapter );
					}
				} );
			}
		}
		else
		{
			funTodayPresenterImpl = new FunTodayPresenterImpl( FunTodayActivity.this );
			funTodayPresenterImpl.doRequestFunToday();
		}
	}
	
	void initView()
	{
		mImgeBtnBack = findViewById( R.id.ft_img_back );
		mTextTitle = findViewById( R.id.ft_txt_title );
		Intent intent = getIntent();
		LogUtils.i( "intent = " + intent );
		String date;
		if( null != intent )
		{
			date = intent.getStringExtra( "date" );
		}
		else
		{
			date = DateAndTimeUtils.getStringCurrentDay();
		}
		mTextTitle.setText( date );
		mList = findViewById( R.id.ft_list );
		mPb = findViewById( R.id.ft_pb );
		mImgeBtnBack.setOnClickListener( new View.OnClickListener()
		{
			@Override
			public void onClick( View view )
			{
				finish();
			}
		} );
		mList = findViewById( R.id.ft_list );
		mPb = findViewById( R.id.ft_pb );
		mList.setOnItemClickListener( listener );
	}
	
	AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener()
	{
		@Override
		public void onItemClick(
				AdapterView< ? > adapterView,
				View view,
				int i,
				long l )
		{
			startActivity( new Intent( mContext, FunTodayDetailsActivity.class ) );
		}
	};
	
	@Override
	public void requestSuccess( List< FunTodayBean > list )
	{
		LogUtils.i( "requestSuccess() list = " + list );
		mFunTodayAdapter = new FunTodayAdapter( list );
		mHandler.post( new Runnable()
		{
			@Override
			public void run()
			{
				mList.setAdapter( mFunTodayAdapter );
			}
		} );
	}
	
	@Override
	public void requestLoading()
	{
		LogUtils.i( "requestLoading() " );
		mHandler.post( new Runnable()
		{
			@Override
			public void run()
			{
				mPb.setVisibility( View.VISIBLE );
			}
		} );
		
	}
	
	@Override
	public void hideLoading()
	{
		LogUtils.i( "hideLoading() " );
		mHandler.post( new Runnable()
		{
			@Override
			public void run()
			{
				mPb.setVisibility( View.GONE );
			}
		} );
	}
	
	@Override
	public void requestFailure( String result )
	{
		LogUtils.i( "requestFailure() result = " + result );
	}
	
	@Override
	public void showList( List< FunTodayBean > list )
	{
		LogUtils.i( "showList() list = " + list );
	}
	
	@Override
	public void clickItem(
			List< FunTodayBean > list,
			int position )
	{
		LogUtils.i( "clickItem() list = " + list );
	}
	
	@Override
	public void saveFunToday(
			int month,
			int day,
			String data )
	{
		String currentKey = month + "-" + day;
		DBConfig.getInstance( mContext ).setKpshString( Constants.KEY_DB_SAVE_LAST_KEY, currentKey );
		DBConfig.getInstance( mContext ).setKpshString( Constants.DB_SAVE_LAST_DATA_KEY, data );
		
	}
	
	@Override
	public void clearFunToday(
			int month,
			int day )
	{
	
	}
}
