package com.kevoroid.forzasample.utils;

import android.content.Context;
import android.view.View;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.kevoroid.forzasample.R;

public class PromptHandler {

	public static void showErrSnackBar(View view, Context context) {
		Snackbar.make(view,
				context.getResources().getString(R.string.we_have_a_problem_err),
				BaseTransientBottomBar.LENGTH_LONG)
				.show();
	}
}
