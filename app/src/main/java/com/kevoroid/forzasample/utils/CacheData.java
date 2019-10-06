package com.kevoroid.forzasample.utils;

import android.content.Context;
import android.util.Log;
import com.kevoroid.forzasample.ForzaApplication;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class CacheData {

	private static final String TAG = "CacheData";

	private static CacheData INSTANCE;
	private ForzaApplication forzaApplication;

	private CacheData() {
		forzaApplication = new ForzaApplication();
	}

	public static synchronized CacheData getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CacheData();
		}
		return INSTANCE;
	}

	public void writeTeamsToData(Object object) {
		try {
			FileOutputStream fos = forzaApplication.getContext().openFileOutput("teams.obj", Context.MODE_PRIVATE);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(object);
			os.close();
			fos.close();
		} catch (Exception e) {
			Log.d(TAG, "readFromCache: " + e.getLocalizedMessage());
		}
	}

	public String readTeamsFromCache() {
		String teamsObject = null;
		try {
			File file = new File(forzaApplication.getContext().getFilesDir(), "teams.obj");
			if (file.exists()) {
				Calendar time = Calendar.getInstance();
				time.add(Calendar.MINUTE, -10);
				Date lastModified = new Date(file.lastModified());
				if (lastModified.before(time.getTime())) {
					file.delete();
				}
			}

			FileInputStream fis = forzaApplication.getContext().openFileInput("teams.obj");
			ObjectInputStream is = new ObjectInputStream(fis);
			teamsObject = String.valueOf(is.readObject());
			is.close();
			fis.close();
		} catch (Exception e) {
			Log.d(TAG, "readFromCache: " + e.getLocalizedMessage());
		}

		return teamsObject;
	}
}
