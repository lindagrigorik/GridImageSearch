package com.codepath.gridimagesearch;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class FilterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.filter, menu);
		return true;
	}
	
	public void onSave(View v){
		//TODO: get all selected field info, store and return.
		this.finish();
	}

}
