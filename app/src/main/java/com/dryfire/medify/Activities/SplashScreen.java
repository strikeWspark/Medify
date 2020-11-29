package com.dryfire.medify.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.dryfire.medify.R;
import com.dryfire.medify.Util.SharedPrefs;

public class SplashScreen extends AppCompatActivity implements Runnable{

    private SharedPrefs sharedPrefs;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        sharedPrefs = new SharedPrefs(this);
        if(sharedPrefs.loadNightModeState() == true){
            setTheme(R.style.darktheme);
        }else{
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(this,2000);
    }

    @Override
    public void run() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
