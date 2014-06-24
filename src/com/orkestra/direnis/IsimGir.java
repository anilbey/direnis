package com.orkestra.direnis;

import com.orkestra.direnis.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IsimGir extends Activity implements TextWatcher {
	Button devamet;
	EditText isimgir;
	TextView tv;
	public static String commentHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_isim_gir);
		tv = (TextView) findViewById(R.id.isimgir_textView1);
		devamet = (Button) findViewById(R.id.geri_don);
		isimgir = (EditText) findViewById(R.id.isim_gir);
		setTitle("İsim Gir");
		
		devamet.setTypeface(DirenisMain.tf);
		isimgir.setTypeface(DirenisMain.tf);
		tv.setTypeface(DirenisMain.tf);
		isimgir.addTextChangedListener(this);
		devamet.setEnabled(false);

		devamet.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(IsimGir.this, SehirSec.class);
				DirenisMain.oyuncu.setName(isimgir.getText().toString());
				SharedPreferences mSharedPrefs = getSharedPreferences(
						"xmlFile", MODE_PRIVATE);
				String name = mSharedPrefs.getString("name", null);
				SharedPreferences.Editor mPrefsEditor = mSharedPrefs.edit();
				mPrefsEditor.putString("name", DirenisMain.oyuncu.getName());
				mPrefsEditor.commit();
				isimgir.setText(isimgir.getText().toString().toUpperCase());
				startActivity(intent);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.isim_gir, menu);
		return true;
	}

	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		if (!isimgir.getText().toString().equals(""))
			devamet.setEnabled(true);
		else
			devamet.setEnabled(false);
		
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub

	}
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		return false;
	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
	}

}
