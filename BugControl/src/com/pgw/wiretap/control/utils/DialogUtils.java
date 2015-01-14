package com.pgw.wiretap.control.utils;

import com.pgw.wiretap.control.mylistener.ConfimDialogListener;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogUtils {

	/**
	 * 显示一个列表对话框
	 * @param context 上下文
	 * @param title 标题
	 * @param items 行数据
	 * @param listener 监听回调
	 * @return
	 */
	public static void showItems(Context context,String title, CharSequence[] items,
			android.content.DialogInterface.OnClickListener listener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setItems(items, listener);
		AlertDialog meunDialog = builder.create();
		meunDialog.show();
	}
	/**
	 * 显示一个确认对话框
	 * @param context 上下文
	 * @param title 标题
	 * @param msg 提示信息
	 * @param listener 回调借口
	 * @return
	 */
	public static void showConfirm(Context context,String title,String msg,final ConfimDialogListener listener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
					listener.onCancle(dialog);
			}
		});
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				 listener.onConifm(dialog);
			}
		});
		AlertDialog conDialog = builder.create();
		conDialog.show();
	}
}
