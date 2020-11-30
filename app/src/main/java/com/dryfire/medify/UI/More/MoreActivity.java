package com.dryfire.medify.UI.More;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dryfire.medify.R;
import com.dryfire.medify.Util.SharedPrefs;

public class MoreActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Switch mSwitch;
    private SharedPrefs sharedPrefs;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPrefs = new  SharedPrefs(this);
        if(sharedPrefs.loadNightModeState() == true){
            setTheme(R.style.darktheme);
        }else{
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.medify_more);

        mSwitch = findViewById(R.id.myswitch);
        if(sharedPrefs.loadNightModeState() == true){
            mSwitch.setChecked(true);
        }else{

        }

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mSwitch.isChecked()){
                    sharedPrefs.setNightModeState(true);
                    restartApp();
                }else{
                    sharedPrefs.setNightModeState(false);
                    restartApp();
                }
            }
        });
        toolbar = findViewById(R.id.more_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void restartApp() {

        Intent i = new Intent(getApplicationContext(),MoreActivity.class);
        startActivity(i);
        finish();
    }
}
