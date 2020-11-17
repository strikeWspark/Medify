package com.dryfire.medify.Util;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class SharedPrefs {
    SharedPreferences mysharedPreferences;
    public SharedPrefs(Context context){
        mysharedPreferences = context.getSharedPreferences("filename",Context.MODE_PRIVATE);
    }

    public void setNightModeState(boolean state){
        SharedPreferences.Editor editor = mysharedPreferences.edit();
        editor.putBoolean("NightMode",state);
        editor.commit();
    }

    public boolean loadNightModeState(){
        Boolean state = mysharedPreferences.getBoolean("NightMode",false);
        return state;
    }
}
