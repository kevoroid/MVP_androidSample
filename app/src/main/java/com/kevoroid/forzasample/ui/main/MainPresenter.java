package com.kevoroid.forzasample.ui.main;

import androidx.annotation.NonNull;
import com.kevoroid.forzasample.api.ApiEndpoints;
import com.kevoroid.forzasample.api.RetroMaster;
import com.kevoroid.forzasample.repo.MasterRepo;

public class MainPresenter implements MainContracts.Actions {

	private MainContracts.Views views;

	private ApiEndpoints apiEndpoints = RetroMaster.getInstance().create(ApiEndpoints.class);
	private MasterRepo masterRepo;

	public MainPresenter(@NonNull MainContracts.Views views) {
		this.views = views;
		masterRepo = MasterRepo.getINSTANCE(apiEndpoints);
	}

	@Override
	public void fetchTeams() {
		masterRepo.fetchTeamsFromApi();
	}
}
