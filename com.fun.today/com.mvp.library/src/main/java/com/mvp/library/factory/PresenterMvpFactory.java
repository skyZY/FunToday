package com.mvp.library.factory;

import com.mvp.library.presenter.BaseMvpPresenter;
import com.mvp.library.view.BaseMvpView;

/**
 * syp
 * description Presenter工厂接口
 * date 2017-12-11
 */

public interface PresenterMvpFactory< V extends BaseMvpView, P extends BaseMvpPresenter< V > >
{
	/**
	 * 创建Presenter的接口方法
	 * @return 需要创建的Presenter
	 */
	P createMvpPresenter();
}
