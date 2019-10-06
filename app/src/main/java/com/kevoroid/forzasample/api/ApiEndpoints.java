package com.kevoroid.forzasample.api;

import com.kevoroid.forzasample.models.Team;
import com.kevoroid.forzasample.models.Teams;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface ApiEndpoints {

	@GET("teams/teams.json")
	Call<List<Teams>> fetchListOfTeams();

	@GET("teams/{id}/team.json")
	Call<Team> fetchTeamDetail(@Path("id") int id);
}
