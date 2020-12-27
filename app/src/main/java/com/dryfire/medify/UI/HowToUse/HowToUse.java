package com.dryfire.medify.UI.HowToUse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.dryfire.medify.Activities.MainActivity;
import com.dryfire.medify.Adapter.HowToUsePageAdapter;
import com.dryfire.medify.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HowToUse extends AppCompatActivity {

    private ViewPager how_toUse_ViewPager;
    private MaterialButton next_button,getStartedButton;
    private TabLayout tabLayout;
    private HowToUsePageAdapter howToUsePageAdapter;
    int position;
    Animation getStartedtton_animation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_how_to_use);


        final List<ScreenItem> screenItemList = new ArrayList<>();

        screenItemList.add(new ScreenItem("Fresh Food","What is Lorem Ipsum?\\n\" +\n" +
                "                    \"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\\n\" +\n" +
                "                    \"\\n\" +\n" ,R.drawable.breaking_news));
        screenItemList.add(new ScreenItem("Fresh Food","What is Lorem Ipsum?\\n\" +\n" +
                "                    \"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\\n\" +\n",R.drawable.travel));
        screenItemList.add(new ScreenItem("Fresh Food","What is Lorem Ipsum?\\n\" +\n" +
                "                    \"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\\n\" +\n" ,R.drawable.trending));

        how_toUse_ViewPager = findViewById(R.id.howToUse_viewPager);
        next_button = findViewById(R.id.next_button);
        getStartedButton = findViewById(R.id.getStartedButton);
        tabLayout =  findViewById(R.id.how_to_use_tabLayout);

        getStartedtton_animation = AnimationUtils.loadAnimation(this,R.anim.getstartedbutton_animation);

        howToUsePageAdapter = new HowToUsePageAdapter(this,screenItemList);
        how_toUse_ViewPager.setAdapter(howToUsePageAdapter);

        tabLayout.setupWithViewPager(how_toUse_ViewPager);

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        position = how_toUse_ViewPager.getCurrentItem();
                        if(position < screenItemList.size() - 1){

                            getStartedButton.setVisibility(View.INVISIBLE);
                            tabLayout.setVisibility(View.VISIBLE);
                            next_button.setVisibility(View.VISIBLE);
                            position++;
                            how_toUse_ViewPager.setCurrentItem(position);
                        }

                        if(position==screenItemList.size()-1){
                            loadLastScreen();

                        }
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == screenItemList.size()-1){
                    loadLastScreen();
                }else{
                    getStartedButton.setVisibility(View.INVISIBLE);
                    tabLayout.setVisibility(View.VISIBLE);
                    next_button.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HowToUse.this, MainActivity.class));
                finish();
            }
        });
    }

    private void loadLastScreen() {

        getStartedButton.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.INVISIBLE);
        next_button.setVisibility(View.INVISIBLE);

        getStartedButton.setAnimation(getStartedtton_animation);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
