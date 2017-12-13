package com.fun.today.funtoday.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fun.today.R;
import com.fun.today.funtoday.bean.FunTodayBean;

import java.util.List;

/**
 * Created by joy on 2017/12/12.
 */

public class FunTodayAdapter extends BaseAdapter
{
	private List< FunTodayBean > mDataList;
	
	public FunTodayAdapter( List< FunTodayBean > dataList )
	{
		this.mDataList = dataList;
		;
	}
	
	@Override
	public int getCount()
	{
		return mDataList != null ? mDataList.size() : 0;
	}
	
	@Override
	public Object getItem( int i )
	{
		return mDataList != null ? mDataList.get( i ) : null;
	}
	
	@Override
	public long getItemId( int i )
	{
		return i;
	}
	
	@Override
	public View getView(
			int i,
			View view,
			ViewGroup viewGroup )
	{
		ViewHolder viewHolder = null;
		if( null == view )
		{
			view = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.ft_layout_list_item, null );
			viewHolder = new ViewHolder();
			viewHolder.ft_list_title = ( TextView )view.findViewById( R.id.ft_list_title );
			viewHolder.ft_list_year = ( TextView )view.findViewById( R.id.ft_list_year );
			view.setTag( viewHolder );
		}
		FunTodayBean funTodayBean = mDataList.get( i );
		viewHolder = ( ViewHolder )view.getTag();
		viewHolder.ft_list_title.setText( funTodayBean.getTitle() );
		viewHolder.ft_list_year.setText( funTodayBean.getYear() + "" );
		return view;
	}
	
	private static class ViewHolder
	{
		private TextView ft_list_title;
		private TextView ft_list_year;
	}
}
