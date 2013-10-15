package com.codepath.gridimagesearch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.loopj.android.image.SmartImageView;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ImageDisplayActivity extends Activity {

    ImageResult result; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_image_display);
	result = (ImageResult) getIntent().getSerializableExtra("result");
	SmartImageView ivImage = (SmartImageView) findViewById(R.id.ivResult);
	ivImage.setImageUrl(result.getFullUrl());
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
	Intent shareIntent = new Intent(Intent.ACTION_SEND);
	Log.d("DEBUG", Uri.parse(result.getFullUrl()).toString());
	shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Image Attachment");
	shareIntent.setType("image/jpeg");
	shareIntent.putExtra(Intent.EXTRA_STREAM, result.getFullUrl());
	/*try {
	    File rootSdDirectory = Environment.getExternalStorageDirectory();

	    File pictureFile = new File(rootSdDirectory, "attachment.jpg");
	    if (pictureFile.exists()) {
	        pictureFile.delete();
	    }                   
	    pictureFile.createNewFile();

	    FileOutputStream fos = new FileOutputStream(pictureFile);

	    URL url = new URL(result.getFullUrl());
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    connection.setRequestMethod("GET");
	    connection.setDoOutput(true);
	    connection.connect();
	    InputStream in = connection.getInputStream();

	    byte[] buffer = new byte[1024];
	    int size = 0;
	    while ((size = in.read(buffer)) > 0) {
	        fos.write(buffer, 0, size);
	    }
	    fos.close();
	    
	    shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(pictureFile));

	} catch (Exception e) {
	    e.printStackTrace();
	}*/
	
	startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));
	return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.image_display, menu);
	return true;
    }

}
