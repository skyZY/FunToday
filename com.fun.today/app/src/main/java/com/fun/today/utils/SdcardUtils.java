package com.fun.today.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by joy on 2017/11/1.
 */

public class SdcardUtils
{
	private final static String PERMISSION_WRITE = "android.permission.WRITE_EXTERNAL_STORAGE";
	
	public static boolean canWriteSdcard( Context context )
	{
		try
		{
			if( isSdCardExist() )
			{
				boolean isPermissionRegeisted = judgeSdcardPermission( context, PERMISSION_WRITE );
				if( isPermissionRegeisted )
				{
					return writeFileToSdcard( context );
				}
			}
		}
		catch( Exception e )
		{
			LogUtils.e( "SdcardUtils needWriteSdcard() exception : " + e.toString() );
		}
		return false;
	}
	
	/**
	 *
	 * @param context
	 * @return
	 */
	public static boolean writeFileToSdcard( Context context )
	{
		String destFilePath = Environment.getExternalStorageDirectory() + File.separator + "test.txt";
		try
		{
			File file = new File( Environment.getExternalStorageDirectory() + Constants.SDCARD_SDDIR_FILEPATH );
			if( !file.exists() )
			{
				String filePath = context.getExternalFilesDir( null ).getParent();
				new File( filePath ).mkdirs();
			}
			File file1 = new File( destFilePath );
			if( !file1.exists() )
			{
				file1.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream( file1 );
			String info = "This is a test";
			fos.write( info.getBytes() );
			fos.close();
			return true;
		}
		catch( Exception e )
		{
			LogUtils.i( "syp", "exception : " + e.toString() );
			e.printStackTrace();
		}
		finally
		{
			File fileCreate = new File( destFilePath );
			if( fileCreate.exists() )
			{
				new File( destFilePath ).delete();
			}
		}
		return false;
	}
	
	public static boolean judgeSdcardPermission(
			Context context,
			String checkPermission )
	{
		try
		{
			PackageManager pm = context.getPackageManager();
			PackageInfo pacageInfo = pm.getPackageInfo( context.getPackageName(), PackageManager.GET_PERMISSIONS );
			String[] permissionStrings = pacageInfo.requestedPermissions;
			if( permissionStrings.length <= 0 )
			{
				return false;
			}
			for( String permission : permissionStrings )
			{
				if( checkPermission.equals( permission ) )
				{
					return true;
				}
			}
		}
		catch( PackageManager.NameNotFoundException e )
		{
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 判断SDCard是否存在 [当没有外挂SD卡时，内置ROM也被识别为存在sd卡]
	 */
	public static boolean isSdCardExist()
	{
		return Environment.MEDIA_MOUNTED.equals( Environment.getExternalStorageState() );
	}
}


