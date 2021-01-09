package com.dryfire.medify.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.dryfire.medify.R;
import com.dryfire.medify.Util.SharedPrefs;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity implements Runnable{

    private SharedPrefs sharedPrefs;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
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

        statusBarView(getWindow());


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        new Handler().postDelayed(this,2000);
    }

    @Override
    public void run() {
        if(mUser != null){
            startActivity(new Intent(this,MainActivity.class));

        }else{
            startActivity(new Intent(this,LoginActivity.class));
        }
        finish();

    }
    public void statusBarView(Window window){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
}
