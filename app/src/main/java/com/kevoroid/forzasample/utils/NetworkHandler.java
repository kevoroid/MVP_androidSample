package com.kevoroid.forzasample.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkHandler {

	public static boolean internetAvailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = null;
		if (cm != null) {
			activeNetwork = cm.getActiveNetworkInfo();
		}
		return activeNetwork != null && activeNetwork.isConnected();
	}
}
