package com.kevoroid.forzasample;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kevoroid.forzasample.api.ApiEndpoints;
import com.kevoroid.forzasample.api.RetroMaster;
import com.kevoroid.forzasample.models.Team;
import com.kevoroid.forzasample.models.Teams;
import com.kevoroid.forzasample.repo.MasterRepo;
import com.kevoroid.forzasample.ui.main.MainContracts;
import com.kevoroid.forzasample.ui.main.MainPresenter;
import com.kevoroid.forzasample.utils.NationalTeamHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class BasicUnitTests {

	@Mock
	MainContracts.Views view;

	@Mock
	MasterRepo masterRepo;

	@Mock
	MasterRepo.MasterRepoCallbacks masterRepoCallbacks;

	@Mock
	ApiEndpoints apiEndpoints;

	private MainContracts.Actions presenter;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		presenter = new MainPresenter(view);
		masterRepo = MasterRepo.getINSTANCE(masterRepoCallbacks, apiEndpoints);
	}

	private String returnResourcesTeamsData() {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("teams.json");

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) != -1) {
				byteArrayOutputStream.write(buffer, 0, length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteArrayOutputStream.toString();
	}

	@Test
	public void testTeamsData() {
		Type collectionType = new TypeToken<Collection<Teams>>(){}.getType();
		List<Teams> teams = new Gson().fromJson(returnResourcesTeamsData(), collectionType);

		Assert.assertEquals(teams.size(), 5);
		Assert.assertEquals(teams.get(0).getName(), "Arsenal FC");
		Assert.assertEquals(teams.get(2).getGender(), "female");
		Assert.assertEquals(NationalTeamHandler.isNationalOrClub(teams.get(2).getNational()), R.string.label_national);
		Assert.assertEquals(NationalTeamHandler.isNationalOrClub(teams.get(4).getNational()), R.string.label_club);
	}

	@Test
	public void testTeamLogosURL() {
		Assert.assertEquals(RetroMaster.getTeamBadgeUrl(3), "https://android-exam.s3-eu-west-1.amazonaws.com/teams/3/badge.png");
		Assert.assertEquals(RetroMaster.getTeamBadgeUrl(5), "https://android-exam.s3-eu-west-1.amazonaws.com/teams/5/badge.png");
	}

	@Test
	public void addition_isCorrect() {
		assertEquals(4, 2 + 2);
	}
}