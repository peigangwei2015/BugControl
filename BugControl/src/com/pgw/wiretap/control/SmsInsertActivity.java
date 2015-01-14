package com.pgw.wiretap.control;


import com.pgw.wiretap.control.domain.MsgHeader;
import com.pgw.wiretap.control.domain.MsgType;
import com.pgw.wiretap.control.utils.MsgUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SmsInsertActivity extends Activity implements OnClickListener {
	protected static final String TAG = "SmsInsertActivity";
	private EditText et_sender, et_body;
	private Button bt_send, bt_select_contact;
	private String getter;
	private Long date;
	private String senderNumber;
	private String senderName;
	private String body;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert_sms);
		getter = getIntent().getStringExtra("getter");
		// 获取所有的组件
		et_sender = (EditText) findViewById(R.id.et_insert_sms_sender);
		et_body = (EditText) findViewById(R.id.et_insert_sms_body);
		bt_send = (Button) findViewById(R.id.bt_insert_sms_send);
		bt_select_contact = (Button) findViewById(R.id.bt_insert_sms_select_contact);
		// 给组件绑定事件
		bt_select_contact.setOnClickListener(this);
		bt_send.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_insert_sms_select_contact:
			Intent intent = new Intent(SmsInsertActivity.this,
					ContactsActivity.class);
			intent.putExtra("getter", getter);
			startActivityForResult(intent, 0);
			break;
		case R.id.bt_insert_sms_send:
			body = et_body.getText().toString().trim();
			if (body.equals("")) {
				Toast.makeText(getApplicationContext(), "内容不能为空！",
						Toast.LENGTH_LONG).show();
				return;
			}
			senderNumber = et_sender.getText().toString().trim();
			if ("".equals(senderNumber)) {
				Toast.makeText(getApplicationContext(), "发送人不能为空！", 1).show();
				return;
			}
			date = System.currentTimeMillis();

			int type=getIntent().getIntExtra("type", 0);
			String msg="";
			if (type==MsgType.SEND_SMS_TO_OTHER_PHONE) {

			}else if (type == MsgType.INSERT_SMS) {
				
			}
			Intent intent2 = new Intent(SmsInsertActivity.this,
					SmsActivity.class);
			
			intent2.putExtra("getter", getter);
			startActivity(intent2);

			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data != null && requestCode == 0) {
			String number = data.getStringExtra("number");
			String[] senderInfo = number.split(":");
			System.out.println(senderInfo[0]);
			et_sender.setText(senderInfo[1].replaceAll("-", ""));
			senderName=senderInfo[0];
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
