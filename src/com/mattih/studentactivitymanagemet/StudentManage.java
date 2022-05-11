package com.mattih.studentactivitymanagemet;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentManage extends Activity {

	EditText ad_id, ad_name, ad_pas,ad_ans;

	DbConnection db;
	Button ins_btn, del_btn, upd_btn,vw_btn;
	String sid ;
	protected void Alert(String si) {

		// TODO Auto-generated method stub
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setMessage("Do you want to delet YOUR Account?\n You will loose all your data");
		alertDialogBuilder.setPositiveButton("Continue",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						boolean isdeleted=db.deletAccount(ad_id.getText().toString());
						if(isdeleted){
							Toast.makeText(getApplicationContext(), "deleting...", Toast.LENGTH_LONG).show();
							Toast.makeText(getApplicationContext(), "Account Deleted Succefully", Toast.LENGTH_LONG).show();

							//Intent innn = new Intent(getApplicationContext(),Login_Activity.class);
							//startActivity(innn);
							finish();

						}
					}
				});

		alertDialogBuilder.setNegativeButton("cancel",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
				;
					}
				});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
	public void addListener() {
		ins_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (check()) {
					boolean ins = db.insertAdmin(ad_id.getText().toString(),
							ad_name.getText().toString(), ad_pas.getText()
									.toString(),ad_ans.getText().toString());

					if (ins)
						Toast.makeText(getApplicationContext(), "Inserted",
								Toast.LENGTH_LONG).show();

					else {
						Toast.makeText(getApplicationContext(),
								"Data Not Inserted", Toast.LENGTH_LONG).show();
					}

				} else {
					Toast.makeText(getApplicationContext(),
							"Inserte value first", Toast.LENGTH_LONG).show();
					ad_id.setError("plesase provide an information");

				}
			}
		});
		upd_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				if(ad_id.getText().toString().equals(sid)){
					ad_id.setError("Please Enter Your Id Update");
				}else{
					ArrayList<String> s=db.getStudentData(ad_id.getText().toString());
					if(s!=null){
						ad_name.setText(s.get(0));
						ad_pas.setText(s.get(1));
						ad_ans.setText(s.get(2));
					}
				}
				
			}
		});
		vw_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		del_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!ad_id.getText().toString().equals(sid)){
					ad_id.setError("Please Enter Your Id to Delete");
				}else{
					Alert(ad_id.getText().toString());
					
					}
				}
			
		});
	}

	protected boolean check() {
		// TODO Auto-generated method stub
		if (ad_id.getText().toString().equals("")
				|| ad_name.getText().toString().equals("")
				|| ad_pas.getText().toString().equals("")
				|| ad_ans.getText().toString().equals("")) {
			return false;
		}
		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_mange);
		db = new DbConnection(this);
		ins_btn = (Button) findViewById(R.id.ad_ins_btn);
		del_btn = (Button) findViewById(R.id.ad_del_btn);
		upd_btn = (Button) findViewById(R.id.ad_upd_btn);
		vw_btn= (Button) findViewById(R.id.ad_view_btn);

		
		sid= getIntent().getExtras().getString("sid");
		ad_id = (EditText) findViewById(R.id.et_admin_id);
		ad_name = (EditText) findViewById(R.id.et_admin_name);
		ad_pas = (EditText) findViewById(R.id.et_admin_pass);
		ad_ans=(EditText) findViewById(R.id.ad_ans);
		if (sid.equals("0")) {
			del_btn.setEnabled(false);
			upd_btn.setEnabled(false);
			// del_btn.setEnabled(false);
		}
		addListener();

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_logout:
			Intent in = new Intent(getApplicationContext(),
					Login_Activity.class);
			startActivity(in);
			finish();

		}
		return super.onOptionsItemSelected(item);
	}

}
