package com.pgw.wiretap.control.utils;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;

public class ServiceUtils {
	/**
	 * 判断一个服务是否正在运行
	 * @param context 上下文
	 * @param name 服务的类名
	 * @return 如果运行返回true，否则返回false;
	 */
	public static boolean isServiceRunning(Context context,String name){
		ActivityManager am = (ActivityManager)context.getSystemService(context.ACTIVITY_SERVICE);
		List<RunningServiceInfo> infos = am.getRunningServices(100);
		for (RunningServiceInfo info : infos) {
			if (info.service.getClassName().equals(name)) {
				return true;
			}
		}
		return false;
	}
}
