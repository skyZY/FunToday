package com.fun.today.utils;

import android.content.Context;

import java.io.File;

public class FilePathUtil
{
	
	public static String getPrivatePath( Context context )
	{
		return context.getFilesDir().getAbsolutePath() + File.separator;
	}
	
	// 获取kpsh T卡文件目录
	public static String getSdcardPath( Context context )
	{
		return context.getExternalFilesDir( null ).getAbsolutePath() + File.separator;
	}
	
}
