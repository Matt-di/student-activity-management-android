package com.mattih.studentactivitymanagemet;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Student_Manage_Activity extends Activity {
	Button b_ins, b_clr, b_vw, b_del, b_srch, b_upd;
	DbConnection db;
	EditText et_st_name, et_st_depa, et_sid, et_st_pass, e5, e6;

	public void addData() {
		b_ins.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (check()) {
					boolean isinserted = db.insertStudent(et_sid.getText()
							.toString(), et_st_name.getText().toString(),
							et_st_depa.getText().toString(), et_st_pass
									.getText().toString());
					if (isinserted) {
						Toast.makeText(getApplicationContext(),
								"DATA INSERTEd", Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(getApplicationContext(),
								"DATA Not Inserted", Toast.LENGTH_LONG).show();

					}

				} else {
					Toast.makeText(getApplicationContext(),
							"please Insert Value", Toast.LENGTH_LONG).show();

				}
			}
		});
	}

	protected boolean check() {
		if (et_sid.getText().toString().equals("")
				|| et_st_name.getText().toString().equals("")
				|| et_st_depa.getText().toString().equals("")
				|| et_st_pass.getText().toString().equals(""))
			return false;
		else
			return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_student);
		db = new DbConnection(this);
		Bundle data = getIntent().getExtras();
		String aname = data.getString("aname");
		String aidd = data.getString("aidd");
		String subject = data.getString("subject");
		String disc = data.getString("disc");

		b_ins = (Button) findViewById(R.id.insert);
		b_clr = (Button) findViewById(R.id.clr);
		b_vw = (Button) findViewById(R.id.vview);
		b_del = (Button) findViewById(R.id.del);
		b_srch = (Button) findViewById(R.id.serach);
		b_upd = (Button) findViewById(R.id.update);

		et_st_name = (EditText) findViewById(R.id.st_name_et);
		et_st_depa = (EditText) findViewById(R.id.st_depart_et);
		et_sid = (EditText) findViewById(R.id.sid_et);
		et_st_pass = (EditText) findViewById(R.id.st_pass);

		et_st_name.setText(aname);
		et_sid.setText(aidd);
		et_st_depa.setText(subject);
		et_st_pass.setText(disc);
		addData();

	}

}
