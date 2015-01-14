package com.pgw.wiretap.control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pgw.wiretap.control.domain.FileInfo;
import com.pgw.wiretap.control.domain.MsgHeader;
import com.pgw.wiretap.control.domain.MsgType;
import com.pgw.wiretap.control.utils.JsonUtils;
import com.pgw.wiretap.control.utils.MsgUtils;

public class VoiceActivity extends BaseActivity implements OnClickListener,
		OnItemClickListener, android.content.DialogInterface.OnClickListener {
	private static final String TAG = "VoiceCtrlActivity";
	private Button bt_start = null;
	private Button bt_stop = null;
	private Button bt_update = null;
	private Builder builder;
	private AlertDialog descDialog;
	private EditText et_filename = null;
	private String getter;
	private ListView lv_file_list = null;
	private String msg;
	private AlertDialog optDialog;

	private Map<String, Object> rowData;

	private String fromatDate(long ldate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日hh:mm:ss");
		return df.format(new Date(ldate));
	}

	private String fromatFileSize(long lsize) {
		long kb = lsize / 1024;
		return kb + "";
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if (dialog == optDialog) {
			switch (which) {
			case 0:
				Toast.makeText(getApplicationContext(),
						"准备下载：" + rowData.get("path"), 0).show();
				break;
			case 1:
				CharSequence[] items = new CharSequence[] {
						"文件名：" + rowData.get("name"),
						"路径：" + rowData.get("path"),
						"大小：" + fromatFileSize((Long) rowData.get("size"))
								+ " KB",
						"最后修改时间：" + fromatDate((Long) rowData.get("date")) };
				builder.setItems(items, this);
				descDialog = builder.create();
				descDialog.show();
				break;
			case 2:
				break;

			default:
				break;
			}
		}

	}

	@Override
	public void onClick(View v) {
		String filename = et_filename.getText().toString().trim();
		switch (v.getId()) {
		case R.id.bt_voice_start:
			break;
		case R.id.bt_voice_stop:
			break;
		case R.id.bt_voice_update:
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.voice);
		getter = getIntent().getExtras().getString("getter");
		et_filename = (EditText) findViewById(R.id.et_voice_save_filename);
		bt_start = (Button) findViewById(R.id.bt_voice_start);
		bt_start.setOnClickListener(this);
		bt_stop = (Button) findViewById(R.id.bt_voice_stop);
		bt_stop.setOnClickListener(this);
		bt_update = (Button) findViewById(R.id.bt_voice_update);
		bt_update.setOnClickListener(this);
		lv_file_list = (ListView) findViewById(R.id.lv_voice_file_list);
		lv_file_list.setOnItemClickListener(this);


	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View v, int position,
			long arg3) {
		rowData = (Map<String, Object>) adapter.getItemAtPosition(position);
		CharSequence[] items = new CharSequence[] { "下载", "查看详情", "删除" };
		builder = new AlertDialog.Builder(VoiceActivity.this);
		builder.setItems(items, this);
		optDialog = builder.create();
		optDialog.show();

	}


	@Override
	protected void receive(int type,String data) {
		if (type == MsgType.START_VOICE_RECODER_SUCCESS) {
			Toast.makeText(getApplicationContext(), "已成功开启录音，正在录制中。。。", 0)
					.show();
		} else if (type == MsgType.STOP_VOICE_RECODER_SUCCESS) {
			Toast.makeText(getApplicationContext(), "已成功关闭录音", 0).show();
		} else if (type == MsgType.FILE_LIST) {
			ArrayList<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			List<FileInfo> fileInfoList = JsonUtils.json2list(
					data, FileInfo.class);
			for (FileInfo fileInfo : fileInfoList) {
				Map<String, Object> row = new HashMap<String, Object>();
				row.put("icon", R.drawable.mp3_icon);
				row.put("name", fileInfo.getName());
				row.put("path", fileInfo.getPath());
				row.put("size", fileInfo.getSize());
				row.put("date", fileInfo.getDate());
				dataList.add(row);
			}

		} else if (type == MsgType.FILE_DEL_SUCCESS) {
			Toast.makeText(getApplicationContext(), "文件删除成功！正在更新列表，请稍后...",
					0).show();
		} else if (type == MsgType.FILE_NOT_EXIST) {
			Toast.makeText(getApplicationContext(), "文件不存在，删除失败！", 0)
					.show();
		} else if (type == MsgType.FILE_DOWNLOAD_SUCCESS) {
			String fileName = data;
			Toast.makeText(getApplicationContext(), fileName + "文件下载成功！", 0)
					.show();
		}		
	}
}
