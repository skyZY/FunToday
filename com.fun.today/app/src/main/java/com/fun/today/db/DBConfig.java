/**
 * database for kpsh msg and some flag.
 */
package com.fun.today.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.fun.today.utils.FilePathUtil;
import com.fun.today.utils.LogUtils;
import com.fun.today.utils.SdcardUtils;

import java.io.File;
import java.util.HashMap;

public class DBConfig
{
	
	private static final String CONFIG_DBNAME = "dn_config.db";
	private static DBConfig instance = null;
	private static SQLiteDatabase defaultDatabase = null;
	private static SQLiteDatabase appDatabase = null;
	
	private DBConfig()
	{
	}
	
	public static DBConfig getInstance( Context context )
	{
		synchronized( DBConfig.class )
		{
			if( instance == null )
			{
				instance = new DBConfig();
			}
			try
			{
				String defaultFileDir;
				String appFileDir = null;
				// 双备份文件夹目录
				if( SdcardUtils.canWriteSdcard( context ) )
				{
					defaultFileDir = FilePathUtil.getSdcardPath( context );
					
				}
				defaultFileDir = FilePathUtil.getPrivatePath( context );
				// 文件不存在创建文件
				createKpshDB( defaultFileDir, appFileDir );
				// 如果数据库连接没打开，打开数据库连接
				if( defaultFileDir != null && defaultDatabase == null || !defaultDatabase.isOpen() )
				{
					try
					{
						defaultDatabase = SQLiteDatabase.openOrCreateDatabase( defaultFileDir + CONFIG_DBNAME, null );
					}
					catch( Exception ex )
					{
					}
				}
				if( appFileDir != null && ( appDatabase == null || !appDatabase.isOpen() ) )
				{
					try
					{
						appDatabase = SQLiteDatabase.openOrCreateDatabase( appFileDir + CONFIG_DBNAME, null );
					}
					catch( Exception ex )
					{
					}
				}
			}
			catch( Exception e )
			{
				LogUtils.exception( e );
			}
			return instance;
		}
	}
	
	/**
	 *
	 * @param defaultFileDir
	 * @param appFileDir
	 */
	private static void createKpshDB(
			String defaultFileDir,
			String appFileDir )
	{
		if( defaultFileDir != null )
		{
			File defaultDbFile = new File( defaultFileDir + CONFIG_DBNAME );
			if( !defaultDbFile.exists() )
			{
				try
				{
					File file = new File( defaultDbFile.getParent() );
					if( !file.exists() )
					{// 目录存在返回
						file.mkdirs();// 创建一个目录
					}
					boolean createFile = defaultDbFile.createNewFile();// 创建数据库文件
					if( !createFile )
					{
						return;
					}
					defaultDatabase = SQLiteDatabase.openOrCreateDatabase( defaultDbFile, null );
					// 创建保存标志位表
					String flag_sql = "CREATE TABLE flag (id INTEGER PRIMARY KEY AUTOINCREMENT,name name,value value,otime otime,utime utime);";
					defaultDatabase.execSQL( flag_sql );
				}
				catch( Exception e )
				{
					// TODO Auto-generated catch block
					LogUtils.e( e.toString() );
				}
			}
		}
		if( appFileDir != null )
		{
			File appDbFile = new File( appFileDir + CONFIG_DBNAME );
			if( !appDbFile.exists() )
			{
				try
				{
					File file = new File( appDbFile.getParent() );
					if( !file.exists() )
					{
						/*
						 * 创建一个目录
						 */
						file.mkdirs();
					}
					/* 创建数据库文件 */
					appDbFile.createNewFile();
					appDatabase = SQLiteDatabase.openOrCreateDatabase( appDbFile, null );
					// 创建保存标志位表
					String flag_sql = "CREATE TABLE flag (id INTEGER PRIMARY KEY AUTOINCREMENT,name name,value value,otime otime,utime utime);";
					appDatabase.execSQL( flag_sql );
				}
				catch( Exception e )
				{
					LogUtils.e( e.toString() );
				}
			}
		}
	}
	
	public long getKpshLong( String flagName )
	{
		try
		{
			String value = getKpshString( flagName );
			if( value != null )
			{
				return Long.parseLong( value );
			}
		}
		catch( Exception e )
		{
		}
		return -1;
	}
	
	public boolean setKpshLong(
			String flagName,
			long value )
	{
		return setKpshString( flagName, "" + value );
	}
	
	public long getKpshLong(
			String flagName,
			long defValue )
	{
		long result = defValue;
		try
		{
			String value = getKpshString( flagName, defValue + "" );
			result = Long.parseLong( value );
		}
		catch( Exception e )
		{
			LogUtils.e( e.toString() );
		}
		return result;
	}
	
	public String getKpshString(
			String falgName,
			String defValue )
	{
		String value = getKpshStringFromDB( appDatabase, falgName, defValue );
		if( null != value && !value.equals( defValue ) )
		{
			return value;
		}
		return getKpshStringFromDB( defaultDatabase, falgName, defValue );
	}
	
	public String getKpshString( String flagName )
	{
		String value = getKpshStringFromDB( appDatabase, flagName );
		if( value != null )
		{
			return value;
		}
		return getKpshStringFromDB( defaultDatabase, flagName );
	}
	
	public boolean deleteKpshContentValues(
			SQLiteDatabase sql,
			String key )
	{
		try
		{
			if( sql == null || !sql.isOpen() )
			{
				return false;
			}
			if( TextUtils.isEmpty( key ) )
			{
				return false;
			}
			int result = sql.delete( "flag", "name =?", new String[]{ key } );
			return result > 0;
		}
		catch( Exception e )
		{
			LogUtils.e( e.toString() );
		}
		return false;
	}
	
	public boolean deleteDBKey( String key )
	{
		boolean delDef = deleteKpshContentValues( defaultDatabase, key );
		boolean delApp = deleteKpshContentValues( appDatabase, key );
		return delDef && delApp;
	}
	
	public boolean setKpshString(
			String flagName,
			String value )
	{
		boolean flag1 = setKpshStringFromDB( defaultDatabase, flagName, value );
		boolean flag2 = setKpshStringFromDB( appDatabase, flagName, value );
		return ( flag1 || flag2 );
	}
	
	private String getKpshStringFromDB(
			SQLiteDatabase sql,
			String name )
	{
		Cursor cur = null;
		try
		{
			String value = null;
			if( sql == null || !sql.isOpen() )
			{
				return null;
			}
			cur = sql.rawQuery( "SELECT value FROM flag WHERE name= '" + name + "'", null );
			if( null != cur )
			{
				while( cur.moveToNext() )
				{
					value = cur.getString( 0 );
				}
			}
			return value;
		}
		catch( Exception e )
		{
			LogUtils.e( e.toString() );
		}
		finally
		{
			try
			{
				if( null != cur && !cur.isClosed() )
				{
					cur.close();
				}
			}
			catch( Exception e )
			{
			}
		}
		return null;
	}
	
	private String getKpshStringFromDB(
			SQLiteDatabase sql,
			String name,
			String defValue )
	{
		Cursor cur = null;
		String value = defValue;
		try
		{
			if( sql == null || !sql.isOpen() )
			{
				return value;
			}
			cur = sql.rawQuery( "SELECT value FROM flag WHERE name= '" + name + "'", null );
			if( cur != null )
			{
				while( cur.moveToNext() )
				{
					value = cur.getString( 0 );
				}
			}
		}
		catch( Exception e )
		{
			LogUtils.e( e.toString() );
		}
		finally
		{
			try
			{
				if( null != cur && !cur.isClosed() )
				{
					cur.close();
				}
			}
			catch( Exception e )
			{
			}
		}
		return value;
	}
	
	private boolean setKpshStringFromDB(
			SQLiteDatabase sql,
			String name,
			String value )
	{
		ContentValues cvalue = new ContentValues();
		cvalue.put( "name", name );
		cvalue.put( "value", value );
		return setKpshContentValues( sql, cvalue );
	}
	
	private boolean setKpshContentValues(
			SQLiteDatabase sql,
			ContentValues cvalue )
	{
		Cursor cur = null;
		try
		{
			if( sql == null || !sql.isOpen() )
			{
				return false;
			}
			String name = cvalue.getAsString( "name" );
			cur = sql.rawQuery( "SELECT * FROM flag WHERE name= '" + cvalue.getAsString( "name" ) + "'", null );
			boolean isHave = false;
			if( null != cur )
			{
				while( cur.moveToNext() )
				{
					isHave = true;
				}
			}
			if( isHave )
			{
				sql.update( "flag", cvalue, "name" + "=" + "'" + name + "'", null );
			}
			else
			{
				sql.insert( "flag", null, cvalue );
			}
			return true;
		}
		catch( Exception e )
		{
			LogUtils.e( e.toString() );
			
		}
		finally
		{
			try
			{
				if( null != cur && !cur.isClosed() )
				{
					cur.close();
				}
			}
			catch( Exception e )
			{
			}
		}
		return false;
	}
	
	public HashMap< String, Long > getConnectAfterTime()
	{
		HashMap< String, Long > value;
		value = getConnectAfterTimeFromDB( appDatabase );
		if( value == null )
		{
			value = getConnectAfterTimeFromDB( defaultDatabase );
		}
		return value;
	}
	
	private HashMap< String, Long > getConnectAfterTimeFromDB( SQLiteDatabase sql )
	{
		Cursor cur = null;
		try
		{
			HashMap< String, Long > value = null;
			if( sql == null || !sql.isOpen() )
			{
				return null;
			}
			cur = sql.rawQuery( "SELECT * FROM flag WHERE name= 'connectafter'", null );
			if( cur != null )
			{
				while( cur.moveToNext() )
				{
					value = new HashMap< String, Long >();
					value.put( "flag", cur.getLong( 2 ) );
					value.put( "ctime", cur.getLong( 3 ) );
					value.put( "utime", cur.getLong( 4 ) );
				}
			}
			return value;
		}
		catch( Exception e )
		{
			LogUtils.e( e.toString() );
			
		}
		finally
		{
			try
			{
				if( cur != null )
				{
					cur.close();
				}
			}
			catch( Exception e )
			{
			}
		}
		return null;
	}
	
	public void closeKpshDb()
	{
		if( defaultDatabase != null )
		{
			try
			{
				defaultDatabase.close();
			}
			catch( Exception e )
			{
				LogUtils.e( e.toString() );
			}
		}
		if( appDatabase != null )
		{
			try
			{
				appDatabase.close();
			}
			catch( Exception e )
			{
				LogUtils.e( e.toString() );
			}
		}
	}
	
}
