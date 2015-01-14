package com.pgw.wiretap.control.utils;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class SocketUtils {
	
	public static Socket getSockcet(Context context) throws NumberFormatException, UnknownHostException, IOException{
		SharedPreferences sp=context.getSharedPreferences("config", context.MODE_PRIVATE);
		String server_ip = sp.getString("serverIP", null);
		String server_port = sp.getString("serverPort", null);
		if (TextUtils.isEmpty(server_ip) && TextUtils.isEmpty(server_port)) {
			Toast.makeText(context, "IP地址和端口不能为空！", 1).show();
			return null;
		}
		new Thread(){
			public void run() {
				
				try {
					Socket socket=new Socket("192.168.1.101", 8888);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		}.start();
		return null;
	}
	

}
