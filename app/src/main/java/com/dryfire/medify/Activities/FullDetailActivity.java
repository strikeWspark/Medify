package com.dryfire.medify.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.palette.graphics.Palette;

import com.dryfire.medify.Model.WhatsNew;
import com.dryfire.medify.R;
import com.dryfire.medify.Util.SharedPrefs;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

public class FullDetailActivity extends AppCompatActivity {

    private TextView detailTextView;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private ImageView toolbarImageview;
    private SharedPrefs sharedPrefs;
    private Palette.Swatch vibrantSwatch;
    private Palette.Swatch lightVibrantSwatch;
    private Palette.Swatch darkVibrantSwatch;
    private Palette.Swatch mutedSwatch;
    private Palette.Swatch lightMutedSwatch;
    private Palette.Swatch darkMutedSwatch;
    private Palette.Swatch currentSwatch = null;
    private WhatsNew whatsNew;
    boolean flag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        sharedPrefs = new SharedPrefs(this);
        if (sharedPrefs.loadNightModeState() == true) {
            setTheme(R.style.darktheme);
            flag = true;
        } else {
            setTheme(R.style.AppTheme);
            flag = false;
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_detail_activity);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        whatsNew = (WhatsNew) getIntent().getSerializableExtra("whatsnew");
        Toast.makeText(this, ""+ whatsNew.getName(), Toast.LENGTH_SHORT).show();

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.collapsingToolbarTitleTextColor);
        collapsingToolbarLayout.setTitle("Title");
        if (flag) {
            collapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#ffffff"));
        }
        toolbarImageview = (ImageView) findViewById(R.id.toolbarImageView);
        toolbarImageview.setBackgroundResource(whatsNew.getWhats_new_image());
        toolbar = findViewById(R.id.toolbar_full_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FullDetailActivity.this,MainActivity.class));
                finish();
            }
        });
            Bitmap bitmap = ((BitmapDrawable) getDrawable(R.drawable.image)).getBitmap();
            // BitmapDrawable ob = new BitmapDrawable(getResources(),bitmap);
            //collapsingToolbarLayout.setBackground(ob);

            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(@Nullable Palette palette) {
                    vibrantSwatch = palette.getVibrantSwatch();
                    lightVibrantSwatch = palette.getLightVibrantSwatch();
                    darkVibrantSwatch = palette.getDarkVibrantSwatch();
                    mutedSwatch = palette.getMutedSwatch();
                    lightMutedSwatch = palette.getLightMutedSwatch();
                    darkMutedSwatch = palette.getDarkMutedSwatch();

                    if (flag) {
                        currentSwatch = darkVibrantSwatch;
                    } else {
                        currentSwatch = vibrantSwatch;
                    }
                    if (currentSwatch != null) {
                        colorDecide();
                    } else {
                        Toast.makeText(FullDetailActivity.this, "Nhi mila color", Toast.LENGTH_SHORT).show();
                    }
                }
            });


            detailTextView = (TextView) findViewById(R.id.detail_text);


            detailTextView.setText("What is Lorem Ipsum?\n" +
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                    "\n" +
                    "Why do we use it?\n" +
                    "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\n" +
                    "\n" +
                    "\n" +
                    "Where does it come from?\n" +
                    "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\n" +
                    "\n" +
                    "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.\n" +
                    "\n" +
                    "Where can I get some?\n" +
                    "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.");
        }

        private void colorDecide(){
            collapsingToolbarLayout.setContentScrimColor(currentSwatch.getRgb());

            //    collapsingToolbarLayout.setContentScrimColor(currentSwatch.getBodyTextColor());

        }
    }


