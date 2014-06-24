package com.orkestra.direnis;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.orkestra.direnis.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Dukkan extends Activity implements OnItemClickListener {

	TextView point;
	TextView isim;
	TextView seviye;
	TextView seviyeVal;
	TextView pointVal;

	public static boolean direnistenMi = false;
	private boolean toggleToast = false;

	public static final String[] titles = new String[] { "Bez maske", "Kaşkol",
			"Kar maskesi", "Antika gaz maskesi", "Tam yüz gaz maskesi",
			"Tek filtreli gaz maskesi", "Çift filtreli gaz maskesi",
			"Ordu malı gaz maskesi", "Yün eldiven", "Bahçe eldiveni",
			"Snowboard eldiveni", "Deri eldiven", "Limon Suyu", "Sirke",
			"Talcid" };

	public static final int[] values = new int[] { 20, 80, 120, 200, 280, 520,
			880, 1850, 20, 100, 240, 400, 20, 100, 240 };
	public static final double[] powers = new double[] { 1, 1.2, 1.6, 2, 2.8,
			3.6, 4.8, 8, 1, 2.4, 3.8, 5, 1, 6, 15 };

	public static final String[] descriptions = new String[] { "Maske",
			"Maske", "Maske", "Maske", "Maske", "Maske", "Maske", "Maske",
			"Eldiven", "Eldiven", "Eldiven", "Eldiven", "Destek Malzemeleri",
			"Destek Malzemeleri", "Destek Malzemeleri" };

	public static final Integer[] images = { R.drawable.bezmaske,
			R.drawable.kaskol, R.drawable.karmaskesi,
			R.drawable.antikagazmaskesi, R.drawable.tamyuzgazmaskesi,
			R.drawable.tekfiltreligazmaskesi,
			R.drawable.ciftfiltreligazmaskesi, R.drawable.ordumaligazmaskesi,
			R.drawable.yuneldiven, R.drawable.bahcivaneldiveni,
			R.drawable.snowboardeldiveni, R.drawable.derieldiven,
			R.drawable.limonsuyu, R.drawable.sirke, R.drawable.talcid };

	private Toast toast = null;
	

	
	
	

	ListView listView;
	List<RowItem> rowItems;

	/** Called when the activity is first created. */
	@SuppressWarnings("unused")
	@SuppressLint("SimpleDateFormat")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dukkan);
		final SimpleDateFormat std = new SimpleDateFormat("dd MMMM HH:mm");
		TextView level = (TextView) findViewById(R.id.dukkan_seviyeText);
		point = (TextView) findViewById(R.id.dukkan_puanText);
		seviyeVal = (TextView) findViewById(R.id.dukkan_seviye);
		pointVal = (TextView)  findViewById(R.id.dukkan_puan);
		
	
				
				
		TextView time = (TextView) findViewById(R.id.dukkan_time);
		TextView direnisciAdi = (TextView) findViewById(R.id.dukkan_direnisciAdi);
		seviyeVal.setText(String.valueOf(DirenisMain.oyuncu.getLevel()).toUpperCase());
		
		level.setTypeface(DirenisMain.tf);
		seviyeVal.setTypeface(DirenisMain.tf);
		point.setTypeface(DirenisMain.tf);
		pointVal.setTypeface(DirenisMain.tf);
		time.setTypeface(DirenisMain.tf);
		direnisciAdi.setTypeface(DirenisMain.tf);
		
		
		SharedPreferences mSharedPrefs = getSharedPreferences("xmlFile",
				MODE_PRIVATE);

		String name = mSharedPrefs.getString("name", null);
		int mPuan = mSharedPrefs.getInt("puan", 200);
		int mExp = mSharedPrefs.getInt("exp", 0);
		int mCounter = mSharedPrefs.getInt("counter", 0);
		boolean IsAnkara = mSharedPrefs.getBoolean("city", true);
		boolean IsAlive = mSharedPrefs.getBoolean("isalive", true);
		SharedPreferences.Editor mPrefsEditor = mSharedPrefs.edit();
		mPrefsEditor.putString("name", DirenisMain.oyuncu.getName());

		pointVal.setText(String.valueOf(mPuan).toUpperCase());
		mPrefsEditor.commit();
		direnisciAdi.setText(name.toUpperCase());
		DirenisMain.oyuncu.setName(name);
		DirenisMain.oyuncu.setPoint(mPuan);
		time.setText(std.format(DirenisMain.date).toUpperCase());

		rowItems = new ArrayList<RowItem>();

		listView = (ListView) findViewById(R.id.list);
		CustomListViewAdapter adapter = new CustomListViewAdapter(this,
				R.layout.list_item, rowItems);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);

		for (int i = 0; i < titles.length; i++) {

			RowItem item = new RowItem(images[i], titles[i], descriptions[i],
					powers[i], values[i]);
			String test1 = mSharedPrefs.getString("item0", "");
			String test2 = mSharedPrefs.getString("item1", "");
			String test3 = mSharedPrefs.getString("item2", "");
			String itemTest1 = item.getTitle();

			if ((itemTest1.equals(test1)) || (itemTest1.equals(test2))
					|| (itemTest1.equals(test3)))
				{item.setDesc("Satın Alındı.");
			
				
				}
			rowItems.add(item);

		}

	}



	protected void onPause() {
		if (toast != null)
			toast.cancel();
		super.onPause();
	}

	@SuppressWarnings("deprecation")
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		long a = 0;
		SharedPreferences mSharedPrefs = getSharedPreferences("xmlFile",
				MODE_PRIVATE);
		int mPuan = mSharedPrefs.getInt("puan", 200);
		SharedPreferences.Editor mPrefsEditor = mSharedPrefs.edit();
		if ((mPuan - values[position]) <= 0) {

			if (!toggleToast) {
				a = DirenisMain.date.getHours() * 3600
						+ DirenisMain.date.getMinutes() * 60
						+ DirenisMain.date.getSeconds();

				Toast.makeText(getApplicationContext(),
						"Puanınız yeterli değildir".toUpperCase(), Toast.LENGTH_SHORT).show();
				toggleToast = true;

			} else if (((DirenisMain.date.getHours() * 3600
					+ DirenisMain.date.getMinutes() * 60 + DirenisMain.date
						.getSeconds()) - a) > 3) {
				toggleToast = false;
				if (toast != null)
					toast.cancel();
			}
		}

		else {
			mPrefsEditor.putInt("puan", mPuan - values[position]); // puanı
			mPrefsEditor.putBoolean("saved", true); // düştük

			mPrefsEditor.commit();

			DirenisMain.oyuncu.setPoint(mPuan);
			Intent intent = new Intent(Dukkan.this, Dukkan.class);
			startActivity(intent);

			if (descriptions[position] == "Maske") {
				DirenisMain.oyuncu.items[0] = titles[position];
				DirenisMain.oyuncu.itemPowers[0] = powers[position];
				mPrefsEditor.putInt("itemIndex0", position);
				mPrefsEditor.putString("item0", DirenisMain.oyuncu.items[0]);
				mPrefsEditor.putFloat("itemPower0",
						(float) DirenisMain.oyuncu.itemPowers[0]);
			} else if (descriptions[position] == "Eldiven") {
				DirenisMain.oyuncu.items[1] = titles[position];
				DirenisMain.oyuncu.itemPowers[1] = powers[position];
				mPrefsEditor.putInt("itemIndex1", position);
				mPrefsEditor.putString("item1", DirenisMain.oyuncu.items[1]);
				mPrefsEditor.putFloat("itemPower1",
						(float) DirenisMain.oyuncu.itemPowers[1]);

			} else if (descriptions[position] == "Destek Malzemeleri") {
				DirenisMain.oyuncu.items[2] = titles[position];
				DirenisMain.oyuncu.itemPowers[2] = powers[position];
				mPrefsEditor.putInt("itemIndex2", position);
				mPrefsEditor.putString("item2", DirenisMain.oyuncu.items[2]);
				mPrefsEditor.putFloat("itemPower2",
						(float) DirenisMain.oyuncu.itemPowers[2]);
			}

			mPrefsEditor.commit();
		}

	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		return false;
	}
	
	@Override
	public void onBackPressed() {
	if(direnistenMi){
		Intent intent = new Intent(Dukkan.this, Generate.class);
	startActivity(intent);
	overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
}
	else
		{Intent intent = new Intent(Dukkan.this, Ev.class);
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
		}
	}
}
