package com.mvp.library.proxy;

import com.mvp.library.factory.PresenterMvpFactory;
import com.mvp.library.presenter.BaseMvpPresenter;
import com.mvp.library.view.BaseMvpView;

/**
 * Created by joy on 2017/12/11.
 */

public interface PresenterProxyInterface< V extends BaseMvpView, P extends BaseMvpPresenter< V > >
{
	
	/**
	 * 设置创建Presenter的工厂
	 * @param presenterFactory PresenterFactory类型
	 */
	void setPresenterFactory( PresenterMvpFactory< V, P > presenterFactory );
	
	/**
	 * 获取Presenter的工厂类
	 * @return 返回PresenterMvpFactory类型
	 */
	PresenterMvpFactory< V, P > getPresenterFactory();
	
	/**
	 * 获取创建的Presenter
	 * @return 指定类型的Presenter
	 */
	P getMvpPresenter();
	
}
