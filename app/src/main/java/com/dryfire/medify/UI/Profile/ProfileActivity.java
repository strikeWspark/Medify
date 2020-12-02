package com.dryfire.medify.UI.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.dryfire.medify.Activities.MainActivity;
import com.dryfire.medify.R;
import com.dryfire.medify.Util.SharedPrefs;

public class ProfileActivity extends AppCompatActivity {

    private ProfileViewModel profileViewModel;
    private Toolbar toolbar;
    private boolean isOpen = false;
    private ConstraintSet layout1,layout2;
    private ConstraintLayout constraintLayout;
    private ImageView imageViewPhoto;
    private LinearLayout linearLayout;
    private SharedPrefs sharedPrefs;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         sharedPrefs = new SharedPrefs(this);

         if(sharedPrefs.loadNightModeState() == true){
             setTheme(R.style.darktheme);
        }else{
             setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_profile_expanded);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        toolbar = findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layout1 = new ConstraintSet();
        layout2 = new ConstraintSet();
        imageViewPhoto = findViewById(R.id.photo);
        linearLayout = findViewById(R.id.card_layouts);
        constraintLayout = findViewById(R.id.profile_constraint_layout);
        layout2.clone(this,R.layout.activity_profile);
        layout1.clone(constraintLayout);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class).setFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP)
                );


            }
        });



        imageViewPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                collapsingBackground();

            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                collapsingBackground();
            }
        });
    }

    public void collapsingBackground(){
        if(!isOpen){
            TransitionManager.beginDelayedTransition(constraintLayout);
            layout2.applyTo(constraintLayout);
            isOpen = !isOpen;
        }else{
            TransitionManager.beginDelayedTransition(constraintLayout);
            layout1.applyTo(constraintLayout);
            isOpen = !isOpen;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ProfileActivity.this, MainActivity.class).setFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP)
        );

    }
}
