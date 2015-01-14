package com.pgw.wiretap.control;

import java.io.File;

import android.content.BroadcastReceiver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.pgw.wiretap.control.domain.MsgHeader;
import com.pgw.wiretap.control.domain.MsgType;
import com.pgw.wiretap.control.utils.MsgUtils;

public class CameraActivity extends BaseActivity implements OnClickListener {
	private RadioGroup rg_camera;
	private RadioButton rb_front;
	private RadioButton rb_back;
	private Button bt_captrue;
	private Button bt_save;
	private ImageView iv_picture;
	private String getter;
	private String msg;
	private BroadcastReceiver mReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);
		// 得到接受人
		getter = getIntent().getExtras().getString("getter");

		rg_camera = (RadioGroup) findViewById(R.id.rg_cramera_cramera);
		rb_front = (RadioButton) findViewById(R.id.rb_front_camera);
		rb_back = (RadioButton) findViewById(R.id.rb_back_camera);
		bt_captrue = (Button) findViewById(R.id.bt_camera_capture);
		bt_save = (Button) findViewById(R.id.bt_camera_save_file);
		iv_picture = (ImageView) findViewById(R.id.iv_camera_picture);

		bt_captrue.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (rb_back.isChecked()) {
			MsgUtils.sendToService(getApplicationContext(), getter, MsgType.CAMERA, 1);
		} else {
			MsgUtils.sendToService(getApplicationContext(), getter, MsgType.CAMERA, 0);
		}
	}


	@Override
	protected void receive(int type, String data) {
		String fileName=data;
		if (type == MsgType.FILE_DOWNLOAD_SUCCESS) {
			Toast.makeText(getApplicationContext(), fileName + "下载成功！",
					Toast.LENGTH_LONG).show();
			File file = new File(fileName);
			if (file.exists()) {
				// Bitmap
				// bitmap=BitmapFactory.decodeFile(file.getAbsolutePath(),op);
				Options opts = new Options();
				opts.inJustDecodeBounds = true;
				BitmapFactory.decodeFile(fileName, opts);
				int imgWidth = opts.outWidth;
				int imgHieght = opts.outHeight;

				int winWidth = getWindowManager().getDefaultDisplay()
						.getWidth();
				int winHeight = getWindowManager().getDefaultDisplay()
						.getHeight();
				int sacel = 1;
				int xSacel = imgWidth / winWidth;
				int ySacel = imgHieght / winHeight;

				if (xSacel > ySacel && xSacel >= 1) {
					sacel = xSacel;
				} else if (ySacel > xSacel && ySacel >= 1) {
					sacel = ySacel;
				}
				Toast.makeText(getApplicationContext(), imgWidth + "", 1)
						.show();
				opts.inSampleSize = sacel;
				opts.inJustDecodeBounds = false;

				Bitmap bitmap = BitmapFactory.decodeFile(fileName, opts);

				iv_picture.setImageBitmap(bitmap);
			}

		}		
	}
}
