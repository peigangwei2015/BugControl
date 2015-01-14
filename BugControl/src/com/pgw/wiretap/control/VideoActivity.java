package com.pgw.wiretap.control;

import com.alibaba.fastjson.JSONObject;
import com.pgw.wiretap.control.domain.MsgHeader;
import com.pgw.wiretap.control.domain.MsgType;
import com.pgw.wiretap.control.utils.MsgUtils;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VideoActivity extends BaseActivity implements OnClickListener {
	private Button bt_start,bt_stop;
	private EditText et_file_name;
	private String getter;
	private String msg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video);
		getter=getIntent().getStringExtra("getter");
		bt_start=(Button) findViewById(R.id.bt_video_start);
		bt_stop=(Button) findViewById(R.id.bt_video_stop);
		bt_start.setOnClickListener(this);
		bt_stop.setOnClickListener(this);
		et_file_name=(EditText) findViewById(R.id.et_video_save_filename);
	}
	@Override
	public void onClick(View v) {
		String fileName=et_file_name.getText().toString().trim();
		switch (v.getId()) {
		case R.id.bt_video_start:
			if (fileName.equals("")) {
				Toast.makeText(getApplicationContext(), "文件名不能为空！", Toast.LENGTH_LONG).show();
				return;
			}
			break;
		case R.id.bt_video_stop:
			break;
		}
	}
	@Override
	protected void receive(int type, String data) {
		// TODO Auto-generated method stub
		
	}
}
