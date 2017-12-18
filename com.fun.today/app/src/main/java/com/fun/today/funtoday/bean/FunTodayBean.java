package com.fun.today.funtoday.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by joy on 2017/12/12.
 */

public class FunTodayBean implements Parcelable
{
	public static final Creator< FunTodayBean > CREATOR = new Creator< FunTodayBean >()
	{
		@Override
		public FunTodayBean createFromParcel( Parcel in )
		{
			return new FunTodayBean( in );
		}
		
		@Override
		public FunTodayBean[] newArray( int size )
		{
			return new FunTodayBean[ size ];
		}
	};
	/**
	 * _id : 19021101
	 * title : 挪威作家格里格诞生
	 * pic :
	 * year : 1902
	 * month : 11
	 * day : 1
	 * des : 在115年前的今天，1902年11月1日 (农历十月初二)，挪威作家格里格诞生。
	 * lunar : 壬寅年十月初二
	 */
	
	private String _id;
	private String title;
	private String pic;
	private int year;
	private int month;
	private int day;
	private String des;
	private String lunar;
	
	public FunTodayBean()
	{
	}
	
	protected FunTodayBean( Parcel in )
	{
		_id = in.readString();
		title = in.readString();
		pic = in.readString();
		year = in.readInt();
		month = in.readInt();
		day = in.readInt();
		des = in.readString();
		lunar = in.readString();
	}
	
	public String get_id()
	{
		return _id;
	}
	
	public void set_id( String _id )
	{
		this._id = _id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle( String title )
	{
		this.title = title;
	}
	
	public String getPic()
	{
		return pic;
	}
	
	public void setPic( String pic )
	{
		this.pic = pic;
	}
	
	public int getYear()
	{
		return year;
	}
	
	public void setYear( int year )
	{
		this.year = year;
	}
	
	public int getMonth()
	{
		return month;
	}
	
	public void setMonth( int month )
	{
		this.month = month;
	}
	
	public int getDay()
	{
		return day;
	}
	
	public void setDay( int day )
	{
		this.day = day;
	}
	
	public String getDes()
	{
		return des;
	}
	
	public void setDes( String des )
	{
		this.des = des;
	}
	
	public String getLunar()
	{
		return lunar;
	}
	
	public void setLunar( String lunar )
	{
		this.lunar = lunar;
	}
	
	@Override
	public String toString()
	{
		return "FunTodayBean{" + "_id='" + _id + '\'' + ", title='" + title + '\'' + ", pic='" + pic + '\'' + ", year=" + year + ", month=" + month + ", day=" + day + ", des='" + des + '\'' + ", lunar='" + lunar + '\'' + '}';
	}
	
	@Override
	public int describeContents()
	{
		return 0;
	}
	
	@Override
	public void writeToParcel(
			Parcel dest,
			int flags )
	{
		
		dest.writeString( _id );
		dest.writeString( title );
		dest.writeString( pic );
		dest.writeInt( year );
		dest.writeInt( month );
		dest.writeInt( day );
		dest.writeString( des );
		dest.writeString( lunar );
	}
}
