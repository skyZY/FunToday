package com.mvp.library;

import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by Administrator on 2017/5/8.
 */

public class MvpLogUtils
{
	
	public final static String DEFAULT_TAG = "SypMvp";
	public final static String TAG_FILE = Environment.getExternalStorageDirectory() + "/LogLevel.dat";
	public static boolean PRINT_LOG = false;
	
	public static boolean isExistLogFile()
	{
		if( !PRINT_LOG )
		{
			PRINT_LOG = new File( TAG_FILE ).exists();
			Log.i( DEFAULT_TAG, " PRINT_LOG = " + PRINT_LOG + " , TAG_FILE = " + TAG_FILE );
		}
		return PRINT_LOG;
	}
	
	public static void i( String msg )
	{
		if( isExistLogFile() )
		{
			Log.i( DEFAULT_TAG, msg );
		}
	}
	
	public static void d( String msg )
	{
		if( isExistLogFile() )
		{
			Log.d( DEFAULT_TAG, msg );
		}
	}
	
	public static void e( String msg )
	{
		if( isExistLogFile() )
		{
			Log.e( DEFAULT_TAG, msg );
		}
	}
	
}
