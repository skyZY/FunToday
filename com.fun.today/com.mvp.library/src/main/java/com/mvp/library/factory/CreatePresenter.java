package com.mvp.library.factory;

import com.mvp.library.presenter.BaseMvpPresenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by joy on 2017/12/11.
 */

@Inherited
@Retention( RetentionPolicy.RUNTIME )
public @interface CreatePresenter
{
	Class< ? extends BaseMvpPresenter > value();
}
