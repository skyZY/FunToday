package com.mvp.library.presenter;

import android.os.Bundle;

import com.mvp.library.MvpLogUtils;
import com.mvp.library.view.BaseMvpView;

public class BaseMvpPresenter< V extends BaseMvpView >
{
	
	/**
	 * V层view
	 */
	private V mView;
	
	/**
	 * Presenter被创建后调用
	 * @param savedState 被意外销毁后重建后的Bundle
	 */
	public void onCreatePrsenter( Bundle savedState )
	{
		MvpLogUtils.i( "P onCreatePresenter() savedState = " + savedState );
	}
	
	/**
	 * 绑定view
	 * @param mvpView 绑定的view
	 */
	public void onAttachMvpView( V mvpView )
	{
		this.mView = mvpView;
		MvpLogUtils.i( "P onAttachMvpView() onResume mView = " + mView );
	}
	
	/**
	 * 解除绑定view
	 */
	public void onDetachMvpView()
	{
		mView = null;
		MvpLogUtils.i( "P onDetachMvpView() mView = " + mView );
	}
	
	/**
	 * present被销毁时调用
	 */
	public void onDestroyPresent()
	{
		MvpLogUtils.i( "P onDestroyPresent()  " );
	}
	
	/**
	 * 在Present 意外销毁的时候被调用，它的调用时机和Activity、Fragment、View中的onSaveInstanceState时机相同
	 */
	public void onSaveInstanceState( Bundle outState )
	{
		MvpLogUtils.i( "P onSaveInstanceState() outState = " + outState );
	}
	
	/**
	 * @return 返回当前MvpView
	 * 获取V层接口View
	 */
	public V getMvpView()
	{
		MvpLogUtils.i( "P getMvpView() mView = " + mView );
		return mView;
	}
	
}
