package com.dryfire.medify.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dryfire.medify.R;
import com.dryfire.medify.Util.SharedPrefs;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    SharedPrefs sharedPrefs;
    private MaterialButton loginButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        sharedPrefs = new SharedPrefs(this);
        if(sharedPrefs.loadNightModeState() == true){
            setTheme(R.style.darktheme);
        }else{
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (MaterialButton) findViewById(R.id.signin_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}
