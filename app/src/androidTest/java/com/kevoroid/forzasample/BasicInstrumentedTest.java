package com.kevoroid.forzasample;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.kevoroid.forzasample.ui.main.MainActivity;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class BasicInstrumentedTest {

	@Rule
	public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

	@Test
	public void useAppContext() {
		assertEquals("com.kevoroid.forzasample", ApplicationProvider.getApplicationContext().getPackageName());
	}

	@Test
	public void testListOfTeams() {
		onView(withId(R.id.main_activity_recyclerview))
				.inRoot(RootMatchers.withDecorView(Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
				.perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));

		onView(withId(R.id.team_detail_team_name)).check(matches(isDisplayed()));
		onView(withText("Sweden")).check(matches(isDisplayed()));
		onView(withText("National team!")).check(matches(withText(R.string.label_national)));
	}
}
