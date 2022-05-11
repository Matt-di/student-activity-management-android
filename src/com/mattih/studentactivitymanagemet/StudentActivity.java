package com.mattih.studentactivitymanagemet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StudentActivity extends Activity {

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.action_logout:
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			alertDialogBuilder.setMessage("Do You wanna Logout?\n You will loose your data");
			alertDialogBuilder.setPositiveButton("continue",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							Toast.makeText(getApplicationContext(), "LOGOUT", Toast.LENGTH_LONG).show();
							Intent a = new Intent(getApplicationContext(),
									Login_Activity.class);
							startActivity(a);
							finish();
						}
					});

			alertDialogBuilder.setNegativeButton("cancel",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//check = false;
							//finish();
						}
					});

			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
			
			break;
		case R.id.action_about:
			Toast.makeText(getApplicationContext(), "LOGOUT", Toast.LENGTH_LONG).show();
			Intent a1 = new Intent(getApplicationContext(),
					AboutUsActivity.class);
			startActivity(a1);
			break;
		default :
			
			return super.onOptionsItemSelected(item);

		}
		return true;
	}
	protected boolean check=false;

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
		Alert();
		
		if(check){
			Intent ii = new Intent(Intent.ACTION_MAIN);
			ii.addCategory(Intent.CATEGORY_HOME);
			ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(ii);
			finish();
		}
		
	}
	protected void Alert() {

		// TODO Auto-generated method stub
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setMessage("Do You wanna create ?");
		alertDialogBuilder.setPositiveButton("continue",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						check = true;
					}
				});

		alertDialogBuilder.setNegativeButton("cancel",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						check = false;
						finish();
					}
				});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
	Button make_schedule_btn, manage_account_btn, view_schedule_btn;
	String sid = "";
	private Button manage_schedule_btn;

	private void addListner() {
		// TODO Auto-generated method stub
		make_schedule_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Make Activituy",
						Toast.LENGTH_LONG).show();
				Intent ii = new Intent(getApplicationContext(),
						ManageActivity.class);
				ii.putExtra("sid", sid);
				startActivity(ii);
			}
		});
		view_schedule_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Toast.makeText(getApplicationContext(), "Display Activity",
						Toast.LENGTH_LONG).show();
				Intent a = new Intent(getApplicationContext(),
						DisplayActivities.class);
				a.putExtra("sid", sid);
				startActivity(a);
			}
		});
		manage_account_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Edit Scedule",
						Toast.LENGTH_LONG).show();
				Intent a = new Intent(getApplicationContext(),
						StudentManage.class);
				a.putExtra("sid", sid);
				startActivity(a);
			}
		});
		manage_schedule_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in=new Intent(getApplicationContext(),Student_Manage_Activity.class);
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student);
		sid = getIntent().getExtras().getString("sid");
		make_schedule_btn = (Button) findViewById(R.id.mk_schedule_btn);
		manage_account_btn = (Button) findViewById(R.id.manage_account_btn);
		view_schedule_btn = (Button) findViewById(R.id.view_schedule_btn);
		manage_schedule_btn=(Button) findViewById(R.id.manage_schedule_btn);
		
		addListner();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
