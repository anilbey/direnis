package com.orkestra.direnis;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Timer;

import org.json.JSONException;
import org.json.JSONObject;

import com.orkestra.direnis.R;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Answer extends Activity {
	static int hakikiPoint = 0;
	static int highScoreCounter;
	int cumulative;
	int zorluk = 0;
	String item = "";
	AlertDialog alertDialog;
	private boolean gameOver = false;
	Timer timer;
	
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("ShowToast")
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newanswer);
		setTitle(Olay.mekanAdi);
		SharedPreferences mSharedPrefs = getSharedPreferences("xmlFile",
				MODE_PRIVATE);
		Handler myHandler = new Handler();
		// assign the view to the actionbar
		
		RelativeLayout main = (RelativeLayout) findViewById(R.id.new_answer_relative_layout);
		Button devam = (Button) findViewById(R.id.newanswer_btn);
		Button dukkan = (Button) findViewById(R.id.newanswer_dukkan);
		TextView tv = (TextView) findViewById(R.id.newanswer_tv1);
		TextView kazanc_text = (TextView) findViewById(R.id.newanswer_tv2);
		TextView direnisciAdi = (TextView) findViewById(R.id.newanswer_direnisci_adi);
		TextView saat = (TextView) findViewById(R.id.newanswer_time);
		TextView seviyeText = (TextView) findViewById(R.id.newanswer_seviyetext);
		TextView seviye = (TextView) findViewById(R.id.newanswer_seviye);
		TextView puanText = (TextView) findViewById(R.id.newanswer_puantext);
		TextView puan = (TextView) findViewById(R.id.newanswer_puan);
		ImageView maske = (ImageView) findViewById(R.id.newanswer_maskeimg);
		ImageView eldiven = (ImageView) findViewById(R.id.newanswer_eldivenimg);
		ImageView destek = (ImageView) findViewById(R.id.newanswer_destekimg);
		saat.setTypeface(DirenisMain.tf);
		seviyeText.setTypeface(DirenisMain.tf);
		seviye.setTypeface(DirenisMain.tf);
		puanText.setTypeface(DirenisMain.tf);
		puan.setTypeface(DirenisMain.tf);
		direnisciAdi.setTypeface(DirenisMain.tf);
		direnisciAdi.setText(DirenisMain.oyuncu.getName());
		final SimpleDateFormat std = new SimpleDateFormat("dd MMMM HH:mm");
		saat.setText(std.format(DirenisMain.date).toUpperCase());
		puan.setText(String.valueOf(DirenisMain.oyuncu.getPoint())
				.toUpperCase()+" ");
		seviye.setText(String.valueOf(DirenisMain.oyuncu.getLevel())
				.toUpperCase()+" ");
		maske.setImageResource(Ev.images[mSharedPrefs.getInt("itemIndex0",
				15)]);
		eldiven.setImageResource(Ev.images[mSharedPrefs.getInt("itemIndex1",
				15)]);
		destek.setImageResource(Ev.images[mSharedPrefs.getInt("itemIndex2",
				15)]);
		
		
		
		
		
		
		
		Intent intent = getIntent();
		gameOver = false;
		devam.setTypeface(DirenisMain.tf);
		kazanc_text.setTypeface(DirenisMain.tf);
		tv.setTypeface(DirenisMain.tf);

		final Integer[] images2 = { R.drawable.b1, R.drawable.b2,
				R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6,
				R.drawable.b7, R.drawable.b8, R.drawable.b9, R.drawable.b10,
				R.drawable.b11, R.drawable.b12, R.drawable.b13, R.drawable.b14,
				R.drawable.b15, R.drawable.b16, R.drawable.b17, R.drawable.b18,
				R.drawable.b19, R.drawable.b20, R.drawable.b21, R.drawable.b22,
				R.drawable.b23, R.drawable.b24, R.drawable.b25, R.drawable.b26,
				R.drawable.b27, R.drawable.b28, R.drawable.b29, R.drawable.b30,
				R.drawable.b31, R.drawable.b32, R.drawable.b33, R.drawable.b34,
				R.drawable.b35, R.drawable.b36, R.drawable.b37, R.drawable.b38,
				R.drawable.b39, R.drawable.b40, R.drawable.b41, R.drawable.b42,
				R.drawable.b43, R.drawable.b44, R.drawable.b45, R.drawable.b46,
				R.drawable.b47, R.drawable.b48, R.drawable.b49, R.drawable.b50,
				R.drawable.b51, R.drawable.b52, R.drawable.b53, R.drawable.b54,
				R.drawable.b55, R.drawable.b56, R.drawable.b57, R.drawable.b58,
				R.drawable.b59, R.drawable.b60, R.drawable.b61, R.drawable.b62,
				R.drawable.b63, R.drawable.b64, R.drawable.b65, R.drawable.b66,
				R.drawable.b67, R.drawable.b68, R.drawable.b69, R.drawable.b70 };

		main.setBackgroundResource(images2[Generate.hakikiBackgroundNumber]);

		alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Oyun Bitti");
		alertDialog.setButton("#anamenuyedonuyoruz",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// here you can add functions
						Intent intent = new Intent(Answer.this,
								DirenisMain.class);
						SharedPreferences mSharedPrefs = getSharedPreferences(
								"xmlFile", MODE_PRIVATE);
						SharedPreferences.Editor mPrefsEditor = mSharedPrefs
								.edit();
						mPrefsEditor.putBoolean("saved", false);

						mPrefsEditor.commit();
						startActivity(intent);
					}
				});

		
		dukkan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent dukkanaGir = new Intent(Answer.this, Dukkan.class);
				Dukkan.direnistenMi = true;
				startActivity(dukkanaGir);

			}
		});
		
		
		devam.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Answer.this, Generate.class);
				if(gameOver) alertDialog.show();
				else
				startActivity(intent);
			}
		});

		JSONObject db = null;
		db = parse(getResources().openRawResource(R.raw.database));
		try {
			String dummy4;
			int dummyXP;
			int dummyPoint;
			JSONObject jsonHolder;
			if (mSharedPrefs.getBoolean("city", true))
				jsonHolder = db
						.getJSONObject("Ankara")
						.getJSONObject(Olay.mekanAdi)
						.getJSONArray(
								Generate.getHakikiTime(DirenisMain.date
										.getHours()))
						.getJSONObject(intent.getExtras().getInt("soruNo"))
						.getJSONArray("Cevaplar")
						.getJSONObject(
								(Integer) intent.getExtras().get("cevapNo"));

			else
				jsonHolder = db
						.getJSONObject("İstanbul")
						.getJSONObject(Olay.mekanAdi)
						.getJSONArray(
								Generate.getHakikiTime(DirenisMain.date
										.getHours()))
						.getJSONObject(intent.getExtras().getInt("soruNo"))
						.getJSONArray("Cevaplar")
						.getJSONObject(
								(Integer) intent.getExtras().get("cevapNo"));

			// String debugDummy =

			item = jsonHolder.getString("Item");

			zorluk = jsonHolder.getInt("Zorluk");

			String SuccessOrFail = SuccessFail();

			dummy4 = jsonHolder.getJSONObject(SuccessOrFail).getString("Text");

			dummyXP = jsonHolder.getJSONObject(SuccessOrFail)
					.getJSONObject("Result").getInt("XP");

			dummyPoint = jsonHolder.getJSONObject(SuccessOrFail)
					.getJSONObject("Result").getInt("Point");

			if (SuccessOrFail.equals("Fail"))
				dummyPoint *= -1;
			tv.setText(String.valueOf(dummy4).toUpperCase());
			boolean mSaved = mSharedPrefs.getBoolean("saved", true);
			cumulative = mSharedPrefs.getInt("cumulativePoint", 0);
			SharedPreferences.Editor mPrefsEditor = mSharedPrefs.edit();
			if (SuccessOrFail.equals("Success")) {
				mPrefsEditor.putInt("cumulativePoint", cumulative + dummyPoint);
				cumulative = mSharedPrefs.getInt("cumulativePoint", 0);
				if (dummyPoint > 0)
					kazanc_text.setText("+" + String.valueOf(dummyXP) + " XP"
							+ "\n" + "+" + String.valueOf(dummyPoint)
							+ " Puan".toUpperCase());
				
				else if (dummyPoint == 0)
					kazanc_text.setText(" ");
				
				else
					kazanc_text.setText("+" + String.valueOf(dummyXP) + " XP"
							+ "\n" + String.valueOf(dummyPoint)
							+ " Puan".toUpperCase());

			} else
				kazanc_text.setText("+" + String.valueOf(dummyXP) + " XP"
						+ "\n" + String.valueOf(dummyPoint)
						+ " Puan".toUpperCase());

			DirenisMain.oyuncu.setPoint(DirenisMain.oyuncu.getPoint()
					+ dummyPoint);
			mPrefsEditor.putInt("puan", DirenisMain.oyuncu.getPoint());
			DirenisMain.oyuncu.setExp(DirenisMain.oyuncu.getExp() + dummyXP);
			mPrefsEditor.putInt("exp", DirenisMain.oyuncu.getExp());
			if (DirenisMain.oyuncu.getExp() >= (100 + DirenisMain.oyuncu
					.getLevel() * DirenisMain.oyuncu.getLevel())) {
				DirenisMain.oyuncu.setExp(DirenisMain.oyuncu.getExp()
						- (100 + DirenisMain.oyuncu.getLevel()
								* DirenisMain.oyuncu.getLevel()));
				DirenisMain.oyuncu.setLevel(DirenisMain.oyuncu.getLevel() + 1);
				mPrefsEditor.putInt("level", DirenisMain.oyuncu.getLevel());
				mPrefsEditor.commit();
			}

			if (DirenisMain.oyuncu.getPoint() <= 0) {
				mPrefsEditor.putBoolean("saved", false);

				hakikiPoint = (cumulative + 1000
						* (DirenisMain.oyuncu.getLevel() - 1) + 10
						* (mSharedPrefs.getInt("exp", 0)) + ((DirenisMain.date
						.getSeconds() - DirenisMain.dateBegins.getSeconds()) % 1000000) / 1000);
				alertDialog
						.setMessage("Puanın sıfırlandı. Gelecek sefere daha dikkatli olman gerek.Toplam puanın: "
								+ String.valueOf(hakikiPoint).toUpperCase());

				DirenisMain.highScoreFlag = true;
				mPrefsEditor.commit();
				gameOver = true;
				// myHandler.postDelayed(new Runnable() { gotoState1(); }, 250);
				if(gameOver) alertDialog.show();
			} else
				mPrefsEditor.putBoolean("saved", true);
			mPrefsEditor.commit();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static JSONObject parse(InputStream is) {
		String rawDB;
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader bfr = new BufferedReader(isr);
			rawDB = bfr.readLine();
			isr.close();
			bfr.close();
			return new JSONObject(rawDB);

		} catch (Exception e) {
			Log.e("ERROR", "hata oldu");
			e.printStackTrace();
			return null;
		}
	}

	private String SuccessFail() {
		Random generator = new Random();
		int zorlukRandom = generator.nextInt(100);
		double itemPower = 0.6;
		SharedPreferences mSharedPrefs = getSharedPreferences("xmlFile",
				MODE_PRIVATE);
		int mPuan = mSharedPrefs.getInt("puan", 200);
		SharedPreferences.Editor mPrefsEditor = mSharedPrefs.edit();
		if (item.equals("Maske"))
			itemPower = mSharedPrefs.getFloat("itemPower0",
					(float) DirenisMain.oyuncu.itemPowers[0]);

		else if (item.equals("Eldiven"))
			itemPower = mSharedPrefs.getFloat("itemPower1",
					(float) DirenisMain.oyuncu.itemPowers[1]);
		else if (item.equals("İlaç"))
			itemPower = mSharedPrefs.getFloat("itemPower2",
					(float) DirenisMain.oyuncu.itemPowers[2]);
		if (zorluk > zorlukRandom
				+ (DirenisMain.oyuncu.getLevel() * 0.6 * itemPower))
			return "Fail";
		else
			return "Success";
	}

	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(Answer.this, Generate.class);
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
		getMenuInflater().inflate(R.menu.answer, menu);
		return true;
	}

}
