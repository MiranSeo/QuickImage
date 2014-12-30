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
        
        // Intent를 얻어오고 액션과 MIME 타입을 가져온다.
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        
        // Intent의 Action 종류에 따라 비교 후 해당 함수 수행.
        if (Intent.ACTION_SEND.equals(action) && type != null) {
           if (type.startsWith("image/")) {
                handleSendImage(intent); // 단일 image 를 처리한다.
            }
        } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleSendMultipleImages(intent); // 여러 image 들을 처리한다.
            }
        } 
    }
    
//주의 ! 받은 Binary data는 항상 UI Thread 가 아닌 다른 Thread에서 처리되도록 해야 한다.
    
    // Intent의 Type이 image일 경우 수행
    void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        // 보내는 쪽에서 pacelable 로 구현한 객체를 intent에 넣어 전달하면 getParcelableExtra로 받습니다
        if (imageUri != null) {
            //  전달 받은 image 를 사용한다.
        }
    }

    void handleSendMultipleImages(Intent intent) {
        ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (imageUris != null) {
            // 전달받은 여러개의 image 를 사용한다.
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
