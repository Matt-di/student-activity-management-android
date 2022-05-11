package com.mattih.studentactivitymanagemet;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DisplayActivities extends Activity {
	DbConnection db;
	ListView lv;
	Object s;

	
	//menu when item is long pressed will pop uo
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		if (item.getTitle() == "Edit") {
			Toast.makeText(getApplicationContext(), "Editing....",
					Toast.LENGTH_LONG).show();
			Intent i = new Intent(getApplicationContext(), ManageActivity.class);
			String aname = ((HashMap<String, String>) s).get("aname");
			String subj = ((HashMap<String, String>) s).get("subject");
			String disc = ((HashMap<String, String>) s).get("disc");
			String aid = ((HashMap<String, String>) s).get("aid");

			i.putExtra("aname", aname);
			i.putExtra("subject", subj);
			i.putExtra("disc", disc);
			i.putExtra("sid", "mat");
			startActivity(i);

		} else if (item.getTitle() == "Delete") {
			
			String aname = ((HashMap<String, String>) s).get("aname");
			String subj = ((HashMap<String, String>) s).get("subject");
			String disc = ((HashMap<String, String>) s).get("disc");
			String aid = ((HashMap<String, String>) s).get("aid");
			
			boolean isdeleted=db.deletActivity(aid);
			if(isdeleted){
				Toast.makeText(getApplicationContext(), "Deleting....",
						Toast.LENGTH_SHORT).show();
				Toast.makeText(getApplicationContext(), "Succefully Deleted!.",
						Toast.LENGTH_LONG).show();
				startActivity(getIntent());
			}

		} else if (item.getTitle() == "View") {
			Toast.makeText(getApplicationContext(), "Openning....",
					Toast.LENGTH_LONG).show();
			Intent i = new Intent(getApplicationContext(), ViewActivity.class);
			String aname = ((HashMap<String, String>) s).get("aname");
			String subj = ((HashMap<String, String>) s).get("subject");
			String disc = ((HashMap<String, String>) s).get("disc");
			String aid = ((HashMap<String, String>) s).get("aid");

			i.putExtra("aname", aname);
			i.putExtra("subject", subj);
			i.putExtra("disc", disc);
			i.putExtra("aid", aid);
			startActivity(i);

		} else {
			return false;
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_activities_activity);
		db = new DbConnection(this);
		String sid = getIntent().getExtras().getString("sid");
		final ArrayList<HashMap<String, String>> activityList = db
				.getActvityData(sid);
		lv = (ListView) findViewById(R.id.activity_lv);
		ListAdapter adapter = new SimpleAdapter(getApplicationContext(),
				activityList, R.layout.row_format, new String[] { "aname",
						"subject", "disc" }, new int[] { R.id.name,
						R.id.subject, R.id.date });
		lv.setAdapter(adapter);
		registerForContextMenu(lv);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				s = lv.getItemAtPosition(arg2);

				// i.putExtra("phone", phone);
				// i.putExtra("country", country);

				// startActivity(i);

				// Toast.makeText(getApplicationContext(), name,
				// Toast.LENGTH_LONG).show();
			}
		});

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Select Action");
		menu.add(0, v.getId(), 0, "Edit");
		menu.add(0, v.getId(), 0, "Delete");
		menu.add(0, v.getId(), 0, "View");
	}

}
