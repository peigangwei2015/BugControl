package com.pgw.wiretap.control.myadapter;

import android.content.DialogInterface;

import com.pgw.wiretap.control.mylistener.ConfimDialogListener;
/**
 * ConfimDialogListener接口的默认实现
 */
public class ConfimDialogAdapter implements ConfimDialogListener {

	@Override
	public void onConifm(DialogInterface dialog) {

	}

	@Override
	public void onCancle(DialogInterface dialog) {
		dialog.dismiss();
	}

}
