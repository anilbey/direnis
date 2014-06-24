package com.orkestra.direnis;

import com.orkestra.direnis.R;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.widget.TextView;

public class NasilOynanir extends Activity {

	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nasil_oynanir);
		

		
		tv = (TextView) findViewById(R.id.nasil_oynanir);
		tv.setMovementMethod(new ScrollingMovementMethod());
		tv.setText(tv.getText().toString().toUpperCase());
		tv.setTypeface(DirenisMain.tf);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nasil_oynanir, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		return false;
	}

}
