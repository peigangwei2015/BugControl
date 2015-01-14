package com.pgw.wiretap.control;

import com.alibaba.fastjson.JSONObject;
import com.pgw.wiretap.control.domain.MsgType;
import com.pgw.wiretap.control.service.WSService;
import com.pgw.wiretap.control.utils.ServiceUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

public class BootActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_boot);
		new Thread(){
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//				判断是否设置服务器IP，如果未设置，跳转至设置界面
				String serverIP=sp.getString("serverIP", null);
				if (TextUtils.isEmpty(serverIP)) {
					 Intent intent = new Intent(BootActivity.this, SetServerActivity.class);
					startActivity(intent);
					finish();
					return;
				}
				if (ServiceUtils.isServiceRunning(getApplicationContext(), WSService.class.getName())) {
					 Intent intent = new Intent(BootActivity.this, HomeActivity.class);
						startActivity(intent);
						finish();
				}else{
//				开启连接服务器服务
					Intent intent=new Intent(BootActivity.this, WSService.class);
					startService(intent);
				}
			};
		}.start();

		
	}
	@Override
	protected void receive(int type,String data) {
		switch (type) {
		case MsgType.LOGIN_SUCCESS://登陆成功跳转至主界面
			Intent intent=new Intent(this, HomeActivity.class);
			startActivity(intent);
			finish();
			break;
		case MsgType.ON_ERROR://登陆成功跳转至主界面
			Toast.makeText(getApplicationContext(), "连接服务器出错，请稍后再试！", 1).show();
			finish();
			break;

		}
		
	}
}
