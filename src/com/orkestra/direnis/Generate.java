package com.orkestra.direnis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.orkestra.direnis.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Generate extends Activity {
	public int soruRandom = 0;
	public static int exSoruRandom = 0;
	int a;
	static int zamansizSorular;
	public static int hakikiBackgroundNumber;
	public static String city;

	
	
	EditText edittext;
	
	
	TextView textView;
	TextView seviye;
	TextView puan;
	Button b1;
	Button b2;
	Button b3;
	Button dukkan;
	TextView level;
	TextView point;
	TextView time;
	TextView direnisciAdi;
	ImageView maskeimg;
	ImageView eldivenimg;
	ImageView destekimg;
	RelativeLayout main;
	Button twitter;
	Button facebookBtn;

	@SuppressWarnings("deprecation")
	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_generate);

		Random generator = new Random();
		setTitle(Olay.mekanAdi);
		main = (RelativeLayout) findViewById(R.id.relative_layout);
		seviye = (TextView) findViewById(R.id.generate_seviyetext);
		puan = (TextView) findViewById(R.id.generate_puantext);
		textView = (TextView) findViewById(R.id.generatetv1);
		b1 = (Button) findViewById(R.id.generatebtn1);
		b2 = (Button) findViewById(R.id.generatebtn2);
		b3 = (Button) findViewById(R.id.generatebtn3);
		dukkan = (Button) findViewById(R.id.generate_dukkan);
		level = (TextView) findViewById(R.id.generate_seviye);
		point = (TextView) findViewById(R.id.generate_puan);
		time = (TextView) findViewById(R.id.generate_time);
		direnisciAdi = (TextView) findViewById(R.id.generate_direnisci_adi);
		maskeimg = (ImageView) findViewById(R.id.maskeimg);
		eldivenimg = (ImageView) findViewById(R.id.eldivenimg);
		destekimg = (ImageView) findViewById(R.id.destekimg);
		twitter = (Button) findViewById(R.id.twitterbtn);
		facebookBtn = (Button) findViewById(R.id.facebookbtn);

		seviye.setTypeface(DirenisMain.tf);
		puan.setTypeface(DirenisMain.tf);
		textView.setTypeface(DirenisMain.tf);
		b1.setTypeface(DirenisMain.tf);
		b2.setTypeface(DirenisMain.tf);
		b3.setTypeface(DirenisMain.tf);
		dukkan.setTypeface(DirenisMain.tf);
		level.setTypeface(DirenisMain.tf);
		point.setTypeface(DirenisMain.tf);
		time.setTypeface(DirenisMain.tf);
		direnisciAdi.setTypeface(DirenisMain.tf);

		SharedPreferences mSharedPrefs = getSharedPreferences("xmlFile",
				MODE_PRIVATE);
		SharedPreferences.Editor mPrefsEditor = mSharedPrefs.edit();
		maskeimg.setImageResource(Ev.images[mSharedPrefs.getInt("itemIndex0",
				15)]);
		eldivenimg.setImageResource(Ev.images[mSharedPrefs.getInt("itemIndex1",
				15)]);
		destekimg.setImageResource(Ev.images[mSharedPrefs.getInt("itemIndex2",
				15)]);

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

		Random generator2 = new Random();
		zamansizSorular = generator2.nextInt(5);

		Random backgroundRandom = new Random();
		hakikiBackgroundNumber = backgroundRandom.nextInt(70);

		main.setBackgroundResource(images2[hakikiBackgroundNumber]);

		direnisciAdi.setText(DirenisMain.oyuncu.getName());

		final SimpleDateFormat std = new SimpleDateFormat("dd MMMM HH:mm");
		time.setText(std.format(DirenisMain.date).toUpperCase());
		point.setText(String.valueOf(DirenisMain.oyuncu.getPoint())
				.toUpperCase()+" ");
		level.setText(String.valueOf(DirenisMain.oyuncu.getLevel())
				.toUpperCase()+" ");

		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Bu günlük bu kadar");
		alertDialog
				.setMessage("Güneşin açmasıyla hayat yavaş yavaş normale dönüyor. Akşama tüm gücünle direnebilmen için eve gidip güç toplamalısın.");
		alertDialog.setButton("Eve Dön", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// here you can add functions
				Intent intent = new Intent(Generate.this, Ev.class);
				startActivity(intent);
			}
		});

		
		/* facebookBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url = "https://www.facebook.com/DirenisCapulcununYolu?fref=ts";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}
		});
	*/
		
		twitter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			
				
				String mPath = Environment.getExternalStorageDirectory().toString();   

				// create bitmap screen capture
				Bitmap bitmap;
				View v1 = findViewById(R.id.relative_layout);
				
				v1.setDrawingCacheEnabled(true);
				
		
				bitmap = Bitmap.createBitmap(v1.getDrawingCache());
				int height = bitmap.getHeight();
				int width = bitmap.getWidth();
				v1.setDrawingCacheEnabled(false);
				Bitmap size = Bitmap.createBitmap(bitmap, 0,0, width, height-120 );
				OutputStream fout = null;
				long timestamp = System.currentTimeMillis()/1000;
				File imageFile = new File(mPath, "Direnis" + "_" + String.valueOf(timestamp)+ ".jpg");

				try {
				    fout = new FileOutputStream(imageFile);
				    size.compress(Bitmap.CompressFormat.JPEG, 90, fout);
				    fout.flush();
				    fout.close();

				} catch (FileNotFoundException e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				} catch (IOException e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				}
				
	//TWITTER
				
				
				try {
					Intent intent = new Intent(Intent.ACTION_SEND);
					intent.setType("image/*");
					intent.putExtra(Intent.EXTRA_TEXT, "http://diren.is ");
					intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(imageFile));
					final PackageManager pm = getPackageManager();
					final List<?> activityList = pm.queryIntentActivities(
							intent, 0);
					int len = activityList.size();
					for (int i = 0; i < len; i++) {
						final ResolveInfo app = (ResolveInfo) activityList
								.get(i);
						if ("com.twitter.android.PostActivity"
								.equals(app.activityInfo.name)) {
							final ActivityInfo activity = app.activityInfo;
							final ComponentName name = new ComponentName(
									activity.applicationInfo.packageName,
									activity.name);
							intent.addCategory(Intent.CATEGORY_LAUNCHER);
							intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
									| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
							intent.setComponent(name);
							startActivity(intent);
							break;
						}
					}
				} catch (final ActivityNotFoundException e) {
					Toast.makeText(getApplicationContext(),
							"Twitter Uygulamanız Bulunmamaktadır",
							Toast.LENGTH_SHORT);
					Log.i("twitter", "no twitter native", e);
				}

			}
		});

		dukkan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent dukkanaGir = new Intent(Generate.this, Dukkan.class);
				Dukkan.direnistenMi = true;
				startActivity(dukkanaGir);

			}
		});

		b3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DirenisMain.date.setMinutes(DirenisMain.date.getMinutes() + 30);
				Intent intent = new Intent(Generate.this, Olay.class);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_up,
						R.anim.slide_out_up);
			}
		});

		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DirenisMain.date.setMinutes(DirenisMain.date.getMinutes() + 60);
				int cevapno = 1;
				Intent intent = new Intent(Generate.this, Answer.class);
				intent.putExtra("cevapNo", cevapno);
				intent.putExtra("soruNo", soruRandom);
				startActivity(intent);
			}
		});

		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				DirenisMain.date.setMinutes(DirenisMain.date.getMinutes() + 60);
				int cevapno = 0;
				Intent intent = new Intent(Generate.this, Answer.class);
				intent.putExtra("cevapNo", cevapno);
				intent.putExtra("soruNo", soruRandom);
				// intent.putExtra("random", a);
				startActivity(intent);
			}
		});

		JSONObject db = null;

		db = parse(getResources().openRawResource(R.raw.database));
		try {

			JSONArray dummy2;
			String dummy3;
			String dummy4;
			String dummy5;

			if (DirenisMain.date.getHours() >= 6
					&& DirenisMain.date.getHours() <= 14) {
				// Intent evegit = new Intent(Generate.this, Ev.class);
				DirenisMain.date.setHours(14);
				DirenisMain.date.setMinutes(30);
				// Toast.makeText(getApplicationContext(),
				// "Eve git, biraz dinlen", Toast.LENGTH_SHORT).show();
				alertDialog.show();
				// startActivity(evegit);

			}
			if (mSharedPrefs.getBoolean("city", true))
				dummy2 = db
						.getJSONObject("Ankara")
						.getJSONObject(Olay.mekanAdi)
						.getJSONArray(
								getHakikiTime(DirenisMain.date.getHours()));

			else
				dummy2 = db
						.getJSONObject("İstanbul")
						.getJSONObject(Olay.mekanAdi)
						.getJSONArray(
								getHakikiTime(DirenisMain.date.getHours()));

			a = generator.nextInt(dummy2.length());
			exSoruRandom = a;
			soruRandom = a;
			int loopCount = 0;
			while (soruRandom == exSoruRandom && loopCount != 5) {
				soruRandom = generator.nextInt(dummy2.length());
				loopCount++;
			}

			dummy3 = dummy2.getJSONObject(soruRandom).getString("Soru Text");

			dummy4 = dummy2.getJSONObject(soruRandom).getJSONArray("Cevaplar")
					.getJSONObject(0).getString("Cevap Text");
			dummy5 = dummy2.getJSONObject(soruRandom).getJSONArray("Cevaplar")
					.getJSONObject(1).getString("Cevap Text");

			textView.setText(String.valueOf(dummy3));
			b2.setText(dummy4);
			b1.setText(dummy5);

		} catch (JSONException e) {
			// TODO Auto-generated catch block

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
			e.printStackTrace();
			return null;
		}
	}

	public static String getHakikiTime(int time) {
		if (zamansizSorular == 1)
			return "Zamansız";
		if ((time >= 14) && (time <= 18))
			return "14-18";
		else if ((time >= 18) && (time <= 22))
			return "18-22";
		else if ((time >= 22) && (time <= 2))
			return "22-02";
		else if ((time >= 2) && (time <= 6))
			return "02-06";

		else
			return "Zamansız";

	}

	@SuppressWarnings("deprecation")
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(Generate.this, Olay.class);
		DirenisMain.date.setMinutes(DirenisMain.date.getMinutes() + 30);
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		return false;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.generate, menu);
		return true;
	}

	public class Screenshot {
		private final View view;

		/** Create snapshots based on the view and its children. */
		public Screenshot(View root) {
			this.view = root;
		}

		/**
		 * Create snapshot handler that captures the root of the whole activity.
		 */
		public Screenshot(Activity activity) {
			final View contentView = activity
					.findViewById(android.R.id.content);
			this.view = contentView.getRootView();
		}

		/** Take a snapshot of the view. */
		public Bitmap snap() {
			Bitmap bitmap = Bitmap.createBitmap(this.view.getWidth(),
					this.view.getHeight(), Config.ARGB_8888);
			Canvas canvas = new Canvas(bitmap);
			view.draw(canvas);
			return bitmap;
		}
	}
	
	 public static Bitmap takeScreenshot(Activity activity) {  
	      View view = activity.getWindow().getDecorView();  
	      view.setDrawingCacheEnabled(true);  
	      view.buildDrawingCache();  
	      Bitmap bitmap = view.getDrawingCache();  
	      Rect rect = new Rect();  
	      activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);  
	      int statusBarHeight = rect.top;  
	      int width = activity.getWindowManager().getDefaultDisplay().getWidth();  
	      int height = activity.getWindowManager().getDefaultDisplay().getHeight();  
	      Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, statusBarHeight, width,  
	          height - statusBarHeight);  
	      view.destroyDrawingCache();  
	      return bitmap2;  
	    }
	
}
