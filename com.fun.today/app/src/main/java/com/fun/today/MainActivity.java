package com.fun.today;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fun.today.funtoday.FunTodayActivity;

public class MainActivity extends Activity
{
	
	Context mContext;
	public final static String TAG = "FunTodayApplication";
	private Button mBtn_Ft;
	
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		mContext = this;
		//		startActivity( new Intent( this, CalendarActivity.class ) );
		
		mBtn_Ft = findViewById( R.id.btn_ft );
		mBtn_Ft.setOnClickListener( new View.OnClickListener()
		{
			@Override
			public void onClick( View view )
			{
				startActivity( new Intent( mContext, FunTodayActivity.class ) );
			}
		} );
	}
	
}
