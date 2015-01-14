package com.pgw.wiretap.control;

import com.alibaba.fastjson.JSONObject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;

public abstract class BaseActivity extends Activity {
	public static final String ACTION = "com.pgw.activity.receiver";
	protected SharedPreferences sp;
	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String msg = intent.getStringExtra("msg");
			JSONObject msgObj=JSONObject.parseObject(msg);
			int type=msgObj.getIntValue("type");
			receive(type,msgObj.getString("body"));
		}
	};
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sp=getSharedPreferences("config", MODE_PRIVATE);
		
	};
	@Override
	protected void onStop() {
		unregisterReceiver(receiver);
		super.onPause();
	}

	protected abstract void receive(int type, String data) ;

	@Override
	protected void onStart() {
		IntentFilter filter = new IntentFilter();
		filter.addAction(ACTION);
		registerReceiver(receiver, filter);
		super.onResume();
	}
}
