package com.calendar.library;

import android.os.Parcel;
import android.os.Parcelable;

public class CalendarBean implements Parcelable
{
	
	public int year;
	public int moth;
	public int day;
	public int week;
	
	//-1,0,1
	public int mothFlag;
	
	//显示
	public String chinaMonth;
	public String chinaDay;
	
	public CalendarBean(
			int year,
			int moth,
			int day )
	{
		this.year = year;
		this.moth = moth;
		this.day = day;
	}
	
	protected CalendarBean( Parcel in )
	{
		year = in.readInt();
		moth = in.readInt();
		day = in.readInt();
		week = in.readInt();
		mothFlag = in.readInt();
		chinaMonth = in.readString();
		chinaDay = in.readString();
	}
	
	public static final Creator< CalendarBean > CREATOR = new Creator< CalendarBean >()
	{
		@Override
		public CalendarBean createFromParcel( Parcel in )
		{
			return new CalendarBean( in );
		}
		
		@Override
		public CalendarBean[] newArray( int size )
		{
			return new CalendarBean[ size ];
		}
	};
	
	public String getDisplayWeek()
	{
		String s = "";
		switch( week )
		{
			case 1:
				s = "星期日";
				break;
			case 2:
				s = "星期一";
				break;
			case 3:
				s = "星期二";
				break;
			case 4:
				s = "星期三";
				break;
			case 5:
				s = "星期四";
				break;
			case 6:
				s = "星期五";
				break;
			case 7:
				s = "星期六";
				break;
			
		}
		return s;
	}
	
	@Override
	public String toString()
	{
		//        String s=year+"/"+moth+"/"+day+"\t"+getDisplayWeek()+"\t农历"+":"+chinaMonth+"/"+chinaDay;
		String s = year + "/" + moth + "/" + day;
		return s;
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
		
		dest.writeInt( year );
		dest.writeInt( moth );
		dest.writeInt( day );
		dest.writeInt( week );
		dest.writeInt( mothFlag );
		dest.writeString( chinaMonth );
		dest.writeString( chinaDay );
	}
}