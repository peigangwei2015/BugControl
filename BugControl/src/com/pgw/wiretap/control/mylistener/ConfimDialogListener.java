package com.pgw.wiretap.control.mylistener;

import android.content.DialogInterface;

/**
 *确认对话框点击会掉
 */
public interface ConfimDialogListener {
	/**
	 * 当点击确认时调用
	 */
	void onConifm(DialogInterface dialog);
	/**
	 * 当点击取消时调用
	 */
	void onCancle(DialogInterface dialog);
}
