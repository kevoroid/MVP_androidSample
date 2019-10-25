package com.kevoroid.forzasample.ui.main.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.kevoroid.forzasample.BuildConfig;
import com.kevoroid.forzasample.R;
import com.kevoroid.forzasample.models.Teams;
import com.kevoroid.forzasample.utils.NationalTeamHandler;

import java.util.List;

// Using old-fashion RecyclerView.Adapter<ViewHolder> instead of ListAdapter + DiffUtil for sake of this test!
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

	private RecyclerViewCallback recyclerViewCallback;
	private List<Teams> teams;

	public MainAdapter(RecyclerViewCallback recyclerViewCallback, List<Teams> teams) {
		this.recyclerViewCallback = recyclerViewCallback;
		this.teams = teams;
	}

	@NonNull
	@Override
	public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.teams_row_cardview, parent, false);
		return new MainViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
		try {
			holder.teamName.setText(teams.get(position).getName());
			holder.teamGender.setText(teams.get(position).getGender());
			holder.teamRegion.setText(NationalTeamHandler.isNationalOrClub(teams.get(position).getNational()));

			holder.itemView.setOnClickListener(v -> {
				recyclerViewCallback.showSelectedTeam(teams.get(position).getId());
			});
		} catch (Exception e) {
			if (BuildConfig.DEBUG) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int getItemCount() {
		return teams.size();
	}

	static class MainViewHolder extends RecyclerView.ViewHolder {

		TextView teamName;
		TextView teamGender;
		TextView teamRegion;

		MainViewHolder(@NonNull View itemView) {
			super(itemView);

			teamName = itemView.findViewById(R.id.teams_row_team_name);
			teamGender = itemView.findViewById(R.id.teams_row_team_gender);
			teamRegion = itemView.findViewById(R.id.teams_row_team_region);

			itemView.setTag(this);
		}
	}

	@FunctionalInterface
	public interface RecyclerViewCallback {

		void showSelectedTeam(int id);
	}
}
