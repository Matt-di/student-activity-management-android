package com.mattih.studentactivitymanagemet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends Activity {

	TextView t1,t2,t3,t4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewe_activity);
		
		t1=(TextView) findViewById(R.id.ac_name);
		t2=(TextView) findViewById(R.id.ac_id);
		t3=(TextView) findViewById(R.id.ac_sub);
		t4=(TextView) findViewById(R.id.ac_dis);
		Bundle data=getIntent().getExtras();
		String an=data.getString("aname");
		String ai=data.getString("aid");
		String as=data.getString("subject");
		String ad=data.getString("disc");

		t1.setText(an);
		t2.setText(ai);
		t3.setText(as);
		t4.setText(ad);
	}

}
