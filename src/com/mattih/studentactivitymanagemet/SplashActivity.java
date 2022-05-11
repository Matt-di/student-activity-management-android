package com.mattih.studentactivitymanagemet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		Thread th=new Thread(){
			public void run(){
				try{
					sleep(3000);
				}catch(Exception e){
					
				}finally{
					Intent lo=new Intent(getApplicationContext(),Login_Activity.class);
					startActivity(lo);
					finish();
				}
			}
			
		};
		th.start();
		
	}

}
