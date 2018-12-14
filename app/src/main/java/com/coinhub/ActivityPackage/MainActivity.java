package com.coinhub.ActivityPackage;
/**
 * all required libraries imported here
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.coinhub.AdapterPackage.NavigationDrawerExpandItemAdapter;
import com.coinhub.AdapterPackage.NavigationDrawerExpandItemHeaderAdapter;
import com.coinhub.AdapterPackage.NavigationDrawerSingleItemAdapter;
import com.coinhub.DataModelPackage.MenuExpandedModel;
import com.coinhub.DataModelPackage.MenuHeadingModel;
import com.coinhub.FragmentPackage.DashboardFragment;
import com.coinhub.R;
import com.coinhub.UtilPackage.StatusBarView;
import com.mindorks.placeholderview.ExpandablePlaceHolderView;
import com.mindorks.placeholderview.PlaceHolderView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    /**
     * field instance of views and variables
     */

    public static ImageButton btnHam;
    ImageButton btnClose;
    public static DrawerLayout drawer;
    Spinner spinner;
    ExpandablePlaceHolderView placeHolderView;
    PlaceHolderView placeHolderView1;
    public static TextView tvTitle;
    public static FragmentManager manager;
    ImageButton btnProfile, btnSettings;
    ImageView profileImage;
    public LinearLayout layoutColorChange, layoutDashboardHeader, layoutAllHeader, layoutDashboard, layoutLogout;
    public StatusBarView statusBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * setting up the content view of this screen
         */
        setContentView(R.layout.activity_main);
        /**
         * type casting the all views
         */
        initialiseViews();


        addMenuExpandedItems(); // calling method for adding expandable items to the navigation drawer

        settingUpSingleItemNavigationDrawer(); // calling method for adding single items to the navigation drawer


        /**
         * demo data for navigation drawer coin price drop down
         */
        String[] demoQtyArray = new String[]{"1 BTC", "2 BTC", "3 BTC", "4 BTC", "5 BTC"};

        /**
         * setting up data to the navigation drawer coin price drop down
         */
        spinner.setAdapter(new ArrayAdapter<String>(MainActivity.this, R.layout.layout_spinner_text, demoQtyArray));

        /**
         * click listener of navigation ham button which will open and close the navigation drawer
         */
        btnHam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * if the drawer is open then the drawer will be closed and else the drawer will be opened
                 */
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        /**
         * setting default layout header configuration which will be shown on dashboard screen appear.
         * first time the dashboard screen will be shown first so this layout configuration is put here.
         * and when user will click on other item to redirect to other screens that time this configuration will be changed on click.
         */
        layoutColorChange.setBackgroundColor(Color.parseColor("#00000000"));
        layoutAllHeader.setVisibility(View.GONE);
        layoutDashboardHeader.setVisibility(View.VISIBLE);
        statusBarView.setBackgroundResource(R.drawable.splash_status_bar_bg);
        btnHam.setImageResource(R.drawable.menu);

        /**
         * setting the default screen to dashboard screen
         */
        manager.beginTransaction().replace(R.id.fragment_container, new DashboardFragment(), "dash").commit();

        /**
         * click listener of profile button
         */

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * starting profile screen with simple animation
                 */
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(MainActivity.this,
                                profileImage,//setting profile image as animation
                                ViewCompat.getTransitionName(profileImage));
                startActivity(intent, options.toBundle());
            }
        });

/**
 * click listener of settings button
 */
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * starting settings screen with simple animation
                 */
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(MainActivity.this,
                                profileImage,//setting profile image as animation
                                ViewCompat.getTransitionName(profileImage));
                startActivity(intent, options.toBundle());
            }
        });

        /**
         * click listener of close button
         */
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * closing the navigation drawer if the navigation drawer is opened
                 */
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
            }
        });


/**
 * click listener of navigation drawer dashboard
 */
        layoutDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * closing the drawer if its opened
                 */
                if (MainActivity.drawer.isDrawerOpen(GravityCompat.START)) {
                    MainActivity.drawer.closeDrawer(GravityCompat.START);
                }
                /**
                 * popping up the all existing fragments in the stack for avoiding overlapping.
                 */
                for (int i = 0; i < MainActivity.manager.getBackStackEntryCount(); i++) {
                    MainActivity.manager.popBackStack(i, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                /**
                 * setting layout configuration for dashboard screen as dashboard screen design is different from base design
                 */
                layoutColorChange.setBackgroundColor(Color.parseColor("#00000000"));
                layoutAllHeader.setVisibility(View.GONE);
                layoutDashboardHeader.setVisibility(View.VISIBLE);
                statusBarView.setBackgroundResource(R.drawable.splash_status_bar_bg);
                MainActivity.btnHam.setImageResource(R.drawable.menu);
                /**
                 * opening dashboard screen
                 */
                MainActivity.manager.beginTransaction().replace(R.id.fragment_container, new DashboardFragment(), "dash").commit();
            }
        });

        /**
         * click listener of navigation drawer logout
         */
        layoutLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * finishing this activity
                 */
                finish();
            }
        });

    }

    /**
     * on back button pressed this method will be called
     */
    @Override
    public void onBackPressed() {
        /**
         * assigning the drawer and then if the drawer is open the drawer will be closed else the transaction will be destroyed
         */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public void initialiseViews() {
        /**
         * type casting  the all views here
         */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        spinner = (Spinner) findViewById(R.id.spinner_coin_qty);
        btnClose = (ImageButton) findViewById(R.id.btn_close);
        btnProfile = (ImageButton) findViewById(R.id.btn_profile);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        btnSettings = (ImageButton) findViewById(R.id.btn_settings);
        layoutDashboard = (LinearLayout) findViewById(R.id.layout_dashboard);

        layoutLogout = (LinearLayout) findViewById(R.id.layout_logout);

        profileImage = (ImageView) findViewById(R.id.image_profile);
        statusBarView = (StatusBarView) findViewById(R.id.status_bar_main);

        layoutAllHeader = (LinearLayout) findViewById(R.id.layout_all_header);
        layoutColorChange = (LinearLayout) findViewById(R.id.layout_color_change);
        layoutDashboardHeader = (LinearLayout) findViewById(R.id.layout_dash_board_header);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        btnHam = (ImageButton) findViewById(R.id.btn_ham);
        placeHolderView = (ExpandablePlaceHolderView) findViewById(R.id.expandable_place_holder_view);
        placeHolderView1 = (PlaceHolderView) findViewById(R.id.placeholder_view1);


        /**
         * assigning fragment manager to static variable for accessing it from other side
         */
        manager = getSupportFragmentManager();


    }

    /**
     * method for adding demo items to expanded menu of navigation drawer
     */
    void addMenuExpandedItems() {
        ArrayList<MenuHeadingModel> menuHeadingModels = new ArrayList<MenuHeadingModel>();
        /**
         * adding heading of expandable menu list
         */
        menuHeadingModels.add(new MenuHeadingModel(getString(R.string.bitcoin)));
        ArrayList<MenuExpandedModel> menuExapndedModels = new ArrayList<MenuExpandedModel>();
        /**
         * adding sub items of expandable list
         */
        menuExapndedModels.add(new MenuExpandedModel(getString(R.string.buy_bitcoin), R.drawable.buy));
        menuExapndedModels.add(new MenuExpandedModel(getString(R.string.sell_bitcoin), R.drawable.sell));
        /**
         * looping into the all items and adding to the place holder view
         */
        for (int i = 0; i < menuHeadingModels.size(); i++) {
            placeHolderView.addView(new NavigationDrawerExpandItemHeaderAdapter(menuHeadingModels.get(i)));
            for (int j = 0; j < menuExapndedModels.size(); j++) {
                placeHolderView.addView(new NavigationDrawerExpandItemAdapter(MainActivity.this, menuExapndedModels.get(j)));
            }
        }
    }

    /**
     * adding single items to the navigation drawer under wallet accounts
     */
    void settingUpSingleItemNavigationDrawer() {
        /**
         * adding demo items to the navigation drawer wallet list
         */
        ArrayList<MenuHeadingModel> menuHeadingModels1 = new ArrayList<MenuHeadingModel>();
        menuHeadingModels1.add(new MenuHeadingModel(getString(R.string.btc_wallet)));
        menuHeadingModels1.add(new MenuHeadingModel(getString(R.string.eth_wallet)));
        menuHeadingModels1.add(new MenuHeadingModel(getString(R.string.lte_wallet)));
        menuHeadingModels1.add(new MenuHeadingModel(getString(R.string.usd_wallet)));
        /**
         * looping into the all items and adding to the place holder view
         */
        for (int i = 0; i < menuHeadingModels1.size(); i++) {
            placeHolderView1.addView(new NavigationDrawerSingleItemAdapter(MainActivity.this, menuHeadingModels1.get(i)));
        }
    }

    /**
     * click listener of tabs
     *
     * @param v
     */
    public void tab_Onclick(View v) {
        /**
         * as xml on click method only works with activity but the dashboard is a fragment so here when user press any tab this pass this
         * click with view to the dashboard fragment's #tab_Onclick(View v) method,
         * and there the respective task according to the click will be completed.
         */
        DashboardFragment fragment = (DashboardFragment) getSupportFragmentManager().findFragmentByTag("dash");
        fragment.tab_Onclick(v);
    }

    /**
     * click listener of chart filters and orders button clicks
     *
     * @param v
     */
    public void btn_onClick(View v) {

        /**
         * as xml on click method only works with activity but the dashboard is a fragment so here when user press any btn this pass this
         * click with view to the dashboard fragment's #btn_onClick(View v) method,
         * and there the respective task according to the click will be completed.
         */
        DashboardFragment fragment = (DashboardFragment) getSupportFragmentManager().findFragmentByTag("dash");
        fragment.btn_onClick(v);
    }
}
