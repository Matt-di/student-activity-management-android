package com.mattih.studentactivitymanagemet;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.Toast;

public class DbConnection extends SQLiteOpenHelper {

	public static final String ACTIVITY_TABLE_NAME = "activity";
	public static final String ADMIN_TABLE_NAME = "admin";
	public static final String STUDENT_TABLE_NAME = "students";
	// activity table details

	public static final String COLUMN_A_CREATED = "activity_created_date";
	public static final String COLUMN_A_ID = "id";
	public static final String COLUMN_A_NAME = "activity_name";
	public static final String COLUMN_A_SCHEDULE_DESCRIPTION = "scheduled_description";
	public static final String COLUMN_A_SCHEDULE_TITLE = "scheduled_title";
	public static final String COLUMN_A_SCHEDULED_DATE = "date";
	public static final String COLUMN_A_SCHEDULED_TIME = "scheduled_time";
	// admin table deatils
	public static final String COLUMN_AD_ID = "admin_id";
	public static final String COLUMN_AD_NAME = "admin_name";
	public static final String COLUMN_AD_PASSWORD = "admin_password";
	public static final String COLUMN_AD_ANSWER = "answer";

	
	public static final String COLUMN_S_CREATED = "createdon";
	public static final String COLUMN_S_DEPARTMENT = "student_department";
	public static final String COLUMN_S_ID = "student_id";
	public static final String COLUMN_S_NAME = "student_name";

	public static final String COLUMN_S_PASSWORD = "password";
	public static final String DATABASE_NAME = "DataBa.db";
	public static final int DATABASE_VESRION = 1;
	// student table details

	public DbConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VESRION);
	}

	public boolean deletActivity(String Sid) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
        int d=db.delete(ACTIVITY_TABLE_NAME, COLUMN_A_ID+" = ?",new String[]{String.valueOf(Sid)});
        db.close();
        if(d==-1){
        	return false;
        }
        return true;
	}
	public ArrayList<HashMap<String, String>> getActvityData(String sid) {
		SQLiteDatabase db = this.getWritableDatabase();
		ArrayList<HashMap<String, String>> activitylist = new ArrayList<HashMap<String, String>>();
		String query = "SELECT * FROM " + ACTIVITY_TABLE_NAME
				+ " Where student_id='" + sid + "'";
		///to retrieve an activity tfor current user using it is login id
		Cursor cursor = db.rawQuery(query, null);
		while (cursor.moveToNext()) {
			HashMap<String, String> activity = new HashMap<String, String>();
			
			activity.put("aid",
					cursor.getString(cursor.getColumnIndex(COLUMN_A_ID)));
			activity.put("aname",
					cursor.getString(cursor.getColumnIndex(COLUMN_A_NAME)));
			activity.put("subject", cursor.getString(cursor
					.getColumnIndex(COLUMN_A_SCHEDULE_TITLE)));
			activity.put("disc", cursor.getString(cursor
					.getColumnIndex(COLUMN_A_SCHEDULE_DESCRIPTION)));
			activitylist.add(activity);
		}
		return activitylist;
	}

	public ArrayList<String> getStudentData(String string) {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<String> userList = new ArrayList<String>();
		String query = "SELECT *  FROM "+ADMIN_TABLE_NAME +" WHERE "+COLUMN_AD_ID+"='"+string+"'";
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.moveToFirst()) {
			userList.add(cursor.getString(cursor.getColumnIndex(COLUMN_AD_NAME)));
			userList.add(cursor.getString(cursor.getColumnIndex(COLUMN_AD_PASSWORD)));
			userList.add(cursor.getString(cursor.getColumnIndex(COLUMN_AD_ANSWER)));
			//userList.add(cursor.getString(cursor.getColumnIndex(COLUMN_S_NAME)));

		}
		return userList;
	}

	public boolean insertActivity(String sid, String a_id, String a_name,String s_titile, String s_disc/*,String d*/) {

		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cValues = new ContentValues();
		cValues.put(COLUMN_A_ID, a_id);
		cValues.put(COLUMN_S_ID, sid);
		cValues.put(COLUMN_A_NAME, a_name);
		cValues.put(COLUMN_A_SCHEDULE_TITLE, s_titile);
		cValues.put(COLUMN_A_SCHEDULE_DESCRIPTION, s_disc);
		//cValues.put(COLUMN_A_SCHEDULED_DATE, d);
		long newRowId = db.insert(ACTIVITY_TABLE_NAME, null, cValues);
		if (newRowId == -1) {
			return false;
		} else {
			return true;
		}
	}

	public boolean insertAdmin(String id, String name, String pass, String anse) {

		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cValues = new ContentValues();
		cValues.put(COLUMN_AD_ID, id);
		cValues.put(COLUMN_AD_NAME, name);
		cValues.put(COLUMN_AD_PASSWORD, pass);
		cValues.put(COLUMN_AD_ANSWER, anse);
		long newRowId = db.insert(ADMIN_TABLE_NAME, null, cValues);
		if (newRowId == -1) {
			return false;
		} else {
			return true;
		}
	}

	public boolean insertStudent(String id, String name, String dep, String pass) {

		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cValues = new ContentValues();
		cValues.put(COLUMN_S_ID, id);
		cValues.put(COLUMN_S_NAME, name);
		cValues.put(COLUMN_S_DEPARTMENT, dep);
		cValues.put(COLUMN_S_PASSWORD, pass);
		long newRowId = db.insert(STUDENT_TABLE_NAME, null, cValues);
		if (newRowId == -1) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// creating acitivity table
		String sql = "CREATE TABLE " + ACTIVITY_TABLE_NAME+"("
				+ COLUMN_A_ID+ " TEXT PRIMARY KEY,"
				+ COLUMN_S_ID+ " TEXT, " 
				+ COLUMN_A_NAME + " TEXT,"
				+ COLUMN_A_SCHEDULE_TITLE + " TEXT,"
				+ COLUMN_A_SCHEDULE_DESCRIPTION + " TEXT)"; 
				//+COLUMN_A_SCHEDULED_DATE+ " TEXT )";
		
		// creating student table
		String sql1 = "CREATE TABLE " + STUDENT_TABLE_NAME
				+ "( id integer PRIMARY KEY AUTOINCREMENT," 
				+COLUMN_S_ID+ " TEXT,"
				+ COLUMN_S_NAME + " TEXT," 
				+ COLUMN_S_DEPARTMENT+ " TEXT," 
				+ COLUMN_S_PASSWORD + " TEXT )";
		
		// creating admin table
		String sql2 = "CREATE TABLE " + ADMIN_TABLE_NAME
				+ "("+COLUMN_AD_ID
				+ " TEXT PRIMARY KEY," + COLUMN_AD_NAME + " TEXT," + COLUMN_AD_PASSWORD
				+ " TEXT, "+COLUMN_AD_ANSWER+ " TEXT )";
		db.execSQL(sql);
		db.execSQL(sql1);
		db.execSQL(sql2);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("DROP DATABASE IF EXISTS " + DATABASE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + ACTIVITY_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + ADMIN_TABLE_NAME);
		onCreate(db);
	}

	// method usedfor inserting activity details to activity table

	public boolean search(String uid, String pas, int type) {
		// TODO Auto-generated method stub

		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<HashMap<String, String>> userList = new ArrayList<HashMap<String, String>>();
		if (type == 1) {
			Cursor cursor = db.rawQuery("SELECT * FROM " + ADMIN_TABLE_NAME
					+ " WHERE " + COLUMN_AD_ID + "='" + uid + "'" + " AND "
					+ COLUMN_AD_PASSWORD + " ='" + pas + "' ", null);
			if (cursor.moveToNext()) {
				
				return true;
			}
			// return userList;
			return false;

		} else {
			Cursor cursor = db.rawQuery("SELECT * FROM " + STUDENT_TABLE_NAME
					+ " WHERE " + COLUMN_AD_ID + "='" + uid + "'" + " AND "
					+ COLUMN_AD_PASSWORD + " ='" + pas + "' ", null);
			if (cursor.moveToNext()) {
				HashMap<String, String> user = new HashMap<String, String>();
				user.put(COLUMN_S_ID,
						cursor.getString(cursor.getColumnIndex(COLUMN_S_ID)));
				user.put(COLUMN_S_NAME,
						cursor.getString(cursor.getColumnIndex(COLUMN_S_NAME)));
				user.put(COLUMN_S_DEPARTMENT, cursor.getString(cursor
						.getColumnIndex(COLUMN_S_DEPARTMENT)));
				user.put(COLUMN_S_PASSWORD, cursor.getString(cursor
						.getColumnIndex(COLUMN_S_PASSWORD)));
				userList.add(user);
				return true;
			}
			return false;

		}
	}

	public ArrayList<String> searchActivity(String string) {
		// TODO Auto-generated method stub
		ArrayList<String> aa=new ArrayList();
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor c=db.rawQuery("SELECT * FROM "+ACTIVITY_TABLE_NAME+" WHERE id='"+string+"'", null);
		if(c.moveToFirst()){
			aa.add(c.getString(c.getColumnIndex(COLUMN_A_ID)));
			aa.add(c.getString(c.getColumnIndex(COLUMN_A_NAME)));
			aa.add(c.getString(c.getColumnIndex(COLUMN_A_SCHEDULE_TITLE)));
			aa.add(c.getString(c.getColumnIndex(COLUMN_A_SCHEDULE_TITLE)));


		}
		db.close();
		return aa;
	}

	public String getData(String id, String ans) {
		// TODO Auto-generated method stub
		
		String pass = null;
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor c=db.rawQuery("SELECT * FROM "+ADMIN_TABLE_NAME+" WHERE "+COLUMN_AD_ID+"='"+id+
				"' AND "+COLUMN_AD_ANSWER+"='"+ans+"'", null);
		if(c.moveToFirst()){
			pass=c.getString(c.getColumnIndex(COLUMN_AD_PASSWORD));
		}
		return pass;
	}

	public boolean deletAccount(String sid) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
        int d=db.delete(ADMIN_TABLE_NAME, COLUMN_A_ID+" = ?",new String[]{String.valueOf(sid)});
        db.close();
        if(d==-1){
        	return false;
        }
        return true;
	}

	public boolean update(String sid, String acid, String acnam,
			String acsub, String acdis) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cValues = new ContentValues();
		cValues.put(COLUMN_A_ID, acid);
		cValues.put(COLUMN_S_ID, sid);
		cValues.put(COLUMN_A_NAME, acnam);
		cValues.put(COLUMN_A_SCHEDULE_TITLE, acsub);
		cValues.put(COLUMN_A_SCHEDULE_DESCRIPTION, acdis);
		//cValues.put(COLUMN_A_SCHEDULED_DATE, d);
		long newRowId = db.update(ACTIVITY_TABLE_NAME, cValues, COLUMN_A_ID+"=?", new String[]{acid});
		if (newRowId == -1) {
			return false;
		} else {
			return true;
		}
	}

}
