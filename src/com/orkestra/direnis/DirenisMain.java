package com.orkestra.direnis;

import java.util.Date;

import com.orkestra.direnis.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DirenisMain extends Activity {
	public static Player oyuncu = new Player(1, 200);
	@SuppressWarnings("deprecation")
	public static Date date = new Date(113, 04, 28, 17, 0);
	public static Date dateBegins = new Date(113, 04, 28, 17, 0);
	public static boolean highScoreFlag = false;

	Button yenioyun;
	Button nasiloynanir;
	Button devamet;
	Button skorlar;
	TextView yol;
	public static Typeface tf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_direnis_main);

		devamet = (Button) findViewById(R.id.button1);
		yenioyun = (Button) findViewById(R.id.yenioyunbtn);
		nasiloynanir = (Button) findViewById(R.id.button3);
		skorlar = (Button) findViewById(R.id.Skorlar);
		yol = (TextView) findViewById(R.id.capulcunun_yolu);

		tf = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue.otf");

		devamet.setTypeface(tf);
		yenioyun.setTypeface(tf);
		nasiloynanir.setTypeface(tf);
		skorlar.setTypeface(tf);
		yol.setTypeface(tf);
		if (highScoreFlag) {

			SharedPreferences prefs = getSharedPreferences("highScores",
					MODE_PRIVATE);

			Editor editor = prefs.edit();

			Answer.highScoreCounter = prefs.getInt("hsNumber", 0);

			editor.putInt(
					("hsNumber" + String.valueOf(Answer.highScoreCounter) + ""),
					Answer.hakikiPoint);
			editor.putString(
					("hsName" + String.valueOf(Answer.highScoreCounter) + ""),
					DirenisMain.oyuncu.getName());
			if (Answer.highScoreCounter != 10)
				Answer.highScoreCounter++;
			editor.putInt("hsNumber", Answer.highScoreCounter);
			editor.commit();
			highScoreFlag = false;
			HighScores.isOver = true;
		}

		SharedPreferences mSharedPrefs = getSharedPreferences("xmlFile",
				MODE_PRIVATE);
		boolean mSaved = mSharedPrefs.getBoolean("saved", false);
		oyuncu.setName(mSharedPrefs.getString("name", ""));

		if (mSaved) {
			devamet.setEnabled(true);
			devamet.setVisibility(0);
		} else {
			devamet.setEnabled(false);
			devamet.setVisibility(100);

		}

		skorlar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent highScoresIntent = new Intent(DirenisMain.this,
						HighScores.class);
				startActivity(highScoresIntent);
			}
		});

		devamet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(DirenisMain.this, Ev.class);
				startActivity(intent);
			}
		});

		yenioyun.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences mSharedPrefs = getSharedPreferences(
						"xmlFile", MODE_PRIVATE);

				Intent intent = new Intent(DirenisMain.this, IsimGir.class);
				SharedPreferences.Editor mPrefsEditor = mSharedPrefs.edit();
				mPrefsEditor.putBoolean("saved", false);
				mPrefsEditor.putInt("level", 1);
				mPrefsEditor.clear().commit();

				date.setHours(14);
				date.setMinutes(30);

				startActivity(intent);
			}
		});

		nasiloynanir.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent nasilOynanirEkrani = new Intent(DirenisMain.this,
						NasilOynanir.class);
				startActivity(nasilOynanirEkrani);

			}
		});
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		startActivity(intent);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.direnis_main, menu);
		return true;
	}

}
