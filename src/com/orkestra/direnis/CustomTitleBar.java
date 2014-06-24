package com.orkestra.direnis;

import com.orkestra.direnis.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;

public class CustomTitleBar extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	        setContentView(R.layout.mytitle);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.mytitle);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.custom_title_bar, menu);
		return true;
	}

}
