package com.federicobenedetti.res;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    static User user;
    private final String TAG = "MainActivity";
    DrawerLayout mDrawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    public static String getUserPURL() {
        return user.getPicURL();
    }

    public static String getUserName() {
        return user.getName();
    }

    public static String getUserToken() {
        return user.getToken();
    }

    public static Response requestBuilderWithBearerToken(String userToken, String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("cache-control", "no-cache")
                .addHeader("Authorization", "Bearer " + userToken)
                .build();

        return client.newCall(request).execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        user = new User(intent.getStringExtra("name"),
                intent.getStringExtra("email"),
                intent.getStringExtra("pURL"),
                intent.getStringExtra("token"));


        navigationView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);

        // Set Image and Text on the Navigation Drawer header
        final View hView = navigationView.getHeaderView(0);
        ImageView imageViewHeader = hView.findViewById(R.id.imageview_header);
        Picasso.get().load(this.user.getPicURL()).into(imageViewHeader);

        TextView textViewHeader = hView.findViewById(R.id.textview_header);
        textViewHeader.setText(this.user.getName());

        TextView textViewHeaderEmail = hView.findViewById(R.id.textview_header_email);
        textViewHeaderEmail.setText(this.user.getEmail());

        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        setFragment(new SearchFragment(), "Search");

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        Log.i(TAG, "MenuItem: " + menuItem.toString());
                        menuItem.setChecked(true);

                        switch (menuItem.toString()) {
                            case "Search":
                                setFragment(new SearchFragment(), "Search");
                                break;

                            case "New Sale":
                                setFragment(new NewSaleFragment(), "New Sale");
                                break;

                            case "Account":
                                setFragment(new AccountFragment(), "Account");
                                break;

                            case "Settings":
                                break;

                            case "Help":
                                Intent i = new Intent(Intent.ACTION_SEND);
                                i.setType("message/rfc822");
                                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"example@example.com"});
                                i.putExtra(Intent.EXTRA_SUBJECT, "Customer Support");
                                i.putExtra(Intent.EXTRA_TEXT   , "Write here your problem");
                                try {
                                    startActivity(Intent.createChooser(i, "Send mail..."));
                                } catch (android.content.ActivityNotFoundException ex) {
                                    Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case "Logout":
                                break;

                            default:
                                setFragment(new SearchFragment(), "Search");
                                break;
                        }
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void setFragment(Fragment fragment, String title) {
        Class fragmentClass = fragment.getClass();

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
        setTitle(title);
    }
}
