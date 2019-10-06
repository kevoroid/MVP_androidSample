package com.kevoroid.forzasample.ui.main;

import androidx.annotation.NonNull;

public class MainPresenter implements MainContracts.Actions {

	private MainContracts.Views views;

	public MainPresenter(@NonNull MainContracts.Views views) {
		this.views = views;
	}
}
