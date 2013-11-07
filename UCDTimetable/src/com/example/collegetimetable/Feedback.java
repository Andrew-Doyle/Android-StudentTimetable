package com.example.collegetimetable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class Feedback extends SherlockActivity {
	

	ImageButton clearForm, sendForm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feedback);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    new MenuInflater(this).inflate(R.menu.options_feedback, menu);
    
    return(super.onCreateOptionsMenu(menu));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    int itemId = item.getItemId();
	if (itemId == android.R.id.home) {
		finish();
		return(true);
	} else if (itemId == R.id.about) {
		openAbout();
		return(true);
	} else if (itemId == R.id.cancel) {
		clearText();
		return (true);
	} else if (itemId == R.id.send_email) {
		sendMail();
		return (true);
	}
	
	 else if (itemId == R.id.gohome) {
			openHome();
			return (true);
		}
    return(super.onOptionsItemSelected(item));
    }
   
    
    /*Method to open about page from options menu*/
    private void openAbout(){
    	Intent intent = new Intent();
    	intent.setClassName("com.example.collegetimetable","com.example.collegetimetable.About");
    	startActivity(intent);
    }
    
    
    /*Method to clear text from actionbar/options menu*/
    private void clearText(){
    	ViewGroup group = (ViewGroup) findViewById(R.id.rootLayout);
		clearForm(group);
    }
    
	/* Method to open view modules page from actionbar/options menu */
	private void openHome() {
		Intent intent = new Intent();
		intent.setClassName("com.example.collegetimetable",
				"com.example.collegetimetable.ViewModules");
		startActivity(intent);
	}
    
    /*Method to send feedback from actionbar/options menu*/
    private void sendMail(){
        final EditText name = (EditText) findViewById(R.id.editText1);
        final EditText mailaddress = (EditText) findViewById(R.id.editText2);
        final EditText comment = (EditText) findViewById(R.id.editText3);
        
		Intent email2 = new Intent(android.content.Intent.ACTION_SEND);
		email2.setType("plain/text");
        email2.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"andrew.doyle@ucdconnect.ie",mailaddress.getText().toString()});
        email2.putExtra(android.content.Intent.EXTRA_SUBJECT, "College Timetable Feedback Form");
        email2.putExtra(android.content.Intent.EXTRA_TEXT, "Name: "+name.getText().toString()+'\n'+"Email Address: "+mailaddress.getText().toString()+'\n'+"Comment: "+comment.getText().toString()+'\n'+'\n'+"Sent from the College Timetable Android App.");

        /* Send it off to the Activity-Chooser */
        startActivity(Intent.createChooser(email2, "Send mail..."));
    }
    
    
	/*ViewGroup group = (ViewGroup) findViewById(R.id.rootLayout);
    clearForm(group);*/
    
    private void clearForm(ViewGroup group)
    {       
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
        View view = group.getChildAt(i);
        if (view instanceof EditText) {
            ((EditText)view).setText("");
        }

        if(view instanceof ViewGroup && (((ViewGroup)view).getChildCount() > 0))
            clearForm((ViewGroup)view);
    }
    }
    
}
