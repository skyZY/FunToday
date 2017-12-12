package com.fun.today.funtoday.model;

import com.fun.today.funtoday.view.FunTodayView;

/**
 * Created by joy on 2017/12/12.
 */

public interface FunTodayRequestModel
{
	void doRequestFunToday(
			int month,
			int day,
			FunTodayView funTodayView );
	
	void saveFunToday(
			int month,
			int day );
	
	void clearFunToday(
			int month,
			int day );
}
