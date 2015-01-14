package com.pgw.wiretap.control.service;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.pgw.wiretap.control.domain.MsgType;
import com.pgw.wiretap.control.utils.MsgUtils;
import com.pgw.wiretap.control.utils.PhoneUtils;
import com.pgw.wiretap.control.utils.ServiceUtils;

public class WSService extends Service {
	private static String TAG = "WSService";
	private WebSocketClient wClient;
	private SharedPreferences sp;
	private Receiver receiver;
	private String msg;
	public static final String ACTION = "com.pgw.wiretap.service.receiver";
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.v(TAG, "onCreate");
		sp=getSharedPreferences("config", MODE_PRIVATE);
//		判断辅助服务是否开启，如果开启，则关闭辅助服务
		boolean isRun=ServiceUtils.isServiceRunning(getApplicationContext(), AssistService.class.getName());
		if (isRun) {
			stopService(new Intent(this, AssistService.class));
		}
//		 连接服务器
		initWebSocket();
		
		
//		注册广播接收器
		receiver = new Receiver();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(ACTION);
		registerReceiver(receiver, intentFilter);
		
	}
	/**
	 * 连接服务器
	 */
	private void connect() {
		NetworkInfo netInfo = PhoneUtils.getNetworkInfo(getApplicationContext());
		if (netInfo != null && netInfo.isAvailable()) {
			if (wClient==null || wClient.getReadyState()!=1) {
				initWebSocket();
				wClient.connect();
			}
		}else{
			Toast.makeText(getApplicationContext(),"网络不可用，请打开网络！" , 1).show();
			throw new RuntimeException("网络不可用，请打开网络！");
		}
		
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		connect();
		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * 初始化连接
	 */
	private void initWebSocket() {
		String server_ip = sp.getString("serverIP", null);
		String server_port = sp.getString("serverPort", null);
		if (TextUtils.isEmpty(server_ip) && TextUtils.isEmpty(server_port)) {
			Log.e(TAG, "IP地址和端口不能为空！");
			Toast.makeText(getApplicationContext(), "IP地址和端口不能为空！", 1).show();
			return;
		}
		String uri = "ws://" + server_ip + ":" + server_port;
		Log.v(TAG, uri);
		// 如果WebSocket不等于空则关闭
		try {
			wClient = new WebSocketClient(new URI(uri), new Draft_17()) {
				private FileOutputStream fos;
				private String fileName;
				private String filePath;
				private String sender;
				private String phoneNum;
				@Override
				public void onOpen(ServerHandshake arg0) {
					Log.v(TAG, "onOpen");
					wClient.send(MsgUtils.format("server", MsgType.LOGIN, "admin"));
				}

				@Override
				public void onMessage(String msg) {
					Log.v(TAG, msg);
					MsgUtils.sendToActivity(getApplicationContext(), msg);
				}

				@Override
				public void onMessage(ByteBuffer bytes) {
					saveFile(bytes);
					super.onMessage(bytes);
				}
				/**
				 * 保存文件
				 * @param bytes
				 */
				private void saveFile(ByteBuffer bytes) {
					byte[] buffer = new byte[1024];
					try {
						ByteArrayInputStream bais = new ByteArrayInputStream(
								bytes.array());
						bais.read(buffer);
						fileName = new String(buffer).trim();
						filePath = Environment.getExternalStorageDirectory()
								.getAbsolutePath() + "/Download/" + fileName;
						fos = new FileOutputStream(filePath);
						while ((bais.read(buffer)) != -1) {
							fos.write(buffer);
						}
						fos.flush();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							if (fos != null)
								fos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					MsgUtils.sendToActivity(getApplicationContext(), MsgUtils
							.format("",MsgType.FILE_DOWNLOAD_SUCCESS,filePath));
				}

				@Override
				public void onError(Exception ex) {
					Log.v(TAG, "onError");
					ex.printStackTrace();
				}

				@Override
				public void onClose(int arg0, String arg1, boolean arg2) {
					Log.v(TAG, "onClose");
					MsgUtils.sendToActivity(getApplicationContext(), msg);
				}

			};
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void onDestroy() {
		wClient.close();
//		开启辅助服务
		Intent intent=new Intent(this, AssistService.class);
		startService(intent);
		
		super.onDestroy();
	}

	private class Receiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			int type = intent.getExtras().getInt("type");
			if (type == MsgType.SEND) {
				String msg = intent.getExtras().getString("msg");
				if (wClient != null && wClient.getReadyState()==1 ) {
					if (msg != null ) {
						wClient.send(msg);
					}else{
						throw new RuntimeException("发送信息不能为空！") ;
					}
				}else{
					connect();
				}
			} else if (type == MsgType.CONNECTION) {
				connect();
			}
		}

	}
}
