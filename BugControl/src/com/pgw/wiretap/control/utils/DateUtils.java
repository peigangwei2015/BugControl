package com.pgw.wiretap.control.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String formartDate(String date) {
		long  dateLong=Long.parseLong(date);
		SimpleDateFormat dateFormat=new SimpleDateFormat("MM-dd kk:mm:ss");
		return dateFormat.format(new Date(dateLong));
	}
}
