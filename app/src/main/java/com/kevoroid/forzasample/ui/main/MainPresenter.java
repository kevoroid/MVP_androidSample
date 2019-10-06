package com.kevoroid.forzasample.ui.main;

import androidx.annotation.NonNull;
import com.kevoroid.forzasample.api.ApiEndpoints;
import com.kevoroid.forzasample.api.RetroMaster;
import com.kevoroid.forzasample.models.Team;
import com.kevoroid.forzasample.models.Teams;
import com.kevoroid.forzasample.repo.MasterRepo;

import java.util.List;

public class MainPresenter implements MainContracts.Actions, MasterRepo.MasterRepoCallbacks {

	private MainContracts.Views views;

	private ApiEndpoints apiEndpoints = RetroMaster.getInstance().create(ApiEndpoints.class);
	private MasterRepo masterRepo;

	MainPresenter(@NonNull MainContracts.Views views) {
		this.views = views;
		masterRepo = MasterRepo.getINSTANCE(this, apiEndpoints);
	}

	@Override
	public void fetchTeams() {
		views.showLoading();
		masterRepo.fetchTeamsFromApi();
	}

	@Override
	public void fetchTeamDetail(int id) {
		views.showLoading();
		masterRepo.fetchTeamDetail(id);
	}

	@Override
	public void onDataReturned(List<Teams> data) {
		if (data != null) {
			System.out.println("MainPresenter.onDataReturned List -- " + data);
			views.setupRecyclerView(data);
			views.hideLoading();
		} else {
			onDataError();
		}
	}

	@Override
	public void onDataReturned(Team data) {
		System.out.println("MainPresenter.onDataReturned Team -- " + data);
		if (data != null) {
			views.openTeamDetails(data);
			views.hideLoading();
		} else {
			onDataError();
		}
	}

	@Override
	public void onDataError() {
		views.hideLoading();
		views.showErr();
	}


}
