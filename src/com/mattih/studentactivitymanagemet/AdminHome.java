package com.mattih.studentactivitymanagemet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminHome extends Activity {

	Button manage_student_btn, view_student_activity, manage_self_account_btn,
			popup;
	MenuItem menuItem;

	public void manageStudent() {
		manage_student_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Hello Admin:",
						Toast.LENGTH_LONG).show();
				Intent iii = new Intent(getApplicationContext(),
						Student_Manage_Activity.class);
				startActivity(iii);
			}
		});
		manage_self_account_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Hello Admin:",
						Toast.LENGTH_LONG).show();
				Intent ii = new Intent(getApplicationContext(),
						StudentManage.class);
				startActivity(ii);
			}
		});
		view_student_activity.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// Intent intent=new
				// Intent(getApplicationContext(),DisplayActivities.class);
				// startActivity(intent);
			}
		});
	}

	public void onClick(View item) {
		// TODO Auto-generated method stub
		switch (item.getId()) {
		case R.id.action_logout:
			Intent intent = new Intent(getApplicationContext(),
					Login_Activity.class);
			startActivity(intent);
			break;
		case R.id.action_settings:
			Toast.makeText(getApplicationContext(), "Setting",
					Toast.LENGTH_LONG).show();
			break;

		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_home);
		manage_self_account_btn = (Button) findViewById(R.id.manage_student_btn);
		view_student_activity = (Button) findViewById(R.id.view_st_ativity_btn);
		manage_student_btn = (Button) findViewById(R.id.self_account_btn);

		menuItem = (MenuItem) findViewById(R.id.action_logout);
		popup = (Button) findViewById(R.id.popup);
		manageStudent();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		return super.onMenuItemSelected(featureId, item);
	}

}
