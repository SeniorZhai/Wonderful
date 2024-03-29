package com.xgr.wonderful.ui;

import net.youmi.android.AdManager;
import net.youmi.android.offers.OffersManager;

import cn.bmob.v3.Bmob;

import com.umeng.analytics.MobclickAgent;
import com.umeng.fb.FeedbackAgent;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengRegistrar;
import com.xgr.wonderful.R;
import com.xgr.wonderful.ui.base.BaseActivity;
import com.xgr.wonderful.utils.LogUtils;
import com.xgr.wonderful.utils.UmengStat;

import android.os.Bundle;
import android.os.Handler;

/**
 * @author kingofglory
 *         email: kingofglory@yeah.net
 *         blog:  http:www.google.com
 * @date 2014-2-21
 * TODO 闪屏界面，根据指定时间进行跳转
 * 		在activity_splash.xml中加入background属性并传入图片资源ID即可
 */
public class SplashActivity extends BaseActivity {

	private static final long DELAY_TIME = 2000L;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		Bmob.initialize(this, "48637b133bf986f332fc03f1a635d1cf");
		
		LogUtils.i(TAG,TAG + " Launched ！");
		MobclickAgent.openActivityDurationTrack(UmengStat.IS_OPEN_ACTIVITY_AUTO_STAT);
		FeedbackAgent agent = new FeedbackAgent(this);
		agent.sync();
		redirectByTime();		
		if(sputil.getValue("isPushOn", true)){
			PushAgent mPushAgent = PushAgent.getInstance(mContext);
			mPushAgent.enable();
			LogUtils.i(TAG,"device_token:"+UmengRegistrar.getRegistrationId(mContext));
		}else{
			PushAgent mPushAgent = PushAgent.getInstance(mContext);
			mPushAgent.disable();
		}
		
		AdManager.getInstance(mContext).init("67daabfc8ffec9c7", "7748a02fe32d6532", false);
		OffersManager.getInstance(mContext);
	}
	
	/**
	 * 根据时间进行页面跳转
	 */
	private void redirectByTime() {
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				redictToActivity(SplashActivity.this, MainActivity.class, null);
				finish();
			}
		}, DELAY_TIME);
	}

}
