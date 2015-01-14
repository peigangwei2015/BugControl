package com.pgw.wiretap.control.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
/**
 * 此类启辅助作用，当WebSocket服务关闭时，此类辅助将其打开
 * @author Administrator
 *
 */
public class AssistService extends Service {
	@Override
	public void onCreate() {
		
		Intent intent=new Intent(this, WSService.class);
		startService(intent);
		super.onCreate();
	}
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
