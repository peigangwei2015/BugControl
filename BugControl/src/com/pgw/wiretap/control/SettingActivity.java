package com.pgw.wiretap.control;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
	}
	/**
	 * 开启设置服务器
	 * @param v
	 */
	public void setServer(View v){
		Intent intent =new Intent(this, SetServerActivity.class);
		startActivity(intent);
	}
}
