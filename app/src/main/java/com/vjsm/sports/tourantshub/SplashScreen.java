package com.vjsm.sports.tourantshub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        progressBar=new ProgressBar(this);
        int numberOfSeconds = 2000/1000; // Ex : 20000/1000 = 20
        int secondsRemaining = (int) (4000 / 1000);
        int factor = 9/numberOfSeconds;
        int progressPercentage = (numberOfSeconds-secondsRemaining) * factor ;
       progressBar.setProgress(progressPercentage);

        progressBar.setVisibility(View.VISIBLE);

        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),LoginPage.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
