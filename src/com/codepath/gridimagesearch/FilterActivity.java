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
	sizeAdapter = new ArrayAdapter<String>(this, R.layout.contact_spinner_row_nothing_selected, getResources()
	        .getStringArray(R.array.image_size_array));
	svSize.setAdapter(
        new NothingSelectedSpinnerAdapter(
        	sizeAdapter,
                R.layout.contact_spinner_row_nothing_selected,
                this));
	svColor = (Spinner) findViewById(R.id.svColor);
	colorAdapter = new ArrayAdapter<String>(this, R.layout.contact_spinner_row_nothing_selected, getResources()
	        .getStringArray(R.array.image_color_array));
	svColor.setAdapter(
	        new NothingSelectedSpinnerAdapter(
	        	colorAdapter,
	                R.layout.contact_spinner_row_nothing_selected,
	                this));
	svType = (Spinner) findViewById(R.id.svType);
	typeAdapter = new ArrayAdapter<String>(this, R.layout.contact_spinner_row_nothing_selected, getResources()
	        .getStringArray(R.array.image_type_array));
	svType.setAdapter(
	        new NothingSelectedSpinnerAdapter(
	        	typeAdapter,
	                R.layout.contact_spinner_row_nothing_selected,
	                this));
	etSite = (EditText) findViewById(R.id.etSite);
	SettingFilter filter = (SettingFilter) getIntent().getSerializableExtra("filter");
	if (filter != null) {
	    svSize.setSelection(sizeAdapter.getPosition(filter.getSize())+1);
	    svColor.setSelection(colorAdapter.getPosition(filter.getColor())+1);
	    svType.setSelection(typeAdapter.getPosition(filter.getType())+1);
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
	String size = svSize.getSelectedItem() != null ? svSize.getSelectedItem().toString():null;
	String color = svColor.getSelectedItem() != null? svColor.getSelectedItem().toString():null;
	String type = svType.getSelectedItem() != null?svType.getSelectedItem().toString():null;
	String site = etSite.getText().toString();
	sFilter = new SettingFilter(size, color, type, site);
	Intent filterData = new Intent();
	filterData.putExtra("filter", sFilter);
	setResult(RESULT_OK, filterData);
	this.finish();
    }

}
