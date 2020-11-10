package com.dryfire.medify.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dryfire.medify.Adapter.ViewPagerAdapter;
import com.dryfire.medify.Fragments.OrderNowFragment;
import com.dryfire.medify.Fragments.WhatsNewFragment;
import com.dryfire.medify.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    private Toolbar search_toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private FloatingActionButton fab;
    private AlertDialog.Builder searchBuilder;
    private AlertDialog searchDialog;
    Menu search_menu;
    MenuItem item_search;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tabLayout = (TabLayout) findViewById(R.id.naviagtion_table);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        fab = (FloatingActionButton) findViewById(R.id.search_fab);
        search_toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        searchBuilder = new AlertDialog.Builder(this);
        //setSearchtoolbar();



        adapter.addFragemnt(new WhatsNewFragment(),"What's New ");
        adapter.addFragemnt(new OrderNowFragment(),"Order Now");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



        tabLayout.getTabAt(0).setIcon(R.drawable.trend);
        tabLayout.getTabAt(1).setIcon(R.drawable.chatting);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        /*        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
       //             circleReveal(R.id.search_toolbar,1,true,true);
                }else{
                    search_toolbar.setVisibility(View.VISIBLE);
                }
                item_search.expandActionView(); */
                View dialog_view = getLayoutInflater().inflate(R.layout.search_dialog,null);
                searchBuilder.setView(dialog_view);
                searchDialog = searchBuilder.create();
                searchDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                searchDialog.show();

            }
        });


    }

  /*  private void setSearchtoolbar() {

        search_toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        if (search_toolbar != null) {
            search_toolbar.inflateMenu(R.menu.menu_search);
            search_menu=search_toolbar.getMenu();
            search_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        circleReveal(R.id.search_toolbar,1,true,false);
                    }

                    else
                        search_toolbar.setVisibility(View.GONE);
                }
            });

            item_search = search_menu.findItem(R.id.action_filter_search);

            item_search.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        circleReveal(R.id.search_toolbar,1,true,true);
                    }
                    else
                        search_toolbar.setVisibility(View.GONE);

                    return true;
                }

                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {

                    return true;
                }
            });

            initSearchView();


        } else
            Log.d("toolbar", "setSearchtollbar: NULL");
    }


    public void initSearchView()
    {
        final SearchView searchView =
                (SearchView) search_menu.findItem(R.id.action_filter_search).getActionView();

        // Enable/Disable Submit button in the keyboard

        searchView.setSubmitButtonEnabled(false);

        // Change search close button image

        ImageView closeButton = (ImageView) searchView.findViewById(R.id.search_close_btn);
        closeButton.setImageResource(R.drawable.ic_close);


        // set hint and the text colors

        EditText txtSearch = ((EditText) searchView.findViewById(R.id.search_src_text));
        txtSearch.setHint("Search..");
        txtSearch.setHintTextColor(Color.DKGRAY);
        txtSearch.setTextColor(getResources().getColor(R.color.colorPrimary));


        // set the cursor

        AutoCompleteTextView searchTextView = (AutoCompleteTextView) searchView.findViewById(R.id.search_src_text);
        try {
            Field mCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            mCursorDrawableRes.setAccessible(true);
            mCursorDrawableRes.set(searchTextView, R.drawable.ic_search); //This sets the cursor resource ID to 0 or @null which will make it visible on white background
        } catch (Exception e) {
            e.printStackTrace();
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callSearch(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                callSearch(newText);
                return true;
            }

            public void callSearch(String query) {
                //Do searching
                Log.i("query", "" + query);

            }

        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void circleReveal(int viewID, int posFromRight, boolean containsOverflow, final boolean isShow)
    {
        final View myView = findViewById(viewID);

        int width=myView.getWidth();

        if(posFromRight>0)
            width-=(posFromRight*getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_material))-(getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_material)/ 2);
        if(containsOverflow)
            width-=getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_overflow_material);

        int cx=width;
        int cy=myView.getHeight()/2;

        Animator anim;
        if(isShow)
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0,(float)width);
        else
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, (float)width, 0);

        anim.setDuration((long)220);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if(!isShow)
                {
                    super.onAnimationEnd(animation);
                    myView.setVisibility(View.GONE);
                    search_toolbar.setVisibility(View.GONE);
                }
            }
        });

        // make the view visible and start the animation
        if(isShow)
            myView.setVisibility(View.VISIBLE);

        // start the animation
        anim.start();


    }*/
}