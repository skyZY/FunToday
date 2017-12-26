package com.fun.today.funtoday;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.fun.today.FunTodayApplication;
import com.fun.today.R;
import com.fun.today.funtoday.bean.FunTodayBean;
import com.fun.today.view.AutoSpliteTextView;
import com.umeng.analytics.MobclickAgent;

/**
 * author sunyanpeng
 * Created by joy on 2017/12/15.
 */

public class FunTodayDetailsActivity extends Activity
{
	AutoSpliteTextView mText;
	FunTodayBean mFunTodayBean;
	TextView ft_detail_txt_title;
	TextView ft_detail_date;
	ImageView ft_detail_pic;
	private Context mContext;
	
	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		setContentView( R.layout.activity_funtoday_detail );
		
		mContext = this;
		initId();
		initData();
		
	}
	
	void initId()
	{
		ft_detail_txt_title = findViewById( R.id.ft_detail_txt_title );
		mText = findViewById( R.id.ft_detail_content_text );
		ft_detail_date = findViewById( R.id.ft_detail_date );
		ft_detail_pic = findViewById( R.id.ft_detail_pic );
	}
	
	void initData()
	{
		mFunTodayBean = getIntent().getParcelableExtra( "funtodaybean" );
		ft_detail_txt_title.setText( mFunTodayBean.getTitle() );
		mText.setText( mFunTodayBean.getDes() );
		ft_detail_date.setText( mFunTodayBean.getYear() + "/" + mFunTodayBean.getMonth() + "/" + mFunTodayBean.getDay() );
		if( !TextUtils.isEmpty( mFunTodayBean.getPic() ) )
		{
			imageLoader( mFunTodayBean.getPic(), ft_detail_pic );
		}
	}
	
	public void imageLoader(
			String url,
			ImageView iv )
	{
		//ImageLoader的第一个参数就是RequestQueue：即Volley的请求队列
		// ImageLoader的第二个参数是ImageCache：图片缓存，下面会将如何自定义缓存
		ImageLoader il = new ImageLoader( FunTodayApplication.getsInstance().getRequestQueue(), new BitCache() );
		//接下来需要获取一个ImageListener对象
		//getImageListener的三个参数分别为：要加载ImageView对象，图片的默认值（网络图片加载完成前所显示的图片），图片请求出错时的值
		ImageLoader.ImageListener imageListener = ImageLoader.getImageListener( iv, R.mipmap.default_img, R.mipmap.default_img );
		//调用ImageLoader的get()方法来加载图片
		//get()方法第一个参数为图片的url地址，第二个参数是上面获取的ImageListener对象
		//后两个参数用来指定图片允许的最大宽度和高度，可不写
		il.get( url, imageListener, 500, 500 );
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		MobclickAgent.onResume( mContext );
	}
	
	@Override
	protected void onPause()
	{
		super.onPause();
		MobclickAgent.onPause( mContext );
	}
}
