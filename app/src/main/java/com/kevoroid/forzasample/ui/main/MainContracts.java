package com.kevoroid.forzasample.ui.main;

import android.view.View;
import com.kevoroid.forzasample.models.Teams;

import java.util.List;

public interface MainContracts {

	interface Views {

		void showLoading();

		void hideLoading();

		void showErr();

		void openTeamDetails();

		View getMainLayout();

		void setupRecyclerView(List<Teams> data);

	}

	interface Actions {

		void fetchTeams();

	}
}
