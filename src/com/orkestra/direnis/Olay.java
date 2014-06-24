package com.orkestra.direnis;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import com.orkestra.direnis.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Olay extends Activity {

	Button b1;
	Button b2;
	Button b3;
	Button b4;
	TextView meydanSec;

	public static String mekanAdi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_olay);
		setTitle("Meydanlar");
		
		meydanSec = (TextView) findViewById(R.id.meydan_sec);
		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);
		b3 = (Button) findViewById(R.id.button3);
		b4 = (Button) findViewById(R.id.button4);

		meydanSec.setTypeface(DirenisMain.tf);
		b1.setTypeface(DirenisMain.tf);
		b2.setTypeface(DirenisMain.tf);
		b3.setTypeface(DirenisMain.tf);
		b4.setTypeface(DirenisMain.tf);

		final SharedPreferences mSharedPrefs = getSharedPreferences("xmlFile",
				MODE_PRIVATE);

		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mSharedPrefs.getBoolean("city", true))
				mekanAdi = "Kızılay";
				else
					mekanAdi ="Harbiye";
				Intent intent = new Intent(Olay.this, Generate.class);
				startActivity(intent);
			}
		});
		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mSharedPrefs.getBoolean("city", true))
				mekanAdi = "Kuğulu Park";
				else
					mekanAdi ="Gezi Parkı";
				Intent intent = new Intent(Olay.this, Generate.class);
				startActivity(intent);
			}
		});
		b3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mSharedPrefs.getBoolean("city", true))
				mekanAdi = "Kennedy Cad";
				else
					mekanAdi = "Beşiktaş";
				Intent intent = new Intent(Olay.this, Generate.class);
				startActivity(intent);
			}
		});

		b4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Olay.this, Ev.class);

				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_up,
						R.anim.slide_out_up);
			}
		});

		JSONObject db = null;

		try {

			db = parse(getResources().openRawResource(R.raw.database));
		} catch (NotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Iterator dummy2;

		try {

			if (mSharedPrefs.getBoolean("city", true))
				dummy2 = db.getJSONObject("Ankara").keys();
			else
				dummy2 = db.getJSONObject("İstanbul").keys();

			String pl = "";
			pl += (String) dummy2.next();
			b1.setText(pl.toUpperCase());
			pl = "";
			pl += pl += (String) dummy2.next();
			b2.setText(pl.toUpperCase());
			pl = "";
			pl += pl += (String) dummy2.next();
			b3.setText(pl.toUpperCase());

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static JSONObject parse(InputStream is) throws JSONException {
		String rawDB;

		try {

			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader bfr = new BufferedReader(isr);

			rawDB = bfr.readLine();
			isr.close();
			bfr.close();
			JSONObject a = new JSONObject(rawDB);
			return a;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		return false;
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(Olay.this, Ev.class);
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.olay, menu);
		return true;
	}
}