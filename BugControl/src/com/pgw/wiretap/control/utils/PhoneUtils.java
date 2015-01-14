package com.pgw.wiretap.control.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;


public class PhoneUtils {

	/**
	 * 获取手机位置信息
	 * @param context
	 * @return
	 */
	public static Map<String,Integer> getLocation(Context context) {
		Map<String,Integer> map=new HashMap<String, Integer>();
		TelephonyManager mTelephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		// 返回值MCC + MNC
		String operator = mTelephonyManager.getNetworkOperator();
		int mnc = Integer.parseInt(operator.substring(3));
		map.put("mnc", mnc);
		// 中国移动和中国联通获取LAC、CID的方式
		GsmCellLocation location = (GsmCellLocation) mTelephonyManager
				.getCellLocation();
		int lac = location.getLac();
		int cell = location.getCid();
		map.put("lac", lac);
		map.put("cell", cell);
		return map;
	}
//	
	public static String getMyNumber(Context context){
		TelephonyManager phoneMgr=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		String num=phoneMgr.getLine1Number(); //txtPhoneNumber是一个EditText 用于显示手机号
		if (num!=null && !num.trim().equals("")) {
			return num;
		}else{
			return getMyDriverId(context);
		}
	}
	
	public static String getMyDriverId(Context context){
		TelephonyManager phoneMgr=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		return  phoneMgr.getDeviceId(); 
	}
	public static NetworkInfo getNetworkInfo(Context context) {
		// 获取网络连接状况
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(context.CONNECTIVITY_SERVICE);
		return cm.getActiveNetworkInfo();
	}
	
	

}
