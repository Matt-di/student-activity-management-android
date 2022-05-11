package com.mattih.studentactivitymanagemet;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
@SuppressLint("NewApi")
public class ManageActivity extends Activity {

	private String a;

	EditText aid, aname, asubject, adiscription;
	boolean check;
	Button createbtn,searchbtn,update,deletebtn;
	DbConnection db;
	DatePicker dp;
	private String ii;

	private String n;
	private Ringtone ringTone;

	private String s;

	String sid = "";

	TimePicker tp;

	protected void addActivity() {
		
		searchbtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(aid.getText().toString().equals("")){
					aid.setError("Please Insert Id To search");
				}else{
					ArrayList<String> a=db.searchActivity(aid.getText().toString());
					if(a==null){
						Toast.makeText(getApplicationContext(),
								"Id Doesnt Exist", Toast.LENGTH_LONG).show();
					}
					else{
						aid.setText(a.get(0));
						aname.setText(a.get(1));
						asubject.setText(a.get(2));
						adiscription.setText(a.get(3));

					}
				}
			}
		});
		createbtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (check()) {
					Calendar current = Calendar.getInstance();
					Calendar cal = Calendar.getInstance();
					cal.set(dp.getYear(),
							dp.getMonth(),
							dp.getDayOfMonth(),
							tp.getCurrentHour(),
							tp.getCurrentMinute(), 00);

					// TODO Auto-generated method stub
					String acid = aid.getText().toString();
					String acnam = aname.getText().toString();
					String acsub = asubject.getText().toString();
					String acdis = adiscription.getText().toString();
					// Date date=cal.getTime();

					if (cal.compareTo(current) <= 0) {
						// The set Date/Time already passed
						Toast.makeText(getApplicationContext(),
								"Invalid Date/Time", Toast.LENGTH_LONG).show();
					} else {
						
						Alert();
						if (check) {
							boolean isinserted = db.insertActivity(sid,acid,acnam, acsub, acdis);
							if (isinserted) {
								setAlarm(cal,acnam,acsub);
								Toast.makeText(getApplicationContext(),
										"ACtivity Created", Toast.LENGTH_LONG)
										.show();
							} else {
								Toast.makeText(getApplicationContext(),
										"Error in Creatinging activiyt",
										Toast.LENGTH_LONG).show();

							}
						}
					}

				}
				else{
					Toast.makeText(getApplicationContext(),
							"Please Provide Value",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		update.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				if(!check()){
					Toast.makeText(getApplicationContext(),
							"Please Provide Value",
							Toast.LENGTH_LONG).show();
				}else{
					String acid = aid.getText().toString();
					String acnam = aname.getText().toString();
					String acsub = asubject.getText().toString();
					String acdis = adiscription.getText().toString();
					boolean isupdated=db.update(sid,acid,acnam,acsub,acdis);
					if(isupdated){
						Toast.makeText(getApplicationContext(),
								"Successfully Updated",
								Toast.LENGTH_LONG).show();
					}else{
						Toast.makeText(getApplicationContext(),
								"Incorrect Value",
								Toast.LENGTH_LONG).show();
					}
				}
			}
		});
		
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

	protected boolean check() {
		// TODO Auto-generated method stub
		if (aid.getText().toString().equals("")
				|| aname.getText().toString().equals("")
				|| asubject.getText().toString().equals("")
				|| adiscription.getText().toString().equals("")) {

			return false;
		} else
			return true;
	}

	@Override
	public ActionBar getActionBar() {
		// TODO Auto-generated method stub
		return super.getActionBar();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_activity);
		sid = getIntent().getExtras().getString("sid");
		Bundle i = getIntent().getExtras();
		db = new DbConnection(this);

		aid = (EditText) findViewById(R.id.editText4);
		aname = (EditText) findViewById(R.id.fgt_id);
		asubject = (EditText) findViewById(R.id.editText3);
		adiscription = (EditText) findViewById(R.id.editText4);

		sid = i.getString("sid");
		n = i.getString("aname");
		ii = i.getString("subject");
		s = i.getString("disc");

		createbtn = (Button) findViewById(R.id.createAc_btn);
		
		searchbtn=(Button) findViewById(R.id.searchAC_btn);
		update=(Button) findViewById(R.id.updateAC_btn);

		addActivity();
		dp = (DatePicker) findViewById(R.id.datePicker1);
		tp = (TimePicker) findViewById(R.id.timePicker1);
		Calendar now = Calendar.getInstance();
		dp.init(now.get(Calendar.YEAR),
				now.get(Calendar.MONTH),
				now.get(Calendar.DAY_OF_MONTH),
				null);
		tp.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
		tp.setCurrentMinute(now.get(Calendar.MINUTE));

		if (sid.equals("mat")) {
			aid.setText("");
			aname.setText(n);
			asubject.setText(ii);
			adiscription.setText(s);
			createbtn.setText("Update");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	
	@SuppressLint("NewApi")
	private void setAlarm(Calendar targetCal, String acnam, String acsub) {
		// info.setText("\n\n***\n" + "Alarm is set@ " + targetCal.getTime() +
		// "\n" + "***\n");

		Notification.Builder nf = new Notification.Builder(this);
		nf.setContentTitle(acnam);
		nf.setContentText(acsub);
		nf.setVibrate(new long[]{0,1000,1000,1000});
		nf.setSmallIcon(R.drawable.ic_launcher);
		Notification notfic = nf.build();
		Intent intent = new Intent(getBaseContext(), MyBroadcastReceiver.class);
		intent.putExtra("notification_id", 0);
		intent.putExtra("notification", notfic);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(
				getBaseContext(), 1, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		
		alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
				pendingIntent);
		Uri uriAlarm = RingtoneManager.getDefaultUri(R.raw.aus);
		String currentDateTimeString = DateFormat.getDateTimeInstance().format(
				new Date());
		if ((targetCal.getTime()).equals(currentDateTimeString)) {
			ringTone = RingtoneManager.getRingtone(getApplicationContext(),
					uriAlarm);
			Toast.makeText(getApplicationContext(), "Heeey wake up activiyt",
					Toast.LENGTH_LONG).show();

		}

	}
	

}
