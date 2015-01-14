package com.pgw.wiretap.control;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.pgw.wiretap.control.domain.Location;
import com.pgw.wiretap.control.domain.MsgType;
import com.pgw.wiretap.control.service.WSService;
import com.pgw.wiretap.control.utils.JsonUtils;
import com.pgw.wiretap.control.utils.MsgUtils;

public class LocationActivity extends BaseActivity implements OnClickListener {

	private Button bt_start;
	private Button bt_stop;
	private BaiduMap mBaiduMap;
	private MapView mMapView;
	private String TAG = "LocationCtrlActivity";

	private boolean isFirstLoc = true;
	private String msg;
	private String getter;;

	public void setLocation(Location loc) {
		MyLocationData locData = new MyLocationData.Builder()
				.accuracy(loc.getRadius())
				// 此处设置开发者获取到的方向信息，顺时针0-360
				.direction(loc.getDirection()).latitude(loc.getLatitude())
				.longitude(loc.getLongitude()).build();
		// 设置定位数据
		mBaiduMap.setMyLocationData(locData);
		// 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
		// mCurrentMarker = BitmapDescriptorFactory
		// .fromResource(R.drawable.icon_geo);
		MyLocationConfiguration config = new MyLocationConfiguration(
				MyLocationConfiguration.LocationMode.NORMAL, true, null);
		mBaiduMap.setMyLocationConfigeration(config);
		if (isFirstLoc) {
			isFirstLoc = false;
			LatLng ll = new LatLng(loc.getLatitude(), loc.getLongitude());
			MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
			mBaiduMap.animateMapStatus(u);
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.location);
		setTitle("定位服务");
		getter = getIntent().getExtras().getString("getter");

		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		mBaiduMap.setMyLocationEnabled(true);
		bt_start = (Button) findViewById(R.id.bt_location_start);
		bt_start.setOnClickListener(this);
		bt_stop = (Button) findViewById(R.id.bt_location_stop);
		bt_stop.setOnClickListener(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
		mMapView.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		mMapView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		mMapView.onResume();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_location_start:
			MsgUtils.sendToService(getApplicationContext(), getter, MsgType.START_LOCATION);
			break;
		case R.id.bt_location_stop:
			MsgUtils.sendToService(getApplicationContext(), getter, MsgType.STOP_LOCATION);
			break;
		}
	}

	@Override
	protected void receive(int type,String data) {
		Log.v(TAG, msg);
		if (type == MsgType.LOCATION) {
			Location location = JsonUtils.json2obj(data, Location.class);
			setLocation(location);
		} else if (type == MsgType.START_LOCATION_SUCCESS) {
			Toast.makeText(getApplicationContext(), "开始为你定位，请稍后...", 0)
					.show();
		} else if (type == MsgType.STOP_LOCATION_SUCCESS) {
			Toast.makeText(getApplicationContext(), "定位已关闭！", 0).show();
		}
		
	}
}
