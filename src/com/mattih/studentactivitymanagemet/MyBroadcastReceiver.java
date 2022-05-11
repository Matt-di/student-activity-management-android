package com.mattih.studentactivitymanagemet;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

	Context con;
	MediaPlayer mp;

	@Override
	public void onReceive(final Context context, Intent arg1) {
		// TODO Auto-generated method stub

		con = context;
		mp = MediaPlayer.create(context, R.raw.aus);
		mp.start();
		//mp.getDuration();
		NotificationManager nm = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification n = arg1.getParcelableExtra("notification");
		int id = arg1.getIntExtra("notification_id", 0);
		//n.vibrate=new long[]{0,1000,1000,1000,1000,1000,1000,1000,1000};
		//Vibrator v=(Vibrator) getSystemService(context.VIBRATOR_SERVICE);
		nm.notify(id, n);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(con);
		builder.setMessage("are You Sure You want To Exit");
		builder.setCancelable(false);
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub

			}
		});
		Toast.makeText(context, "Wake up", Toast.LENGTH_LONG).show();

	}

}
