package com.kevoroid.forzasample.ui.main;

import android.view.View;

public interface MainContracts {

	interface Views {

		void showErr();

		void openTeamDetails();

		View getMainLayout();

	}

	interface Actions {

		void fetchTeams();

	}
}
