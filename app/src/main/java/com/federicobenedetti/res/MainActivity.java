package com.federicobenedetti.res;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements SearchFragment.OnFragmentInteractionListener {
    private User user;

    DrawerLayout mDrawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    Fragment fragment = null;
    Class fragmentClass;

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        this.user = new User(intent.getStringExtra("name"),
                intent.getStringExtra("email"),
                intent.getStringExtra("pURL"));

        navigationView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);

        // Set Image on the Navigation Drawer header
        final View hView = navigationView.getHeaderView(0);
        ImageView imageViewHeader = hView.findViewById(R.id.imageview_header);
        Picasso.get().load(this.user.getPicURL()).into(imageViewHeader);

        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        setDefaultFragment();

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        Log.i(TAG, "MenuItem: " + menuItem.toString());
                        menuItem.setChecked(true);

                        switch (menuItem.toString()) {
                            case "Search":
                                fragmentClass = SearchFragment.class;
                                break;

                            case "Settings":
                                break;

                            case "Logout":
                                break;

                            default:
                                fragmentClass = SearchFragment.class;
                                break;
                        }

                        fragmentClass = SearchFragment.class;

                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // Insert the fragment by replacing any existing fragment
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

                        // Highlight the selected item has been done by NavigationView
                        menuItem.setChecked(true);
                        // Set action bar title
                        setTitle(menuItem.getTitle());

                        mDrawerLayout.closeDrawers();

                        return true;
                    }
                });
    }

    private void setDefaultFragment() {
        fragmentClass = SearchFragment.class;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        navigationView.setCheckedItem(0);

        // Set action bar title
        setTitle(getString(R.string.search));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {


    }
}
