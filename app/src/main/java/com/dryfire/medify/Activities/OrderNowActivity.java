package com.dryfire.medify.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dryfire.medify.Model.OrderNow;
import com.dryfire.medify.R;
import com.dryfire.medify.Util.SharedPrefs;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class OrderNowActivity extends AppCompatActivity {

    private BottomSheetBehavior bottomSheetBehavior;
    private Button expand;
    private ImageView imageView;
    private TextView descriptionTextView;
    private SharedPrefs sharedPrefs;
    OrderNow orderNow;
    private Toolbar toolbar;
    boolean statusBar_flag;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        sharedPrefs = new SharedPrefs(this);

        if(sharedPrefs.loadNightModeState() == true){
            setTheme(R.style.darktheme);
        }else{
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordernow);

        new SplashScreen().statusBarView(getWindow());

        orderNow = (OrderNow) getIntent().getSerializableExtra("order");
        Toast.makeText(this, "" + orderNow.getItem_name(), Toast.LENGTH_SHORT).show();
        View bottomsheet = findViewById(R.id.bottomsheet);
        toolbar = findViewById(R.id.ordernow_toolbar);
        imageView = findViewById(R.id.order_now_imageView);
        descriptionTextView = findViewById(R.id.ordernow_description_textView);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView.setBackgroundResource(orderNow.getFoodImage());

        descriptionTextView.setText( "What is Lorem Ipsum?\\n\" +\n" +
                "                    \"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\\n\" +\n" +
                "                    \"\\n\" +\n");
        bottomSheetBehavior = BottomSheetBehavior.from(bottomsheet);

      //  bottomsheet.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
       /* expand = findViewById(R.id.button);

        expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });*/

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderNowActivity.this,MainActivity.class));
                finish();
            }
        });
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                switch (newState){
                    case BottomSheetBehavior.STATE_DRAGGING:
                        if(!statusBar_flag){
                            getWindow().setFlags(
                                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
                            statusBar_flag = !statusBar_flag;
                        }else{
                            getWindow().setFlags(
                                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                            statusBar_flag = !statusBar_flag;
                        }



                        //  imageView.getLayoutParams().height -= 50;
                       // imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:

                        getWindow().setFlags(
                                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

                            break;


                        //imageView.getLayoutParams().height += 50;
                        //imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {




            }
        });
    }
}
