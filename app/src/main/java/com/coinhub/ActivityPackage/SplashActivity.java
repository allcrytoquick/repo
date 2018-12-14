package com.coinhub.ActivityPackage;
/**
 * all required libraries imported here
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.coinhub.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * setting up the content view of this screen
         */
        setContentView(R.layout.activity_splash);


        /**
         * handler for showing splash screen for 4 seconds and after then finishing this activity and staring the showcase activity
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /**
                 * finishing this activity after 4 seconds
                 */
                finish();
                /**
                 * starting showcase activity after 4 seconds
                 */
                startActivity(new Intent(SplashActivity.this, ShowcaseActivity.class));
            }
        }, 4000);//this will finish after 4 seconds


    }


}
