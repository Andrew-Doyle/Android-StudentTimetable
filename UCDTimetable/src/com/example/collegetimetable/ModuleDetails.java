package com.example.collegetimetable;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Menu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;

public class ModuleDetails extends SherlockActivity {

	TextView textviewcontent1,textviewcontent2,textviewcontent3,textviewcontent4,textviewcontent5,textviewcontent7,textviewcontent8;
	int rowid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_module_details);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		textviewcontent1 = (TextView) findViewById(R.id.textView1);
		textviewcontent1.setText(getIntent().getExtras().getString("modCode"));
		textviewcontent2 = (TextView) findViewById(R.id.textView2);
		textviewcontent2.setText(getIntent().getExtras().getString("modName"));
		textviewcontent3 = (TextView) findViewById(R.id.textView3);
		textviewcontent3.setText(getIntent().getExtras().getString("lectPrac"));
		textviewcontent4 = (TextView) findViewById(R.id.textView4);
		textviewcontent4.setText(getIntent().getExtras().getString("lectDay"));
		textviewcontent5 = (TextView) findViewById(R.id.textView5);
		textviewcontent5.setText(getIntent().getExtras().getString("lectTime"));
		textviewcontent7 = (TextView) findViewById(R.id.textView7);
		textviewcontent7.setText(getIntent().getExtras().getString("lectLoc"));
		textviewcontent8 = (TextView) findViewById(R.id.textView8);
		textviewcontent8.setText(getIntent().getExtras().getString("lectInfo"));

		rowid = getIntent().getExtras().getInt("row_to_delete");
		

	}

	// public void discard(){
	// ModuleDatabaseHandler info = new ModuleDatabaseHandler(this);
	// info.open();
	// info.deleteEntry(rowid);
	// info.close();
	//
	// }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.menu_module_details, menu);

		return (super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		if (itemId == android.R.id.home) {
			finish();
			return (true);
		} else if (itemId == R.id.about) {
			openAbout();
			return (true);
		} else if (itemId == R.id.discard) {

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ModuleDetails.this);

			// set title
			alertDialogBuilder.setTitle("Module will be deleted");

			// set dialog message
			alertDialogBuilder
					.setMessage("Are you sure?")
					.setCancelable(false)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									// if this button is clicked, close
									// current activity
									ModuleDatabaseHandler info = new ModuleDatabaseHandler(ModuleDetails.this);
									info.open();
									
									info.deleteEntry(rowid);
									info.close();
									
									Intent intent = new Intent();
									intent.setClassName(
											"com.example.collegetimetable",
											"com.example.collegetimetable.ViewModules");
									startActivity(intent);
								}
							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									// if this button is clicked, just close
									// the dialog box and do nothing
									dialog.cancel();
								}
							});

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();

			return (true);
		}

		return (super.onOptionsItemSelected(item));
	}
	
    /*Method to open about page from options menu*/
    private void openAbout(){
    	Intent intent = new Intent();
    	intent.setClassName("com.example.collegetimetable","com.example.collegetimetable.About");
    	startActivity(intent);
    }

}