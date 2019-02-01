package com.chesterlsy.mhwassistant.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.chesterlsy.mhwassistant.R;
import com.chesterlsy.mhwassistant.data.Constant;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private final Handler handler = new Handler();
    private final Launcher launcher = new Launcher();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);

        handler.postDelayed(launcher, Constant.Main.SPLASH_DURATION);
    }

    @Override
    protected void onStop() {
        handler.removeCallbacks(launcher);
        super.onStop();
    }

    private class Launcher implements Runnable {
        @Override
        public void run() {
            if (!isFinishing()) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        }
    }
}
