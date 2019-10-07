package com.kevoroid.forzasample.utils;

import com.kevoroid.forzasample.R;

public class NationalTeamHandler {

	public static int isNationalOrClub(boolean teamStatus) {
		if (teamStatus) {
			return R.string.label_national;
		} else {
			return R.string.label_club;
		}
	}
}
