package com.ug.telescopio;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity  {
	Button btnSearch;
	Button btnOpenActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnSearch = (Button)findViewById(R.id.btnSearch);
		btnOpenActivity = (Button)findViewById(R.id.btnOpenActivity);
		btnSearch.setOnClickListener(new ButtonListener());
		btnOpenActivity.setOnClickListener(new ButtonListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class ButtonListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Intent intent = null;
			EditText searchQuery = (EditText)findViewById(R.id.editTextSearchQuery);
			String queryText = searchQuery.getText().toString();
			String url = "https://www.google.com/?q=" + queryText + "#q=" + queryText;
			if (v.getId() == btnSearch.getId()) {
				intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(url));				
			} else if (v.getId() == btnOpenActivity.getId()) {
				intent = new Intent(getApplicationContext(), ShowSearchQueryActivity.class);
				intent.putExtra(ShowSearchQueryActivity.QUERY, queryText);
			}
			startActivity(intent);
		}
	}

}
