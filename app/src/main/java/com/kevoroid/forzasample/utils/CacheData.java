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

	private static final String TEAMS_CACHE_OBJECT_NAME = "teams.obj";
	private static final String TEAM_DETAIL_CACHE_OBJECT_NAME = "team_";

	private CacheData() {
		forzaApplication = new ForzaApplication();
	}

	public static synchronized CacheData getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CacheData();
		}
		return INSTANCE;
	}

	public void writeTeamsToCache(Object object) {
		try {
			FileOutputStream fos = forzaApplication.getContext().openFileOutput(TEAMS_CACHE_OBJECT_NAME, Context.MODE_PRIVATE);
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
			File file = new File(forzaApplication.getContext().getFilesDir(), TEAMS_CACHE_OBJECT_NAME);
			if (file.exists()) {
				Calendar time = Calendar.getInstance();
				time.add(Calendar.MINUTE, -10);
				Date lastModified = new Date(file.lastModified());
				if (lastModified.before(time.getTime())) {
					file.delete();
				}
			}

			FileInputStream fis = forzaApplication.getContext().openFileInput(TEAMS_CACHE_OBJECT_NAME);
			ObjectInputStream is = new ObjectInputStream(fis);
			teamsObject = String.valueOf(is.readObject());
			is.close();
			fis.close();
		} catch (Exception e) {
			Log.d(TAG, "readFromCache: " + e.getLocalizedMessage());
		}

		return teamsObject;
	}

	public void writeTeamDetailToCache(Object object, int id) {
		try {
			FileOutputStream fos = forzaApplication.getContext().openFileOutput(getTeamDetailCacheFileName(id), Context.MODE_PRIVATE);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(object);
			os.close();
			fos.close();
		} catch (Exception e) {
			Log.d(TAG, "readFromCache: " + e.getLocalizedMessage());
		}
	}

	public String readTeamDetailFromCache(int id) {
		String teamDetailObject = null;
		try {
			File file = new File(forzaApplication.getContext().getFilesDir(), getTeamDetailCacheFileName(id));
			if (file.exists()) {
				Calendar time = Calendar.getInstance();
				time.add(Calendar.MINUTE, -10);
				Date lastModified = new Date(file.lastModified());
				if (lastModified.before(time.getTime())) {
					file.delete();
				}
			}

			FileInputStream fis = forzaApplication.getContext().openFileInput(getTeamDetailCacheFileName(id));
			ObjectInputStream is = new ObjectInputStream(fis);
			teamDetailObject = String.valueOf(is.readObject());
			is.close();
			fis.close();
		} catch (Exception e) {
			Log.d(TAG, "readFromCache: " + e.getLocalizedMessage());
		}

		return teamDetailObject;
	}

	private String getTeamDetailCacheFileName(int id) {
		return TEAM_DETAIL_CACHE_OBJECT_NAME + id + ".obj";
	}
}
