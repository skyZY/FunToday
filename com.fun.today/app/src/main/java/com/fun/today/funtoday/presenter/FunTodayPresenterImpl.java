package com.fun.today.funtoday.presenter;

import android.os.Handler;
import android.os.Looper;

import com.fun.today.funtoday.model.FunTodayRequestModel;
import com.fun.today.funtoday.model.FunTodayRequestModelImpl;
import com.fun.today.funtoday.view.FunTodayView;
import com.mvp.library.presenter.BaseMvpPresenter;

/**
 * Created by joy on 2017/12/12.
 */

public class FunTodayPresenterImpl extends BaseMvpPresenter< FunTodayView > implements FunTodayPresenter
{
	private FunTodayView mFunTodayView;
	FunTodayRequestModel mFunTodayRequestModel;
	Handler mHandler;
	
	public FunTodayPresenterImpl( FunTodayView funTodayView )
	{
		this.mFunTodayView = funTodayView;
		mFunTodayRequestModel = new FunTodayRequestModelImpl();
		mHandler = new Handler( Looper.getMainLooper() );
	}
	
	@Override
	public void doRequestFunToday(
			int month,
			int day )
	{
		mFunTodayRequestModel.doRequestFunToday( month, day, mFunTodayView );
	}
}
