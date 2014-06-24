package com.orkestra.direnis;

import java.util.ArrayList;
import java.util.List;

import com.orkestra.direnis.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class HighScores extends Activity {
	public static int highScoreIndex = 0;
	public static boolean isOver= false;
	ListView listView;
	List<HighScoresRowItem> highScoreRowItems;
	int listNumber = 1;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_high_scores);
		setTitle("High Scores");

		SharedPreferences mSharedPrefs = getSharedPreferences("xmlFile",
				MODE_PRIVATE);

		SharedPreferences prefs = getSharedPreferences("highScores",
				MODE_PRIVATE);
		// String highScores = mSharedPrefs.getStringSet("asd", String<"asd"> ""
		// ) ;
		String name = mSharedPrefs.getString("name", null);
		int mPuan = mSharedPrefs.getInt("puan", 200);
		int mExp = mSharedPrefs.getInt("exp", 0);
		int mCounter = mSharedPrefs.getInt("counter", 0);
		boolean IsAnkara = mSharedPrefs.getBoolean("city", true);
		boolean IsAlive = mSharedPrefs.getBoolean("isalive", true);
		SharedPreferences.Editor mPrefsEditor = mSharedPrefs.edit();
		SharedPreferences.Editor prefsEditor = prefs.edit();
		mPrefsEditor.commit();
		highScoreRowItems = new ArrayList<HighScoresRowItem>();

		listView = (ListView) findViewById(R.id.highscores_list);

		HighScoresListViewAdapter adapter = new HighScoresListViewAdapter(this,
				R.layout.highscores_list_item, highScoreRowItems);

		listView.setAdapter(adapter);
		highScoreIndex = 0;
		HighOne[] h1 = new HighOne[11];
		HighOne[] newH1 = new HighOne[10];

		HighOne h2;
		h2 = new HighOne(prefs.getString("hsName0", ""), 1, prefs.getInt(
				"hsNumber0", 0));
		h1 = h2.add(h1, h2);
		h2 = new HighOne(prefs.getString("hsName1", ""), 1, prefs.getInt(
				"hsNumber1", 0));
		h1 = h2.add(h1, h2);
		h2 = new HighOne(prefs.getString("hsName2", ""), 1, prefs.getInt(
				"hsNumber2", 0));
		h1 = h2.add(h1, h2);
		h2 = new HighOne(prefs.getString("hsName3", ""), 1, prefs.getInt(
				"hsNumber3", 0));
		h1 = h2.add(h1, h2);
		h2 = new HighOne(prefs.getString("hsName4", ""), 1, prefs.getInt(
				"hsNumber4", 0));
		h1 = h2.add(h1, h2);
		h2 = new HighOne(prefs.getString("hsName5", ""), 1, prefs.getInt(
				"hsNumber5", 0));
		h1 = h2.add(h1, h2);
		h2 = new HighOne(prefs.getString("hsName6", ""), 1, prefs.getInt(
				"hsNumber6", 0));
		h1 = h2.add(h1, h2);
		h2 = new HighOne(prefs.getString("hsName7", ""), 1, prefs.getInt(
				"hsNumber7", 0));
		h1 = h2.add(h1, h2);
		h2 = new HighOne(prefs.getString("hsName8", ""), 1, prefs.getInt(
				"hsNumber8", 0));
		h1 = h2.add(h1, h2);
		h2 = new HighOne(prefs.getString("hsName9", ""), 1, prefs.getInt(
				"hsNumber9", 0));
		h1 = h2.add(h1, h2);
		if (isOver) {
			h2 = new HighOne(prefs.getString("hsName10", ""), 1, prefs.getInt(
					"hsNumber10", 0));
			h1 = h2.add(h1, h2);
			isOver = false;
		}


		for (int i = 0; i < 10; i++) {
			newH1[i] = h1[i];
		}
		newH1 = h2.sort(newH1);
		for (int i = 0; i < newH1.length; i++) {
			HighScoresRowItem item2 = new HighScoresRowItem(newH1[i].name,
					newH1[i].number, newH1[i].score);
			highScoreRowItems.add(item2);
			prefsEditor.putInt(("hsNumber" + String.valueOf(i) + ""),
					newH1[i].score);
			prefsEditor.putString(("hsName" + String.valueOf(i) + ""),
					newH1[i].name);
			prefsEditor.commit();

		}
		HighScoresRowItem item = new HighScoresRowItem(
				DirenisMain.oyuncu.getName(), listNumber, Answer.hakikiPoint);

	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(HighScores.this, DirenisMain.class);
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.high_scores, menu);
		return true;
	}

}

class HighOne {
	public int number;
	public String name;
	public int score;

	public HighOne(String name, int number, int score) {
		this.number = number;
		this.name = name;
		this.score = score;

	}

	public HighOne(int number, int score) {
		this.name = DirenisMain.oyuncu.getName();
		this.score = score;
		this.number = number;

	}

	public HighOne[] sort(HighOne[] h1) {
		HighOne temp;

		for (int i = 0; i < h1.length; i++) {
			h1[i].number = i + 1;
			for (int j = 0; j < h1.length - i - 1; j++) {

				if (h1[j].score < h1[j + 1].score) {
					temp = h1[j];
					h1[j] = h1[j + 1];
					h1[j + 1] = temp;

				}

			}
		}
		return h1;
	}

	public HighOne[] add(HighOne[] h1, HighOne h2) {

		h1[HighScores.highScoreIndex] = h2;
		if (HighScores.highScoreIndex == 10) {
			return h1;

		} else
			HighScores.highScoreIndex++;

		return h1;

	}

};
