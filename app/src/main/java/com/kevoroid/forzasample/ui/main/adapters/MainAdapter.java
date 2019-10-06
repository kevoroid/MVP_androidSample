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

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

	private List<Teams> teams;

	public MainAdapter(List<Teams> teams) {
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
			if (teams.get(position).getNational()) {
				holder.teamRegion.setText(R.string.label_national);
			} else {
				holder.teamRegion.setText((R.string.label_club));
			}
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

	static class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		TextView teamName;
		TextView teamGender;
		TextView teamRegion;

		MainViewHolder(@NonNull View itemView) {
			super(itemView);

			teamName = itemView.findViewById(R.id.teams_row_team_name);
			teamGender = itemView.findViewById(R.id.teams_row_team_gender);
			teamRegion = itemView.findViewById(R.id.teams_row_team_region);

			itemView.setTag(this);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			//
		}
	}
}
