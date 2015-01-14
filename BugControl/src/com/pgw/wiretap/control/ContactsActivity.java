package com.pgw.wiretap.control;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pgw.wiretap.control.domain.Contacter;
import com.pgw.wiretap.control.domain.MsgType;
import com.pgw.wiretap.control.service.WSService;
import com.pgw.wiretap.control.utils.JsonUtils;
import com.pgw.wiretap.control.utils.MsgUtils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ContactsActivity extends BaseActivity implements
		OnItemClickListener {
	private static final String TAG = "ContactsActivity";
	private ListView contacts_list;
	List<String> dataList = new ArrayList<String>();
	private String getter;
	private String msg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress_dialog_layout);
		
		getter = getIntent().getStringExtra("getter");
		MsgUtils.sendToService(getApplicationContext(), getter, MsgType.READ_CONTACTS);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String number = dataList.get(position);
		Intent intentData = new Intent();
		intentData.putExtra("number", number);
		ContactsActivity.this.setResult(0, intentData);
		ContactsActivity.this.finish();

	}

	@Override
	protected void receive(int type, String data) {
		Log.v(TAG, msg);

		if (type == MsgType.CONTACTS_LIST) {
			JSONArray bodyArray = JSONArray.parseArray(data);
			for (int i = 0; i < bodyArray.size(); i++) {
				Contacter contacter = JsonUtils.json2obj(
						bodyArray.getString(i), Contacter.class);
				dataList.add(contacter.getName() + ": " + contacter.getPhoneNum());
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					getApplicationContext(),
					android.R.layout.simple_expandable_list_item_1, dataList);
			ContactsActivity.this.setContentView(R.layout.contacts);
			getter = getIntent().getExtras().getString("getter");
			contacts_list = (ListView) findViewById(R.id.lv_contacts_list);

			contacts_list.setAdapter(adapter);
			contacts_list.setOnItemClickListener(this);
		}
	}
}
