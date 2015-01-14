package com.pgw.wiretap.control.ui;

import com.pgw.wiretap.control.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeItemView extends LinearLayout {
	private ImageView iv_icon;
	private TextView tv_text;
	public HomeItemView(Context context) {
		super(context);
		initView(context);
	}

	private void initView(Context context) {
		View view=View.inflate(context,R.layout.home_item, this);
		iv_icon=(ImageView) view.findViewById(R.id.iv_item_icon);
		tv_text=(TextView) view.findViewById(R.id.tv_item_text);
	}

	public HomeItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
		
	}

	@SuppressLint("NewApi")
	public HomeItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

}
