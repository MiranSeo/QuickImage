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
// getWritableDatabase() �� getReadableDatabase() �� �ð��� �����ɸ� �� �ֱ� ������, AsyncTask �̳� IntentService �� �̿��Ͽ�, ��׶��� �����忡�� ȣ���ϵ����Ѵ�.
	    SQLiteDatabase db               =   dbHelper.getWritableDatabase();
	    String sql                      =   "insert into image (photo) valuses ('" + imgUrl +"');";
	    db.execSQL(sql);
	    db.close();
	}
	
	
}
