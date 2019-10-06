package com.kevoroid.forzasample.repo;

import com.kevoroid.forzasample.api.ApiEndpoints;
import com.kevoroid.forzasample.models.Teams;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MasterRepo {

	private static MasterRepo INSTANCE;

	private ApiEndpoints repoApiEndpoints;

	private List<Teams> data;

	public MasterRepo(ApiEndpoints apiEndpoints) {
		this.repoApiEndpoints = apiEndpoints;
	}

	public static synchronized MasterRepo getINSTANCE(ApiEndpoints apiEndpoints) {
		if (INSTANCE == null) {
			INSTANCE = new MasterRepo(apiEndpoints);
		}
		return INSTANCE;
	}

	public List<Teams> fetchTeamsFromApi() {
		repoApiEndpoints.fetchListOfTeams().enqueue(new Callback<List<Teams>>() {
			@Override
			public void onResponse(Call<List<Teams>> call, Response<List<Teams>> response) {
				System.out.println("MasterRepo.onResponse --- " + response.isSuccessful());
				System.out.println("response = " + response);
				System.out.println("response = " + response.body());
				if (response.isSuccessful()) {
					data = response.body();
				} else {
					// do something!
				}
			}

			@Override
			public void onFailure(Call<List<Teams>> call, Throwable t) {
				System.out.println("MasterRepo.onFailure --- " + t.getLocalizedMessage());
				t.printStackTrace();
			}
		});

		return data;
	}
}
