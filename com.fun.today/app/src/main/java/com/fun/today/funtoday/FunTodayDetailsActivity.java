package com.fun.today.funtoday;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.fun.today.R;
import com.fun.today.view.AutoSpliteTextView;

/**
 * Created by joy on 2017/12/15.
 */

public class FunTodayDetailsActivity extends Activity
{
	AutoSpliteTextView mText;
	
	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		setContentView( R.layout.activity_funtoday_detail );
		
		mText = findViewById( R.id.ft_detail_content_text );
		mText.setText( "http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200905/17/C823305577.jpg content 2333333asfqiujivifjw iwejwijafjoejsifjiegjiwaifjaijfakfjai我们都是中国人，在375年前的今天，1642年12月12日 (content 2333333asfqiujivifjw iwejwijafjoejsifjiegjiwaifjaijfakfjai我们都是中国人，在375年前的今天，1642年12月12日 (content 2333333asfqiujivifjw iwejwijafjoejsifjiegjiwaifjaijfakfjai我们都是中国人，在375年前的今天，1642年12月12日 (农历冬月廿一)，荷兰航海家亚伯·塔斯曼发现新西兰" + "content 2333333asfqiujivifjw iwejwijafjoejsifjiegjiwaifjaijfakfjai我们都是中国人，在375年前的今天，1642年12月12日 (农历冬月廿一)，荷兰航海家亚伯·塔斯曼发现新西兰" );
	}
}
