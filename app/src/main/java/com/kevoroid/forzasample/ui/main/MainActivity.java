package com.kevoroid.forzasample.ui.main;

import android.os.Bundle;
import android.view.View;
import com.kevoroid.forzasample.R;
import com.kevoroid.forzasample.ui.BaseActivity;
import com.kevoroid.forzasample.utils.NetworkHandler;
import com.kevoroid.forzasample.utils.PromptHandler;

public class MainActivity extends BaseActivity implements MainContracts.Views {

	private MainContracts.Actions actions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		actions = new MainPresenter(this);

		openTeamDetails();
	}

	@Override
	public void showErr() {
		PromptHandler.showErrSnackBar(getMainLayout(), this);
	}

	@Override
	public void openTeamDetails() {
		if (NetworkHandler.internetAvailable(this)) {
			actions.fetchTeams();
		} else {
			showErr();
		}
	}

	@Override
	public View getMainLayout() {
		return findViewById(android.R.id.content);
	}
}
