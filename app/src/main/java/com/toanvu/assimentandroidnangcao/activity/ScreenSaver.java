package com.toanvu.assimentandroidnangcao.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.toanvu.assimentandroidnangcao.login_logout.AskLogin;
import com.toanvu.assimentandroidnangcao.R;

public class ScreenSaver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_saver);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(ScreenSaver.this,AskLogin.class);
                    startActivity(intent);
                }
            }
        });

        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
