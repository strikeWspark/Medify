package com.dryfire.medify.Activities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;


import com.dryfire.medify.Adapter.ViewPagerAdapter;
import com.dryfire.medify.Fragments.OrderNowFragment;
import com.dryfire.medify.Fragments.WhatsNewFragment;
import com.dryfire.medify.R;
import com.dryfire.medify.UI.HowToUse.HowToUse;
import com.dryfire.medify.UI.More.MoreActivity;
import com.dryfire.medify.Util.NavigationIconClickListener;
import com.dryfire.medify.UI.Profile.ProfileActivity;
import com.dryfire.medify.Util.SharedPrefs;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Toolbar search_toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LinearLayout sliding_linearLayout;
    private ViewPagerAdapter adapter;
    public FloatingActionButton fab;
    private AlertDialog.Builder searchBuilder;
    private AlertDialog searchDialog;
    private Switch aSwitch;
    private TextView moreButton,profileButton,howtoUseButton;
    DrawerLayout drawerLayout;
    private SharedPrefs sharedPrefs;
    private boolean flag = false;
    Menu search_menu;
    MenuItem item_search;
    private AppBarConfiguration mAppBarConfiguration;
    private MaterialButton search_button,cancel_button,done_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedPrefs = new  SharedPrefs(this);
        if(sharedPrefs.loadNightModeState() == true){
            setTheme(R.style.darktheme);
        }else{
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // sliding_linearLayout = findViewById(R.id.slidind_layout);
        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(this,
                findViewById(R.id.slidind_layout),
                new AccelerateDecelerateInterpolator(),
                this.getResources().getDrawable(R.drawable.toolbar_medify),
                this.getResources().getDrawable(R.drawable.ic_close_black)));

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

       // navigationView.setNavigationItemSelectedListener(this);



        tabLayout = (TabLayout) findViewById(R.id.naviagtion_table);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        fab = (FloatingActionButton) findViewById(R.id.search_fab);
        search_toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        moreButton = (TextView) findViewById(R.id.more);
        profileButton = (TextView) findViewById(R.id.medify_profile);
        howtoUseButton = (TextView) findViewById(R.id.medify_howtouse);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        searchBuilder = new AlertDialog.Builder(this);
        //setSearchtoolbar();

        moreButton.setOnClickListener(this);
        profileButton.setOnClickListener(this);
        howtoUseButton.setOnClickListener(this);

       /* mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_profile,R.id.nav_settings)
                .setDrawerLayout(drawerLayout)
                .build();
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController,mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView,navController);
*/
        adapter.addFragemnt(new WhatsNewFragment(),"What's New ");
        adapter.addFragemnt(new OrderNowFragment(),"Order Now");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                    if(position == 1){
                        flag = true;
                        fab.setImageResource(R.drawable.chatting);
                    }else{
                        flag = false;
                        fab.setImageResource(R.drawable.ic_search);
                    }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



      //  tabLayout.getTabAt(0).setIcon(R.drawable.trend);
      //  tabLayout.getTabAt(1).setIcon(R.drawable.chatting);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        /*        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
       //             circleReveal(R.id.search_toolbar,1,true,true);
                }else{
                    search_toolbar.setVisibility(View.VISIBLE);
                }
                item_search.expandActionView(); */
            if(flag){
                categoryBottomSheet();

            }else{

                searchAlertDialog();


            }

            }
        });


    }



    private void searchAlertDialog() {

        View dialog_view = getLayoutInflater().inflate(R.layout.search_dialog,null);
        searchBuilder.setView(dialog_view);
        searchDialog = searchBuilder.create();
        searchDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        searchDialog.show();
    }

    private void categoryBottomSheet() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View v = LayoutInflater.from(this).inflate(R.layout.category_bottom_sheet,null);
        done_button = (MaterialButton) v.findViewById(R.id.done_button);
        bottomSheetDialog.setContentView(v);
        bottomSheetDialog.show();

        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController,mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        /*switch(item.getItemId()){

            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        new ProfileActivity()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        new SettingsFragment()).commit();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
*/
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.medify_signout:
                break;
            case R.id.medify_profile:
                startActivity(new Intent(this,ProfileActivity.class));
                break;
            case R.id.more:
                    startActivity(new Intent(this, MoreActivity.class));
                break;
            case R.id.medify_howtouse:
                startActivity(new Intent(this, HowToUse.class));
                break;
        }
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