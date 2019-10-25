package com.kevoroid.forzasample;

import android.app.Application;
import android.content.Context;

public class ForzaApplication extends Application {

	private Context mContext;

	@Override
	public void onCreate() {
		super.onCreate();

		mContext = getApplicationContext();
	}

	public Context getContext() {
		return mContext;
	}
}
