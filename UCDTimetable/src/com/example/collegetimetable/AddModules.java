package com.example.collegetimetable;

import java.util.ArrayList;

import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;

public class AddModules extends SherlockActivity implements
		OnItemSelectedListener, OnClickListener {

	Spinner lectHours;
	Spinner lectMinutes;
	Spinner lectHoursEnd;
	Spinner lecturePractical;
	Spinner dayOfWeek;
	EditText moduleCode, moduleName, moduleLocation, additionalInfo;
	RadioGroup lpRadio, daysRadio;
	Spinner lectMinutesEnd;
	String[] classHours = { "07", "08", "09", "10", "11", "12", "13", "14",
			"15", "16", "17", "18", "19", "20", "21" };
	String[] classMins = { "00", "05", "10", "15", "20", "25", "30", "35",
			"40", "45", "50", "55" };
	String[] lectPrac = { "Lecture", "Practical" };
	String[] dayWeek = { "Monday", "Tuesday", "Wednesday", "Thursday",
			"Friday", "Saturday", "Sunday" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_modules);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		moduleCode = (EditText) findViewById(R.id.editText1);
		moduleName = (EditText) findViewById(R.id.editText2);
		moduleLocation = (EditText) findViewById(R.id.editText3);
		additionalInfo = (EditText) findViewById(R.id.editText4);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				AddModules.this, android.R.layout.simple_spinner_item,
				classHours);

		lectHours = (Spinner) findViewById(R.id.spinner1);
		lectHours.setAdapter(adapter);
		lectHours.setOnItemSelectedListener(this);

		lectHoursEnd = (Spinner) findViewById(R.id.spinner3);
		lectHoursEnd.setAdapter(adapter);
		lectHoursEnd.setOnItemSelectedListener(this);

		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
				AddModules.this, android.R.layout.simple_spinner_item,
				classMins);

		lectMinutes = (Spinner) findViewById(R.id.spinner2);
		lectMinutes.setAdapter(adapter2);
		lectMinutes.setOnItemSelectedListener(this);

		lectMinutesEnd = (Spinner) findViewById(R.id.spinner4);
		lectMinutesEnd.setAdapter(adapter2);
		lectMinutesEnd.setOnItemSelectedListener(this);

		ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(
				AddModules.this, android.R.layout.simple_spinner_item, lectPrac);

		lecturePractical = (Spinner) findViewById(R.id.spinner_lp);
		lecturePractical.setAdapter(adapter3);
		lecturePractical.setOnItemSelectedListener(this);

		ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(
				AddModules.this, android.R.layout.simple_spinner_item, dayWeek);

		dayOfWeek = (Spinner) findViewById(R.id.spinner_days);
		dayOfWeek.setAdapter(adapter4);
		dayOfWeek.setOnItemSelectedListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.add_modules, menu);

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
		} else if (itemId == R.id.cancel) {
			clearText();
			return (true);
		}

		else if (itemId == R.id.send) {
			addModule();

			Intent intent = new Intent();
			intent.setClassName("com.example.collegetimetable",
					"com.example.collegetimetable.ViewModules");
			startActivity(intent);

			return (true);
		}

		return (super.onOptionsItemSelected(item));
	}

	/* Method to open about page from options menu */
	private void openAbout() {
		Intent intent = new Intent();
		intent.setClassName("com.example.collegetimetable",
				"com.example.collegetimetable.About");
		startActivity(intent);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		int position = lectHours.getSelectedItemPosition();

		switch (position) {
		case 0:

			break;
		case 1:

			break;
		case 2:

			break;
		}

		int position2 = lectMinutes.getSelectedItemPosition();

		switch (position2) {
		case 0:

			break;
		case 1:

			break;
		case 2:

			break;
		}

		int position3 = lectHoursEnd.getSelectedItemPosition();

		switch (position) {
		case 0:

			break;
		case 1:

			break;
		case 2:

			break;
		}

		int position4 = lectMinutesEnd.getSelectedItemPosition();

		switch (position2) {
		case 0:

			break;
		case 1:

			break;
		case 2:

			break;
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	/* Method to add the module */

	private void addModule() {

		String modCode = moduleCode.getText().toString();
		String modName = moduleName.getText().toString();
		String modLocation = moduleLocation.getText().toString();
		String modAddInfo = additionalInfo.getText().toString();

		

		// Lecture or practical information string, and short version
		String modLectOrPract = lecturePractical.getSelectedItem().toString();
		String modLectOrPractShort = modLectOrPract.substring(0, 1);

		// Day of week string, and short version
		String modDay = dayOfWeek.getSelectedItem().toString();
		String modDayShort = modDay.substring(0, 3);

		String modStartHour = lectHours.getSelectedItem().toString();
		String modEndHour = lectHoursEnd.getSelectedItem().toString();
		String modStartMins = lectMinutes.getSelectedItem().toString();
		String modEndMins = lectMinutesEnd.getSelectedItem().toString();

		// ###### STRINGS FOR START AND END TIME #############
		String modStart = modStartHour + ":" + modStartMins;
		String modEnd = modEndHour + ":" + modEndMins;

		ModuleDatabaseHandler entry = new ModuleDatabaseHandler(AddModules.this);

		entry.open();
		entry.createEntry(modCode, modName, modLectOrPract,
				modLectOrPractShort, modDay, modDayShort, modStart, modEnd,
				modLocation, modAddInfo);
		entry.close();

		//

	}

	/* Method to clear text from ActionBar/options menu */
	private void clearText() {
		ViewGroup group = (ViewGroup) findViewById(R.id.rootLayout);
		clearForm(group);
		lpRadio.clearCheck();
		daysRadio.clearCheck();
		lectHours.setSelection(0);
		lectMinutes.setSelection(0);
		lectHoursEnd.setSelection(0);
		lectMinutesEnd.setSelection(0);
	}

	private void clearForm(ViewGroup group) {
		for (int i = 0, count = group.getChildCount(); i < count; ++i) {
			View view = group.getChildAt(i);
			if (view instanceof EditText) {
				((EditText) view).setText("");

			}

			if (view instanceof ViewGroup
					&& (((ViewGroup) view).getChildCount() > 0))
				clearForm((ViewGroup) view);
		}
	}

	@Override
	public void onClick(View v) {

	}


}
