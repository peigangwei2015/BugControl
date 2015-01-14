package com.pgw.wiretap.control.utils;

import android.content.Context;
import android.content.Intent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pgw.wiretap.control.BaseActivity;
import com.pgw.wiretap.control.domain.MsgType;
import com.pgw.wiretap.control.service.WSService;

public class MsgUtils {
	
	public static String format( int type) {
		return format("admin", type, null);
	}

	public static String format(String getter, int type) {
		return format(getter, type, null);
	}
	/**
	 * 默认发送给Admin
	 * @param type 信息类型
	 * @param obj 数据
	 * @return 格式化后
	 */
	public static String formatAdmin( int type, Object obj) {
		return format("admin", type, obj);
	}
	
	public static String format(String getter, int type, Object obj) {
		StringBuffer json = new StringBuffer();
		json.append("{");
		json.append("\"getter\":\"" + getter + "\",");
		json.append("\"type\":" + type);
		if (obj != null) {
			json.append(",");
			String strObj = JsonUtils.obj2json(obj);
			json.append("\"body\":" + strObj);
		}
		json.append("}");
		return json.toString();
	}

	/**
	 * 从服务中发广播到Activity
	 * 
	 * @param context
	 * @param msg
	 */
	public  static void sendToActivity(Context context, String msg) {
		Intent intent = new Intent();
		intent.setAction(BaseActivity.ACTION);
		intent.putExtra("msg", msg);
		context.sendBroadcast(intent);
	}
	
	/**
	 * 发送信息给自己
	 * @param context
	 * @param type 信息类型
	 */
	public static void sendToSelf(Context context, int type){
		sendToActivity(context, format(type));
	}
	/**
	 * 发送信息给自己
	 * @param context
	 * @param type 信息类型
	 * @param obj 数据
	 */
	public static void sendToSelf(Context context, int type,Object obj){
		sendToActivity(context, format("",type,obj));
	}
	
	/**
	 * 发送信息到服务器
	 * 
	 * @param context
	 * @param msg
	 *            要发送的信息
	 */
	public static void sendToService(Context context, String getter,int type,Object obj) {
		Intent intent = new Intent();
		intent.putExtra("msg", format(getter, type, obj));
		intent.putExtra("type", MsgType.SEND);
		intent.setAction(WSService.ACTION);
		context.sendBroadcast(intent);
	}
	


	/**
	 * 发送信息到服务器
	 * 
	 * @param context
	 * @param msg
	 *            要发送的信息
	 */
	public static void sendToService(Context context, String getter,int type) {
		sendToService(context, getter, type, null);
	}

	/**
	 * 发送链接服务器广播
	 * 
	 * @param context
	 */
	public static void connecting(Context context) {
		Intent intent = new Intent();
		intent.putExtra("type", MsgType.CONNECTION);
		intent.setAction(WSService.ACTION);
		context.sendBroadcast(intent);
	}

	/**
	 * 上传文件
	 * 
	 * @param context
	 * @param filePath
	 *            文件路径
	 */
	public static void fileUpload(Context context, String filePath) {
		Intent intent = new Intent();
		intent.putExtra("type", MsgType.FILE_UPLOAD);
		intent.putExtra("filePath", filePath);
		intent.setAction(WSService.ACTION);
		context.sendBroadcast(intent);
	}

}
