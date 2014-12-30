package com.example.image;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.apache.http.util.ByteArrayBuffer;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {
	private DataManager dataManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Intent�� ������ �׼ǰ� MIME Ÿ���� �����´�.
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        
        dataManager = new DataManager(this);
        
        // Intent�� Action ������ ���� �� �� �ش� �Լ� ����.
        if (Intent.ACTION_SEND.equals(action) && type != null) {
           if (type.startsWith("image/")) {
                handleSendImage(intent); // ���� image �� ó���Ѵ�.
            }
        } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleSendMultipleImages(intent); // ���� image ���� ó���Ѵ�.
            }
        } 
    }
    
//���� ! ���� Binary data�� �׻� UI Thread �� �ƴ� �ٸ� Thread���� ó���ǵ��� �ؾ� �Ѵ�.
    
    // Intent�� Type�� image�� ��� ����
    void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        String url = imageUri.toString();
        // ������ �ʿ��� pacelable �� ������ ��ü�� intent�� �־� �����ϸ� getParcelableExtra�� �޽��ϴ�
        if (imageUri != null) {
            //  ���� ���� image �� ����Ѵ�.
        	byte[] logoImage = getLogoImage(url);
        	dataManager.insertImage(url);
        }
    }

    void handleSendMultipleImages(Intent intent) {
        ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (imageUris != null) {
            // ���޹��� �������� image �� ����Ѵ�.
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    private byte[] getLogoImage(String url){
        try {
                URL imageUrl = new URL(url);
                URLConnection ucon = imageUrl.openConnection();

                InputStream is = ucon.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);

                ByteArrayBuffer baf = new ByteArrayBuffer(500);
                int current = 0;
                while ((current = bis.read()) != -1) {
                        baf.append((byte) current);
                }

                return baf.toByteArray();
        } catch (Exception e) {
                Log.d("ImageManager", "Error: " + e.toString());
        }
        return null;
   }
}
