package com.kevoroid.forzasample.repo;

import com.kevoroid.forzasample.BuildConfig;
import com.kevoroid.forzasample.api.ApiEndpoints;
import com.kevoroid.forzasample.models.Team;
import com.kevoroid.forzasample.models.Teams;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MasterRepo {

	private static MasterRepo INSTANCE;

	private ApiEndpoints repoApiEndpoints;
	private MasterRepoCallbacks callbacks;

	private MasterRepo(MasterRepoCallbacks masterRepoCallbacks, ApiEndpoints apiEndpoints) {
		this.callbacks = masterRepoCallbacks;
		this.repoApiEndpoints = apiEndpoints;
	}

	public static synchronized MasterRepo getINSTANCE(MasterRepoCallbacks masterRepoCallbacks, ApiEndpoints apiEndpoints) {
		if (INSTANCE == null) {
			INSTANCE = new MasterRepo(masterRepoCallbacks, apiEndpoints);
		}
		return INSTANCE;
	}

	public void fetchTeamsFromApi() {
		repoApiEndpoints.fetchListOfTeams().enqueue(new Callback<List<Teams>>() {
			@Override
			public void onResponse(Call<List<Teams>> call, Response<List<Teams>> response) {
				System.out.println("MasterRepo.onResponse --- " + response.isSuccessful());
				System.out.println("response = " + response);
				System.out.println("response = " + response.body());
				if (response.isSuccessful()) {
					callbacks.onDataReturned(response.body());
				} else {
					callbacks.onDataError();
				}
			}

			@Override
			public void onFailure(Call<List<Teams>> call, Throwable t) {
				if (BuildConfig.DEBUG) {
					System.out.println("MasterRepo.onFailure fetchTeamsFromApi --- " + t.getLocalizedMessage());
					t.printStackTrace();
				}
				callbacks.onDataError();
			}
		});
	}

	public void fetchTeamDetail(int id) {
		repoApiEndpoints.fetchTeamDetail(id).enqueue(new Callback<Team>() {
			@Override
			public void onResponse(Call<Team> call, Response<Team> response) {
				if (response.isSuccessful()) {
					callbacks.onDataReturned(response.body());
				} else {
					callbacks.onDataError();
				}
			}

			@Override
			public void onFailure(Call<Team> call, Throwable t) {
				if (BuildConfig.DEBUG) {
					System.out.println("MasterRepo.onFailure fetchTeamDetail --- " + t.getLocalizedMessage());
					t.printStackTrace();
				}
				callbacks.onDataError();
			}
		});
	}

	public interface MasterRepoCallbacks {

		void onDataReturned(List<Teams> data);

		void onDataReturned(Team data);

		void onDataError();
	}
}
