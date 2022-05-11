package com.mattih.studentactivitymanagemet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Login_Activity extends Activity {

	Button btn_login;
	Button btn_signup,forgot_btn;
	DbConnection db;
	EditText et_user_id, et_password;
	RadioButton rd_st, rd_ad;

	public void addData() {
		forgot_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						ForgotActivity.class);
				startActivity(intent);
			}
		});
		btn_signup.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						StudentManage.class);
				intent.putExtra("sid", "0");
				startActivity(intent);
			}
		});
		btn_login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				if (check()) {

					
					if (db.search(et_user_id.getText().toString(),
							et_password.getEditableText().toString(), 1)) {
						Toast.makeText(getApplicationContext(), "Admin",
								Toast.LENGTH_LONG).show();
						Intent ii = new Intent(getApplicationContext(),
								StudentActivity.class);
						ii.putExtra("sid", et_user_id.getText().toString());
						et_user_id.setText("");
						et_password.setText("");
						startActivity(ii);

					} else {
						Toast.makeText(getApplicationContext(),
								"Incorrect Value", Toast.LENGTH_LONG).show();
						

					}

					
				} else {
					Toast.makeText(getApplicationContext(),
							"Please Insert Value first", Toast.LENGTH_LONG)
							.show();
				}

			}
		});

	}

	protected boolean check() {
		// TODO Auto-generated method stub

		if (et_user_id.getText().toString().equals("")
				|| et_password.getText().toString().equals("")) {
			et_user_id.setError("please fillOut");
			et_password.setError("insert Value");
			return false;
		}
		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		db = new DbConnection(this);
		et_user_id = (EditText) findViewById(R.id.et_userName);
		et_password = (EditText) findViewById(R.id.et_password);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_signup = (Button) findViewById(R.id.btn_cancel);
		forgot_btn=(Button) findViewById(R.id.forgot_btn);
		addData();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
