package com.kevoroid.forzasample.ui.main;

import android.os.Bundle;
import com.kevoroid.forzasample.R;
import com.kevoroid.forzasample.ui.BaseActivity;

public class MainActivity extends BaseActivity implements MainContracts.Views {

	private MainContracts.Actions actions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		actions = new MainPresenter(this);
	}
}
