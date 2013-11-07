package com.example.collegetimetable;

import java.util.ArrayList;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.widget.Toast;

public class TimetableWidget extends AppWidgetProvider {

	Context context;

	@Override
	public void onUpdate(Context c, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(c, appWidgetManager, appWidgetIds);
		Context context = c;

		ModuleDatabaseHandler info = new ModuleDatabaseHandler(c);
		info.open();
		ArrayList<String> descriptions2 = info.getDescriptionWidget();
		ArrayList<String> titles2 = info.getTitleWidget();

		String[] descriptions = new String[descriptions2.size()];
		String[] titles = new String[titles2.size()];

		descriptions = descriptions2.toArray(descriptions);
		titles = titles2.toArray(titles);

		String title1 = titles[0];
		String description1 = descriptions[0];
		String title2 = titles[1];
		String description2 = descriptions[1];
		String title3 = titles[2];
		String description3 = descriptions[2];

		int length = descriptions.length;

		final int N = appWidgetIds.length;

		for (int i = 0; i < N; i++) {
			int awID = appWidgetIds[i];
			RemoteViews v = new RemoteViews(context.getPackageName(),
					R.layout.widget);
			v.setTextViewText(R.id.tvwidgetUpdate, title1);
			v.setTextViewText(R.id.tvwidgetUpdate2, description1);
			v.setTextViewText(R.id.tvwidgetUpdate3, title2);
			v.setTextViewText(R.id.tvwidgetUpdate4, description2);
			v.setTextViewText(R.id.tvwidgetUpdate5, title3);
			v.setTextViewText(R.id.tvwidgetUpdate6, description3);
			appWidgetManager.updateAppWidget(awID, v);
		}
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		Toast.makeText(context, "Timetable Widget Deleted", Toast.LENGTH_SHORT)
				.show();

	}

}
