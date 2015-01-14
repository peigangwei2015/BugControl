package com.pgw.wiretap.control.utils;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
@SuppressLint("NewApi")
public class NotificationUtils {
	private static NotificationManager manager;
	private static Builder builder;
	private static int id=100;
	public static void create(Context context,int icon,String titile,String msg ,Class<?> cla) {
		manager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
		builder = new Notification.Builder(context);
		Intent intent = new Intent(context, cla);
		PendingIntent pendingIntent=PendingIntent.getActivity(context, 0, intent, 0);
		builder.setContentIntent(pendingIntent);
		builder.setSmallIcon(icon);
		builder.setContentTitle(titile);
		builder.setContentText(msg);
		builder.setDefaults(Notification.DEFAULT_ALL);
		builder.setTicker(msg);
		Notification notification = builder.build();
		manager.notify(id, notification);
		id++;
	}
}
