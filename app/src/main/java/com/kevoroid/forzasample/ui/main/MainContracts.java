package com.kevoroid.forzasample.ui.main;

import android.view.View;
import com.kevoroid.forzasample.models.Teams;

import java.util.List;

public interface MainContracts {

	interface Views {

		void showLoading();

		void hideLoading();

		void showErr();

		void showTeams();

		void openTeamDetails(int id);

		void setupRecyclerView(List<Teams> data);

		View getMainLayout();
	}

	interface Actions {

		void fetchTeams();

		void fetchTeamDetail(int id);

	}
}
