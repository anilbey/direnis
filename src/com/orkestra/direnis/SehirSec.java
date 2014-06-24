package com.orkestra.direnis;

import com.orkestra.direnis.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SehirSec extends Activity {

	Button istanbul;
	Button ankara;
	TextView sehirSec;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sehir_sec);
		setTitle("Şehir Seç");
		
		SharedPreferences mSharedPrefs = getSharedPreferences("xmlFile",
				MODE_PRIVATE);
		
		final SharedPreferences.Editor mPrefsEditor = mSharedPrefs.edit();
		
		
		
		sehirSec  = (TextView) findViewById(R.id.sehirsec_textView1); 
		istanbul = (Button) findViewById(R.id.istanbul_btn);
		ankara = (Button) findViewById(R.id.ankara_btn);

		
		sehirSec.setTypeface(DirenisMain.tf);
		ankara.setTypeface(DirenisMain.tf);
		istanbul.setTypeface(DirenisMain.tf);
		istanbul.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mPrefsEditor.putBoolean("city", false);
				mPrefsEditor.commit();
				Intent intent = new Intent(SehirSec.this, Ev.class);
				startActivity(intent);
			}
		});

		ankara.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mPrefsEditor.putBoolean("city", true);
				mPrefsEditor.commit();
				Intent intent = new Intent(SehirSec.this, Ev.class);
				startActivity(intent);
			}
		});

	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sehir_sec, menu);
		return true;
	}

}