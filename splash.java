package com.example.networkanalyzer20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            startActivity(new Intent(splash.this, MainActivity.class));
            finish();

        }, 2050);

        Window window=this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.black));
    }
}
