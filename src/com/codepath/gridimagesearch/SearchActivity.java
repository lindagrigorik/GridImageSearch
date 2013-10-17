package com.codepath.gridimagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class SearchActivity extends Activity {

    private static final int FILTER_REQUEST_CODE = 1;
    private EditText etQuery;
    private GridView gvResults;
    private ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
    private ImageResultArrayAdapter imageAdapter;
    private SettingFilter sFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_search);
	setupViews();
	imageAdapter = new ImageResultArrayAdapter(this, imageResults);
	gvResults.setAdapter(imageAdapter);
	gvResults.setOnScrollListener(new EndlessScrollListener() {
	    @Override
	    public void loadMore(int page, int totalItemsCount) {
                // whatever code is needed to append new items to your AdapterView
		Log.d("DEBUG", "PAGE NUM IS "+page);
	        loadImages(totalItemsCount);
	    }
        });
	gvResults.setOnItemClickListener(new OnItemClickListener() {
	    @Override
	    public void onItemClick(AdapterView<?> adapter, View parent, int position, long rowId) {
		Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
		ImageResult imageResult = imageResults.get(position);
		i.putExtra("result", imageResult);
		startActivity(i);
	    }

	});
    }

    public void onImageSearch(View v) {
	//clear previous image search.
	imageResults.clear();
	//initial load
	for (int i=0; i<2; i++){
	    this.loadImages(i*8);
	}
    }
    
    private void loadImages(int startVal){
	String query = etQuery.getText().toString();
	AsyncHttpClient client = new AsyncHttpClient();
	StringBuilder url = new StringBuilder("https://ajax.googleapis.com/ajax/services/search/images?rsz=8&start=" + startVal + "&v=1.0&q=" + Uri.encode(query));
	if (sFilter != null) {
	    url = new StringBuilder("https://ajax.googleapis.com/ajax/services/search/images?rsz=8&start=" + startVal + "&v=1.0&q=" + Uri.encode(query));

	    if (sFilter.getColor() != null){
		url.append("&imgcolor=" + sFilter.getColor());
	    }
	    if (sFilter.getSize()!= null){
		url.append("&imgsz="+ sFilter.getSize());
	    }
	    if (sFilter.getType() != null) {
		url.append("&imgtype=" + sFilter.getType());
	    }
	    if (sFilter.getSite() != null){
		url.append("&as_sitesearch=" + sFilter.getSite());
	    }
	}
	client.get(url.toString(), new JsonHttpResponseHandler() {
	    @Override
	    public void onSuccess(JSONObject response) {
		JSONArray imageJsonResults = null;

		try {
		    imageJsonResults = response.getJSONObject("responseData").getJSONArray("results");
		    imageAdapter.addAll(ImageResult.fromJSONArray(imageJsonResults));
		    //Log.d("DEBUG", imageResults.toString());
		} catch (JSONException e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.search, menu);
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
	Intent i = new Intent(getApplicationContext(), FilterActivity.class);
	if (sFilter != null) {
	    i.putExtra("filter", sFilter);
	}
	startActivityForResult(i, FILTER_REQUEST_CODE);
	return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	if (resultCode == RESULT_OK && requestCode == FILTER_REQUEST_CODE) {
	    sFilter = (SettingFilter) data.getSerializableExtra("filter");
	}
    }

    private void setupViews() {
	etQuery = (EditText) findViewById(R.id.etQuery);
	gvResults = (GridView) findViewById(R.id.gvResults);
    }

}
