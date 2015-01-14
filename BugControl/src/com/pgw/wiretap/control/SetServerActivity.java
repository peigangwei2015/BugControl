package com.pgw.wiretap.control;

import com.alibaba.fastjson.JSONObject;
import com.pgw.wiretap.control.domain.MsgType;
import com.pgw.wiretap.control.service.WSService;
import com.pgw.wiretap.control.utils.MsgUtils;
import com.pgw.wiretap.control.utils.ServiceUtils;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SetServerActivity extends BaseActivity {
	private EditText et_ip, et_port;
	private String regIP = "^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_server);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_port = (EditText) findViewById(R.id.et_port);
		et_ip.setText(sp.getString("serverIP", ""));
		et_port.setText(sp.getString("serverPort", ""));
		
	}

	public void done(View v) {
		String serverIP = et_ip.getText().toString().trim();
		String serverPort = et_port.getText().toString().trim();
		if (TextUtils.isEmpty(serverIP) && TextUtils.isEmpty(serverPort)) {
			Toast.makeText(getApplicationContext(), "IP地址和端口都不能为空！请输入。", 1)
					.show();
			return;
		}
		if (!serverIP.matches(regIP)) {
			Toast.makeText(getApplicationContext(), "IP地址格式不正确！请重新输入。", 1)
					.show();
			et_ip.setText("");
			return;
		}
		// 保存IP地址和端口号
		Editor editor = sp.edit();
		editor.putString("serverIP", serverIP);
		editor.putString("serverPort", serverPort);
		editor.commit();

		// 判断是否开启服务
		boolean isRun = ServiceUtils.isServiceRunning(getApplicationContext(),
				WSService.class.getName());
		setContentView(R.layout.progress_dialog_layout);
		if (isRun) {
			MsgUtils.connecting(getApplicationContext());
		} else {
			Intent intent = new Intent(this, WSService.class);
			startService(intent);
		}

	}

	@Override
	protected void receive(int type, String data) {
		switch (type) {
		case MsgType.LOGIN_SUCCESS:
//			登陆成功
			Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
			startActivity(intent);
			break;
		case MsgType.ON_ERROR:
//			连接出错
			Toast.makeText(getApplicationContext(), "连接出错啦！！！", 1).show();
			break;

		}

	}

}
