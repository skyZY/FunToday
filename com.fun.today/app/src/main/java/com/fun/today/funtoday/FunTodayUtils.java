package com.fun.today.funtoday;

import android.text.TextUtils;

import com.fun.today.funtoday.bean.FunTodayBean;
import com.fun.today.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joy on 2017/12/15.
 */

public class FunTodayUtils
{
	
	public static List< FunTodayBean > analyseRequestData( String data )
	{
		try
		{
			if( TextUtils.isEmpty( data ) )
			{
				return null;
			}
			JSONObject jsonObject = new JSONObject( data );
			if( jsonObject.has( "result" ) )
			{
				List< FunTodayBean > dataList = new ArrayList< FunTodayBean >();
				JSONArray jsonArray = jsonObject.getJSONArray( "result" );
				int len = jsonArray.length();
				
				for( int i = 0 ; i < len ; i++ )
				{
					FunTodayBean funTodayBean = new FunTodayBean();
					JSONObject json = jsonArray.getJSONObject( i );
					funTodayBean.setDay( json.getInt( "day" ) );
					funTodayBean.set_id( json.getString( "_id" ) );
					funTodayBean.setDes( json.getString( "des" ) );
					funTodayBean.setLunar( json.getString( "lunar" ) );
					funTodayBean.setMonth( json.getInt( "month" ) );
					funTodayBean.setPic( json.getString( "pic" ) );
					funTodayBean.setTitle( json.getString( "title" ) );
					funTodayBean.setYear( json.getInt( "year" ) );
					dataList.add( funTodayBean );
				}
				return dataList;
			}
			
		}
		catch( Exception e )
		{
			LogUtils.e( "analyseRequestData() exception : " + e.toString() );
		}
		return null;
	}
}
