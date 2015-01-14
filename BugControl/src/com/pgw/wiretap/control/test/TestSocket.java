package com.pgw.wiretap.control.test;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.pgw.wiretap.control.utils.SocketUtils;

import android.test.AndroidTestCase;

public class TestSocket extends AndroidTestCase {
		public void testConnect() throws NumberFormatException, UnknownHostException, IOException{
			SocketUtils.getSockcet(getContext());
		}

}
