package com.orkestra.direnis;

import java.text.SimpleDateFormat;

import com.orkestra.direnis.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Ev extends Activity {

	public static String eldiven, destek, maske;
	Button dukkan;
	Button dinlen;
	Button olay;
	TextView level;
	TextView point;
	TextView time;
	TextView direnisciAdi;
	TextView maskeAdi;
	TextView eldivenAdi;
	TextView destekAdi;
	ImageView maskeImg;
	ImageView eldivenImg;
	ImageView destekImg;
	TextView tv1;
	TextView tv2;
	TextView tv3;
	TextView seviye;
	TextView puan;
	
	
	public static final Integer[] images = { R.drawable.bezmaske36,
		R.drawable.kaskol36, R.drawable.karmaskesi36,
		R.drawable.antikagazmaskesi36, R.drawable.tamyuzgazmaskesi36,
		R.drawable.tekfiltreligazmaskesi36,
		R.drawable.ciftfiltreligazmaskesi36, R.drawable.ordumaligazmaskesi36,
		R.drawable.yuneldiven36, R.drawable.bahceeldiveni36,
		R.drawable.snowboardeldiveni36, R.drawable.derieldiven36,
		R.drawable.limonsuyu36, R.drawable.sirke36, R.drawable.talcid36, R.drawable.blank };
	
	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ev);
		seviye = (TextView) findViewById(R.id.ev_textView1);
		puan = (TextView) findViewById(R.id.ev_textView2);
		olay = (Button) findViewById(R.id.direnisbtn);
		dukkan = (Button) findViewById(R.id.dukkanbtn);
		dinlen = (Button) findViewById(R.id.dinlenbtn);
		level = (TextView) findViewById(R.id.seviye);
		point = (TextView) findViewById(R.id.puan);
		time = (TextView) findViewById(R.id.time);
		direnisciAdi = (TextView) findViewById(R.id.ev_direnisci_adi);
		maskeAdi = (TextView) findViewById(R.id.maske_adi);
		eldivenAdi = (TextView) findViewById(R.id.eldiven_adi);
		destekAdi = (TextView) findViewById(R.id.destek_adi);
		maskeImg = (ImageView) findViewById(R.id.maske_image);
		eldivenImg = (ImageView) findViewById(R.id.eldiven_image);
		destekImg = (ImageView) findViewById(R.id.destek_image);
		tv1 = (TextView) findViewById(R.id.textView3);
		tv2 = (TextView) findViewById(R.id.textView4);
		tv3 = (TextView) findViewById(R.id.textView5);
		seviye.setTypeface(DirenisMain.tf);
		puan.setTypeface(DirenisMain.tf);
		dukkan.setTypeface(DirenisMain.tf);
		olay.setTypeface(DirenisMain.tf);
		dinlen.setTypeface(DirenisMain.tf);
		level.setTypeface(DirenisMain.tf);
		point.setTypeface(DirenisMain.tf);
		time.setTypeface(DirenisMain.tf);
		direnisciAdi.setTypeface(DirenisMain.tf);
		maskeAdi.setTypeface(DirenisMain.tf);
		eldivenAdi.setTypeface(DirenisMain.tf);
		destekAdi.setTypeface(DirenisMain.tf);
		tv1.setTypeface(DirenisMain.tf);
		tv2.setTypeface(DirenisMain.tf);
		tv3.setTypeface(DirenisMain.tf);
		
		
		
		final SimpleDateFormat std = new SimpleDateFormat("dd MMMM HH:mm");

		

		SharedPreferences mSharedPrefs = getSharedPreferences("xmlFile",
				MODE_PRIVATE);

		int mPuan = mSharedPrefs.getInt("puan", 200);
		int mExp = mSharedPrefs.getInt("exp", 0);
		int mCounter = mSharedPrefs.getInt("counter", 0);
		boolean IsAnkara = mSharedPrefs.getBoolean("city", true);
		boolean IsAlive = mSharedPrefs.getBoolean("isalive", true);
		int mLevel = mSharedPrefs.getInt("level", 1);
		String mName = mSharedPrefs.getString("name", "");
		SharedPreferences.Editor mPrefsEditor = mSharedPrefs.edit();
	
		DirenisMain.oyuncu.setName(mName);
		DirenisMain.oyuncu.setPoint(mPuan);
		DirenisMain.oyuncu.setExp(mExp);
		DirenisMain.oyuncu.setLevel(mLevel);
		mPrefsEditor.putBoolean("saved", true);
		mPrefsEditor.commit();
		point.setText(String.valueOf(mPuan)+" ");
		maskeAdi.setText(mSharedPrefs.getString("item0", "yok").toUpperCase());
		eldivenAdi.setText(mSharedPrefs.getString("item1", "yok").toUpperCase());
		destekAdi.setText(mSharedPrefs.getString("item2", "yok").toUpperCase());
	
		maskeImg.setImageResource(images[mSharedPrefs.getInt("itemIndex0", 15)]);
		eldivenImg.setImageResource(images[mSharedPrefs.getInt("itemIndex1", 15 )]);
		destekImg.setImageResource(images[mSharedPrefs.getInt("itemIndex2", 15 )]);
		
		
		level.setText(String.valueOf(DirenisMain.oyuncu.getLevel())+" ");
		direnisciAdi.setText(mName.toUpperCase());
		time.setText(std.format(DirenisMain.date).toUpperCase());

		dinlen.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {

				if (DirenisMain.date.getHours() >= 6
						&& DirenisMain.date.getHours() <= 14) {
					// Intent evegit = new Intent(Generate.this, Ev.class);
					DirenisMain.date.setHours(14);
					DirenisMain.date.setMinutes(30); }
				else
					DirenisMain.date.setHours(DirenisMain.date.getHours() + 1);

				time.setText(std.format(DirenisMain.date));
			}
		});

		olay.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				DirenisMain.date.setMinutes(DirenisMain.date.getMinutes() + 30);
				Intent intent = new Intent(Ev.this, Olay.class);

				startActivity(intent);
			}
		});

		dukkan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(Ev.this, Dukkan.class);
				Dukkan.direnistenMi = false;
				startActivity(intent);
			}
		});

	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(Ev.this, DirenisMain.class);
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
	}
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		return false;
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.ev, menu);
		return true;
	}

	

}
