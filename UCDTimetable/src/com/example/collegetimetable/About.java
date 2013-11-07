package com.example.collegetimetable;

import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class About extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.about);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.options_about, menu);

		return (super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		if (itemId == android.R.id.home) {
			finish();
			return (true);
		} else if (itemId == R.id.feedback) {
			openFeedback();
			return (true);
		}
		
		else if (itemId == R.id.gohome) {
			openHome();
			return (true);
		}
		return (super.onOptionsItemSelected(item));
	}

	/* Method to open feedback page from actionbar/options menu */
	private void openFeedback() {
		Intent intent = new Intent();
		intent.setClassName("com.example.collegetimetable",
				"com.example.collegetimetable.Feedback");
		startActivity(intent);
	}
	
	/* Method to open view modules page from actionbar/options menu */
	private void openHome() {
		Intent intent = new Intent();
		intent.setClassName("com.example.collegetimetable",
				"com.example.collegetimetable.ViewModules");
		startActivity(intent);
	}



}
