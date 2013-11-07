package com.example.collegetimetable;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

public class WidgetConfig extends Activity implements OnClickListener {

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	

//	EditText info;
//	AppWidgetManager awm;
//	Context c;
//	int awID;
//
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.widget);
//
//		Button b = (Button) findViewById(R.id.button1);
//		b.setOnClickListener(TimetableWidget.class);
//		c = TimetableWidget.class;
//		
//		
//		//Getting information about the widget that launched this activity
//		Intent i = getIntent();
//		Bundle extras = i.getExtras();
//		if (extras != null) {
//			awID = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
//					AppWidgetManager.INVALID_APPWIDGET_ID);
//		}
//		else {
//			finish();
//		}
//		
//		awm = AppWidgetManager.getInstance(c);
//
//	}
//
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//	
//		
//		RemoteViews views = new RemoteViews(c.getPackageName(), R.layout.widget);
//		//views.setTextViewText(R.id.tvConfigInput,e);
//		
//		Intent in = new Intent(c, ViewModules.class);
//		PendingIntent pi = PendingIntent.getActivity(c,0,in,0);
//		
//		awm.updateAppWidget(awID, views);
//		
//		finish();
//	}

}
