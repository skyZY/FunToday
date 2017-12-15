package com.fun.today.funtoday.view;

import com.fun.today.funtoday.bean.FunTodayBean;
import com.mvp.library.view.BaseMvpView;

import java.util.List;

/**
 * Created by joy on 2017/12/12.
 */

public interface FunTodayView extends BaseMvpView
{
	/**
	 * 请求网络成功
	 */
	void requestSuccess( List< FunTodayBean > list );
	
	/**
	 * 请求网络
	 */
	void requestLoading();
	
	/**
	 * 网络请求结束
	 */
	void hideLoading();
	
	/**
	 * 请求失败
	 */
	void requestFailure( String result );
	
	/**
	 * 展示列表
	 */
	void showList( List< FunTodayBean > list );
	
	/**
	 * 点击列表某一项
	 */
	void clickItem(
			List< FunTodayBean > list,
			int position );
	
	/**
	 * 将当天数据保存到db
	 */
	void saveFunToday(
			int month,
			int day,
			String data );
	
	/**
	 * 清楚当天db数据
	 */
	void clearFunToday(
			int month,
			int day );
	
}
