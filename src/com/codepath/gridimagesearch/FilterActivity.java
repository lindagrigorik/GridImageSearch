package com.codepath.gridimagesearch;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class FilterActivity extends Activity {
    private SettingFilter sFilter;
    private Spinner svSize;
    private Spinner svColor;
    private Spinner svType;
    private EditText etSite;
    private ArrayAdapter<String> sizeAdapter;
    private ArrayAdapter<String> colorAdapter;
    private ArrayAdapter<String> typeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_filter);
	svSize = (Spinner) findViewById(R.id.svSize);
	sizeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources()
	        .getStringArray(R.array.image_size_array));
	svSize.setAdapter(sizeAdapter);
	svColor = (Spinner) findViewById(R.id.svColor);
	colorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources()
	        .getStringArray(R.array.image_color_array));
	svColor.setAdapter(colorAdapter);
	svType = (Spinner) findViewById(R.id.svType);
	typeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources()
	        .getStringArray(R.array.image_type_array));
	svType.setAdapter(typeAdapter);
	etSite = (EditText) findViewById(R.id.etSite);
	SettingFilter filter = (SettingFilter) getIntent().getSerializableExtra("filter");
	if (filter != null) {
	    svSize.setSelection(sizeAdapter.getPosition(filter.getSize()));
	    svColor.setSelection(colorAdapter.getPosition(filter.getColor()));
	    svType.setSelection(typeAdapter.getPosition(filter.getType()));
	    etSite.setText(filter.getSite());
	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.filter, menu);
	return true;
    }

    public void onSave(View v) {
	svSize = (Spinner) findViewById(R.id.svSize);
	svColor = (Spinner) findViewById(R.id.svColor);
	svType = (Spinner) findViewById(R.id.svType);
	etSite = (EditText) findViewById(R.id.etSite);
	sFilter = new SettingFilter(svSize.getSelectedItem().toString(), svColor.getSelectedItem().toString(), svType
	        .getSelectedItem().toString(), etSite.getText().toString());
	Intent filterData = new Intent();
	filterData.putExtra("filter", sFilter);
	setResult(RESULT_OK, filterData);
	this.finish();
    }

}
