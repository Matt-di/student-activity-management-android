package com.mattih.studentactivitymanagemet;

import android.app.Activity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotActivity extends Activity{

	Button submit;
	EditText id,ans;
	TextView pastv;
	DbConnection db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		db=new DbConnection(this);
		setContentView(R.layout.activity_forgot);
		submit=(Button) findViewById(R.id.submit_btn);
		id = (EditText) findViewById(R.id.fgt_id);
		ans=(EditText) findViewById(R.id.fgt_ans);
		pastv=(TextView) findViewById(R.id.passtv);
		setListener();
	}
	private void setListener() {
		// TODO Auto-generated method stub
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(check()){
					String pass=db.getData(id.getText().toString(),ans.getText().toString());
					if(pass!=null){
						pastv.setText("Your Password:"+pass);
					}
					else{
						Toast.makeText(getApplicationContext(), "Incorrect input", Toast.LENGTH_LONG).show();
					}
				}
				
			}
		});
	}
	public boolean check(){
		if(id.getText().toString().equals("")||ans.getText().toString().equals(""))
			return false;
		return true;
	}

}
