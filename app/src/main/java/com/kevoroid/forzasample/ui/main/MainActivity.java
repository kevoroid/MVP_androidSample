package com.kevoroid.forzasample.ui.main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.kevoroid.forzasample.R;
import com.kevoroid.forzasample.models.Teams;
import com.kevoroid.forzasample.ui.BaseActivity;
import com.kevoroid.forzasample.ui.main.adapters.MainAdapter;
import com.kevoroid.forzasample.utils.NetworkHandler;
import com.kevoroid.forzasample.utils.PromptHandler;

import java.util.List;

public class MainActivity extends BaseActivity implements MainContracts.Views {

	private MainContracts.Actions actions;

	private RecyclerView recyclerView;
	private RecyclerView.Adapter mAdapter;

	// I know its deprecated but for sake of simplicity!
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		actions = new MainPresenter(this);

		recyclerView = (RecyclerView) findViewById(R.id.main_activity_recyclerview);
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage(getString(R.string.label_please_wait));
		progressDialog.setIndeterminate(true);

		showTeams();
	}

	private MainAdapter.RecyclerViewCallback recyclerViewCallback = id -> {
		actions.fetchTeamDetail(id);
	};

	@Override
	public void showLoading() {
		progressDialog.show();
	}

	@Override
	public void hideLoading() {
		progressDialog.dismiss();
	}

	@Override
	public void showErr() {
		PromptHandler.showErrSnackBar(getMainLayout(), this);
	}

	@Override
	public void showTeams() {
		if (NetworkHandler.internetAvailable(this)) {
			actions.fetchTeams();
		} else {
			hideLoading();
			showErr();
		}
	}

	@Override
	public void openTeamDetails(int id) {
		// show teams detail in bottom sheet!
	}

	@Override
	public View getMainLayout() {
		return findViewById(android.R.id.content);
	}

	@Override
	public void setupRecyclerView(List<Teams> data) {
		mAdapter = new MainAdapter(recyclerViewCallback, data);
		recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
		recyclerView.setAdapter(mAdapter);
	}
}
