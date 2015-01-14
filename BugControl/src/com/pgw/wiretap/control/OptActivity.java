package com.pgw.wiretap.control;

import com.alibaba.fastjson.JSONObject;
import com.pgw.wiretap.control.domain.MsgType;
import com.pgw.wiretap.control.service.WSService;
import com.pgw.wiretap.control.utils.JsonUtils;
import com.pgw.wiretap.control.utils.MsgUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class OptActivity extends BaseActivity implements OnCheckedChangeListener {
	private String TAG = "WifiCtrlActivity";
	private String msg;
	private Switch st_wifi, st_notification;
	private String getter;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.opt);
		getter = getIntent().getStringExtra("getter");

		st_wifi = (Switch) findViewById(R.id.st_opt_wifi);
		st_notification = (Switch) findViewById(R.id.st_opt_notifition);

		st_wifi.setOnCheckedChangeListener(this);
		st_notification.setOnCheckedChangeListener(this);

	}



	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		switch (buttonView.getId()) {
		case R.id.st_opt_wifi:
			if (isChecked) {
				MsgUtils.sendToService(getApplicationContext(), getter,  MsgType.OPEN_WIFI);
			} else {
				MsgUtils.sendToService(getApplicationContext(), getter,  MsgType.CLOSE_WIFI);
			}
			break;
		case R.id.st_opt_notifition:
			if (isChecked) {
				MsgUtils.sendToService(getApplicationContext(), getter,  MsgType.OPEN_NOTIFICATION);
			} else {
				MsgUtils.sendToService(getApplicationContext(), getter,  MsgType.CLOSE_NOTIFICATION);
			}
			break;
		}
	}



	@Override
	protected void receive(int type,String data) {
		Log.v(TAG, msg);
		switch (type) {
		case MsgType.OPEN_WIFI_SUCCESS:
			Toast.makeText(getApplicationContext(), "Wifi开启成功！", 0).show();
			break;
		case MsgType.CLOSE_WIFI_SUCCESS:
			Toast.makeText(getApplicationContext(), "Wifi关闭成功！", 0).show();
			break;
		case MsgType.OPEN_NOTIFICATION_SUCCESS:
			Toast.makeText(getApplicationContext(), "通知开启成功！", 0).show();
			break;
		case MsgType.CLOSE_NOTIFICATION_SUCCESS:
			Toast.makeText(getApplicationContext(), "通知关闭成功！", 0).show();
			break;
		}		
	}
}
