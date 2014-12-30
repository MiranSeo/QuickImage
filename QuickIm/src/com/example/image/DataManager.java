package com.example.image;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataManager {

	private Context context = null;
	private ImageDBHelper dbHelper = null;
	
	public DataManager(Context context) {
		this.context = context;
		dbHelper = new ImageDBHelper(this.context);

	}
	
	public void insertImage(String imgUrl){
// getWritableDatabase() 나 getReadableDatabase() 은 시간이 오래걸릴 수 있기 때문에, AsyncTask 이나 IntentService 을 이용하여, 백그라운드 쓰레드에서 호출하도록한다.
	    SQLiteDatabase db               =   dbHelper.getWritableDatabase();
	    String sql                      =   "insert into image (photo) valuses ('" + imgUrl +"');";
	    db.execSQL(sql);
	    db.close();
	}
	
	
}
