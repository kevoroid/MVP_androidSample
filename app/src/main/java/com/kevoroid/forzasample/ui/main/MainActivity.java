package com.kevoroid.forzasample.ui.main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kevoroid.forzasample.R;
import com.kevoroid.forzasample.api.RetroMaster;
import com.kevoroid.forzasample.models.Team;
import com.kevoroid.forzasample.models.Teams;
import com.kevoroid.forzasample.ui.BaseActivity;
import com.kevoroid.forzasample.ui.main.adapters.MainAdapter;
import com.kevoroid.forzasample.utils.CacheData;
import com.kevoroid.forzasample.utils.NetworkHandler;
import com.kevoroid.forzasample.utils.PromptHandler;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.Collection;
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

	private MainAdapter.RecyclerViewCallback recyclerViewCallback = new MainAdapter.RecyclerViewCallback() {
		@Override
		public void showSelectedTeam(int id) {
			String cachedData = CacheData.getInstance().readTeamDetailFromCache(id);
			if (cachedData != null) {
				Team tt = new Gson().fromJson(cachedData, Team.class);
				openTeamDetails(tt);
			} else {
				actions.fetchTeamDetail(id);
			}
		}
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
		String cachedData = CacheData.getInstance().readTeamsFromCache();
		if (cachedData != null) {
			// read from cache instead! --- Cache will be deleted after 10 min!
			Type collectionType = new TypeToken<Collection<Teams>>(){}.getType();
			Collection<Teams> teams = new Gson().fromJson(cachedData, collectionType);
			setupRecyclerView((List<Teams>) teams);
		} else {
			if (NetworkHandler.internetAvailable(this)) {
				actions.fetchTeams();
			} else {
				hideLoading();
				showErr();
			}
		}
	}

	@Override
	public void openTeamDetails(Team data) {
		View bottomSheetView = View.inflate(this, R.layout.bottom_sheet_team_detail, null);
		BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
		bottomSheetDialog.setContentView(bottomSheetView);

		TextView teamName = (TextView) bottomSheetView.findViewById(R.id.team_detail_team_name);
		TextView teamGender = (TextView) bottomSheetView.findViewById(R.id.team_detail_team_gender);
		TextView teamRegion = (TextView) bottomSheetView.findViewById(R.id.team_detail_team_region);
		TextView teamDesc = (TextView) bottomSheetView.findViewById(R.id.team_detail_team_desc);
		ImageView teamBadge = (ImageView) bottomSheetView.findViewById(R.id.team_detail_team_badge);

		teamName.setText(data.getName());
		teamGender.setText(data.getGender());
		if (data.getNational()) {
			teamRegion.setText(R.string.label_national);
		} else {
			teamRegion.setText((R.string.label_club));
		}
		teamDesc.setText(data.getDescription());
		if (data.getBadgeUrl() != null && !data.getBadgeUrl().isEmpty()) {
			Picasso.get().load(RetroMaster.getTeamBadgeUrl(data.getId())).into(teamBadge);
		}

		try {
			bottomSheetDialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public View getMainLayout() {
		return findViewById(android.R.id.content);
	}

	@Override
	public void setupRecyclerView(List<Teams> data) {
		try {
			mAdapter = new MainAdapter(recyclerViewCallback, data);
			recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
			recyclerView.setAdapter(mAdapter);
		} catch (Exception x) {
			x.printStackTrace();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		mAdapter = null;
	}
}
