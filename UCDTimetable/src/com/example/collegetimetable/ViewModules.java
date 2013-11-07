package com.example.collegetimetable;

import java.util.ArrayList;

import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

public class ViewModules extends SherlockActivity implements
		OnItemClickListener, OnItemLongClickListener {

	ListView l;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_modules_test);

		l = (ListView) findViewById(R.id.listView1);

		l.setAdapter(new VivzAdapter(this));

		l.setOnItemClickListener(this);

		l.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub

				Log.v("long clicked", "pos: " + arg2);
				return false;
			}

		});

	}

	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

		// TextView temp = (TextView) view;
		// Toast.makeText(this, temp.getText() + " " + i, Toast.LENGTH_SHORT)
		// .show();
		ModuleDatabaseHandler info = new ModuleDatabaseHandler(this);
		info.open();
		int rowID = info.getRowID(i);
		TextView invisible = (TextView) findViewById(R.id.textViewInvisible);

		String invisibleID = invisible.getText().toString();

		int invisibleKEYID = Integer.parseInt(invisibleID);

		// String module_info = info.getAllData(i);
		String module_code = info.getModCode(l);
		String module_name = info.getModName((int) l);
		String lect_prac = info.getLectPrac((int) l);
		String lect_day = info.getLectDay((int) l);
		String lect_start = info.getLectStart((int) l);
		String lect_end = info.getLectEnd((int) l);
		String lect_time = lect_start + " - " + lect_end;
		String lect_loc = info.getLectLoc((int) l);
		String lect_info = info.getLectInfo((int) l);

		info.close();

		Intent fullModuleDetails = new Intent();
		fullModuleDetails.setClassName("com.example.collegetimetable",
				"com.example.collegetimetable.ModuleDetails");

		// fullModuleDetails.putExtra("list1", module_info);
		fullModuleDetails.putExtra("modCode", module_code);
		fullModuleDetails.putExtra("modName", module_name);
		fullModuleDetails.putExtra("lectPrac", lect_prac);
		fullModuleDetails.putExtra("lectDay", lect_day);
		fullModuleDetails.putExtra("lectTime", lect_time);
		fullModuleDetails.putExtra("lectLoc", lect_loc);
		fullModuleDetails.putExtra("lectInfo", lect_info);
		fullModuleDetails.putExtra("row_to_delete", rowID);

		startActivity(fullModuleDetails);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.view_modules, menu);

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
		} else if (itemId == R.id.add_modules) {
			openAddModules();
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

	/* Method to open add modules page from options menu */
	private void openAddModules() {
		Intent intent = new Intent();
		intent.setClassName("com.example.collegetimetable",
				"com.example.collegetimetable.AddModules");
		startActivity(intent);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	class SingleRow {
		String title;
		String description;
		int image;
		String id;

		SingleRow(String title, String description, int image, String id) {
			this.title = title;
			this.description = description;
			this.image = image;
			this.id = id;

		}
	}

	class VivzAdapter extends BaseAdapter {

		ArrayList<SingleRow> list;
		Context context;

		VivzAdapter(Context c) {
			context = c;
			list = new ArrayList<SingleRow>();
			ModuleDatabaseHandler info = new ModuleDatabaseHandler(
					getBaseContext());
			info.open();
			ArrayList<String> descriptions2 = info.getDescriptionData();
			ArrayList<String> titles2 = info.getTitleData();
			ArrayList<String> days2 = info.getDay();
			ArrayList<String> ids2 = info.getIDs();

			String[] descriptions = new String[descriptions2.size()];
			String[] titles = new String[titles2.size()];
			String[] days = new String[days2.size()];
			String[] ids = new String[ids2.size()];

			descriptions = descriptions2.toArray(descriptions);
			titles = titles2.toArray(titles);
			days = days2.toArray(days);
			ids = ids2.toArray(ids);

			int length = descriptions.length;

			int[] images = new int[length];

			for (int i = 0; i < titles.length; i++) {

				images[i] = R.drawable.calendar;

			}

			for (int i = 0; i < titles.length; i++) {
				list.add(new SingleRow(titles[i], descriptions[i], images[i],
						ids[i]));
			}
			info.close();

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View row = inflater.inflate(R.layout.single_row, parent, false);
			TextView title = (TextView) row.findViewById(R.id.textView1);
			TextView description = (TextView) row.findViewById(R.id.textView2);
			ImageView image = (ImageView) row.findViewById(R.id.imageView1);
			TextView invisible = (TextView) row
					.findViewById(R.id.textViewInvisible);

			SingleRow temp = list.get(position);

			title.setText(temp.title);
			description.setText(temp.description);
			image.setImageResource(temp.image);
			invisible.setText(temp.id);

			return row;
		}

	}

}
