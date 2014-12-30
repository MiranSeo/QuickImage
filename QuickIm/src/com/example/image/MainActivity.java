package com.example.image;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Intent�� ������ �׼ǰ� MIME Ÿ���� �����´�.
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        
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
        // ������ �ʿ��� pacelable �� ������ ��ü�� intent�� �־� �����ϸ� getParcelableExtra�� �޽��ϴ�
        if (imageUri != null) {
            //  ���� ���� image �� ����Ѵ�.
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
}
