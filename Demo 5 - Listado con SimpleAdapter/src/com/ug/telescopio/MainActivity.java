package com.ug.telescopio;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity  {
	Button btnSearch;
	Button btnOpenList;
	Button btnOpenActivity;
	ScrollView inputControls;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnSearch = (Button)findViewById(R.id.btnSearch);
		btnOpenList = (Button)findViewById(R.id.btnOpenList);
		btnOpenActivity = (Button)findViewById(R.id.btnOpenActivity);
		
		ButtonListener listener = new ButtonListener();
		btnSearch.setOnClickListener(listener);
		btnOpenList.setOnClickListener(listener);
		btnOpenActivity.setOnClickListener(listener);		
		
		/*
		Button btnList = new Button(this);		
		btnList.setText(getResources().getString(R.string.btn_list));
		btnList.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		mainContent.addView(btnList);
		*/
		
		LinearLayout mainContent = (LinearLayout)findViewById(R.id.layoutMainContent);		
		inputControls = (ScrollView) View.inflate(this, R.layout.input_controls_content, null);
		setInputControls();
		mainContent.addView(inputControls);
	}
	
	public void setInputControls() {

		SeekBar seekBar = (SeekBar)inputControls.findViewById(R.id.seekBar1);
		seekBar.setMax(10);
		seekBar.setProgress(5);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				Toast.makeText(getApplicationContext(), "Cambio a " + progress, Toast.LENGTH_SHORT).show();
			}
		});
		
		
		RatingBar ratingBar = (RatingBar)inputControls.findViewById(R.id.ratingBar1);
		ratingBar.setRating((float) 2.5);
				
		Spinner spinner = (Spinner)inputControls.findViewById(R.id.spinner1);

		ArrayList<String> names = new ArrayList<String>();		
		names.add("Hugo");
		names.add("Paco");
		names.add("Luis");
		
		ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(this, 
																     android.R.layout.simple_spinner_dropdown_item, 
																     names);
		spinner.setAdapter(namesAdapter);
		
		CheckBox checkbox = (CheckBox)inputControls.findViewById(R.id.checkBox1);
		checkbox.setChecked(true);
		
		RadioGroup radioGroup = (RadioGroup)inputControls.findViewById(R.id.radioGroup1);
		OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				String option = "";
				switch (checkedId) {
					case R.id.radio0 :
						option = "A";
						break;
					case R.id.radio1 :
						option = "B";
						break;
					case R.id.radio2 :
						option = "C";
						break;						
				}
				Log.e("TAG","Seleccionado " + option);
				
			}
		};
		radioGroup.setOnCheckedChangeListener(checkedChangeListener);		
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
			} else if (v.getId() == btnOpenList.getId()) {
				intent = new Intent(getApplicationContext(), EmailListActivity.class);
			}
			startActivity(intent);
		}
	}

}
