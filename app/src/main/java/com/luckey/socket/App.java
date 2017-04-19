package com.luckey.socket;

import android.app.Activity;
import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * App
 * 
 * @author Tom
 * @date 2014/11/24 11:40
 * 
 */
public class App {


	public static final String SERVER_IP = "112.74.23.119"; //正式环境
//	public static final String SERVER_IP = "172.16.5.221";  //周承业
//	test

	public static final int SERVER_PORT = 9989; //正式环境端口
//	public static final int SERVER_PORT = 9988;
	
//	public static final String SMS_APPKEY = "18c18fa43c278";
//	public static final String SMS_APPSECRET = "111cbfc55936459af945a9cdeefb3446";
	public static final String SMS_APPKEY = "1c792e863caa4";
	public static final String SMS_APPSECRET = "4e0bf9ffaf410771581ea34d8ced149a";

	public static final String DB_NAME = "esop.db";
	public static final int DB_VERSION = 19;
	public static final int WAITTING_SECOND=20*1000;
	public static final String SELECTED_TAB="SELECTED_TAB";
public static final String ACTION_STOP_DATA_HANDLE_SERVICE = "com.yzh.sikalesmoking.googlebth.BleDataService.Stop";
	
public static final String SHOPPING_WEB_SITE="http://www.baidu.com";	
public static final String EVENT_REFRESH_LANGUAGE="EVENT_REFRESH_LANGUAGE";
	
	
public static final int Blood_Oxygen_Max=100;
public static final int Blood_Oxygen_Min=95;
public static final int Heart_Rate_Max=100;
public static final int Heart_Rate_Min=60;
	
	


	
	/** 甯搁噺 **/
	public static final String AQUERY_CACHE_PATH = File.separator
			+ "com.yzh.sikalesmoking" + File.separator;
	public static final String IMAGE_CACHE_PATH = AQUERY_CACHE_PATH
			+ "imagecache" + File.separator;
	public static final String CRASH_CACHE_PATH = AQUERY_CACHE_PATH
			+ "crashcache" + File.separator;
	public static final String SOCKET_NOTE_PATH = AQUERY_CACHE_PATH
			+ "socketnote" + File.separator;

	public static final String SEPARATOR_CHAR = "#@#";
	public static final String DECIMAL_FORMAT_STRING = "#####0.00";


	public static final String SYSTEM_ID = "kklapp";

	public final static String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";

	public static final String SYSTEM_TYPE = "aPhone";
	public static final String SYSTEM_UPDATEDATE_DEFAULT = "2014-01-01 00:00:00";

	public static final String PHONE_NUMBER_START = "+86";

	public static final int PAGE_SIZE = 8;

	public static class IntentKey {
		public static final String FOR_RESULT_FLAG = "for_result_flag";
		public static final String ACCOUNT = "account";
		public static final String PWD = "pwd";
		public static final String REGIEST_FLAG = "regiest_flag";

		public static final String SURVEY_ITEM_ID = "survey_item_id";
		public static final String ADDRESS_ITEM = "address_item";
		public static final String SELECTED_IMAGE_URI = "selected_image_uri";
		public static final String CLIP_IMAGE_DATA = "clip_image_data";

	}

	public static class BDOpenPlatform {
		public static final String MAP_KEY = "31C1A78A0EC8D634729CB90546913764414D7ABF";
	}

	public static class HandlerKey {
		public static final int HANDLER_MSG_TOTAL_CHANGE = 101;
		public static final int HANDLER_MSG_TIME_CHANGE = 102;
		public static final int HANDLER_MSG_CAN_CLICK = 103;
		public static final int HANDLER_MSG_CAN_NOT_CLICK = 104;
	}

	public static final String BIND_TYPE_PHONE = "phone",
			BIND_TYPE_MAIL = "mail";

	public static final String WEB_URL = "http://%1$s:%2$d";
	// Sina
	// 鐧婚檰甯愬彿锛歱iaggio1884@sina.com
	// 瀵嗙爜锛歾spiaggio1884

	// 鍟嗘埛PID
	public static final String ALIPAY_PARTNER = "2088511438023119";
	// 鍟嗘埛鏀舵璐﹀彿
	public static final String ALIPAY_SELLER = "zspiaggio@126.com";
	// 鍟嗘埛绉侀挜锛宲kcs8鏍煎紡
	public static final String ALIPAY_RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALxQrEcMpALdNdJBZpDruAnRBI6+Q6HAzUpYdYCwxiw+PydYsCSAeWgXqD5mwuCOotemVsvXwWHaiECJzC2RcY9Z7zuBBUwHnKXbvO4iHXziBve7lixknOyTsHuBsScbqOOUNiEdZFWduw1gkEBVbhhkEYh9bZRIsXY73hmHfNDPAgMBAAECgYEAh3bg9xs4wHawyNcTGRz3/tfFF4n2zN0M0HZh0HlZlWoRtBQwYZGpxB+Af6McfL0el1KpDWnfSq0RZpu5rO37aY6oKBL1QKLAqbbxrEZg+NDx8a8WWzxKGXwPGcos0AUqeEsEF7SaZiKMegY2UuU20GhZZxqSxWjFEnzhhff0ZMECQQDuduZE2Cu819r14t03jCFT6BVykFzRAZyLzjfw3FGpQJVBS+LH123Df8MlFJYlZ1KjzDWAnp/u5jJqEkKR47ltAkEAyim2AWokSeSRQNfVRWAOWXRn3KLh2d2o4N7M4IKsmezyPei1UPpGs5kZN0Rx3k3P4J/yIpecO4B8EMOzX3OpqwJBAIH0E+sirzs7mxzy5EF4QVVbngyP9bUFHs3ltQCy4GHPS0eJVm43zZ6DytIzVNvPYe7Xw7RzPY9Qrdq2VsbwhkUCQQDKHRNyrJxlJ9e0fVQijNsrwf5Ijqu/9iTt1LH9z4ciSMsS7AHCWMWwruKP56Tz/NVUnHQm825l6pme5RkC2A8LAkBIA8noFQbk8XOM2q4mmQ8Wo/i9dKBnG00ISBYAEKNf9nGeaqhiyjitMoBKjIVm9pJdWmwN4ukLtBaS8VAaUbju";

	// WebService method
	public static class WebService {
		public static final String MSGHEAD = "msgHead";
		public static final String CODE = "code";
		public static final String MSG = "msg";
		public static final String BODY = "body";

		public static final String ERROR = "error";
		public static final String SIGN = "sign";
		// socket杩炴帴瓒呮椂鏃堕棿
		public static final int SocketConnectTimeout = 6000;
		// socket鎺ユ敹瓒呮椂鏃堕棿
		public static final int SocketReceiveTimeout = 6000;

		/**webservice **/
		public static final int WEBSERVICE_PORT = 8080;// 8080
		public static final String WEBSERVICE_IP ="127.0.0.1";// 8080
		public static final int CONNECTION_WEB_PORT = 8080;// 8080


		// 妫�鏌ユ洿鏂�
		public static final String UPDATE_URL = "";

		// params
		public static final String PARAM_JSONSTR = "jsonStr";
		public static final String PARAM_FUNCTION_NAME = "func";
		public static final String PARAM_PARAMETERS = "params";

		// method
		//
		public static final String METHOD_ = "";

		// 娉ㄥ唽push璁惧
		public static final String METHOD_REGISTER_PUSH_DEVICE = "registerPushDevice";
		// 缁戝畾Device
		public static final String METHOD_BIND_USER_DEVICE = "bindUser2Device";
		// 瑙ｉ櫎缁戝畾Device
		public static final String METHOD_UNBIND_USER_DEVICE = "unbindUser2Device";
	}

	/**
	 * 鍒ゆ柇push鑳藉惁鍚姩
	 */
//	public static long getPushMsgUser(Context context) {
//		if (PreferenceData.Push.loadPushFlashFlag(context)) {
//			return PreferenceData.loadLoginAccount(context);
//		}
//		return 0;
//	}



//	public static String getDetailUrl(Context context) {
//		return getWebServerUrl(context) + "/byq/admin";
//	}

	public static class Contants {
		public static final String MSGKEY = "message";// 娑堟伅鐨刱ey
		public static final String BACKKEY_ACTION = "com.foxconn.positionplatform.backKey";// 杩斿洖閿彂閫佸箍鎾殑action
		public static final int NOTIFY_ID = 0x911;// 閫氱煡ID
		public static final String ACTION = "com.foxconn.positionplatform.message";// 娑堟伅骞挎挱action

		public static final String ACTION_GET_ALL_DEVICE_INFO = "com.foxconn.positionplatform.getalldeviceinfo";// 娑堟伅骞挎挱action
		public static final String BROADCAST_GET_ALL_DEVICE_INFO = "getalldeviceinfo";// 娑堟伅鐨刱ey

		public static final String ACTION_GET_REAL_TIME_TRACK = "com.foxconn.positionplatform.getrealtimetrack";// 娑堟伅骞挎挱action
		public static final String BROADCAST_GET_REAL_TIME_TRACK = "getrealtimetrack";// 娑堟伅鐨刱ey

	}

	public static class DownloadService {
		public static final String ACTION_DOWNLOAD_PROGRESS = "my_download_progress";
		public static final String ACTION_DOWNLOAD_SUCCESS = "my_download_success";
		public static final String ACTION_DOWNLOAD_FAIL = "my_download_fail";
	}

	// 娣诲姞 activity
	public static List<Activity> activityList = new ArrayList<Activity>();

	public static void addActivity(Activity activity) {
		App.activityList.add(activity);
	}

	public static void removeActivity(Activity activity)
	{
		App.activityList.remove(activity);
	}
	// 閫�鍑� activity
	public static void exitSystem(Context context) {
		// finish all activity
		for (int i = 0; i < App.activityList.size(); i++) {
			if (null != App.activityList.get(i)) {
				App.activityList.get(i).finish();
			}
		}

	}
}
