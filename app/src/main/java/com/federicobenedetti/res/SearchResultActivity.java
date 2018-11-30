package com.federicobenedetti.res;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SearchResultActivity extends AppCompatActivity {

    private final String TAG = "SearchResultActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Intent i = getIntent();
        String qs = i.getStringExtra("QueryString");
        String ss = i.getStringExtra("SpinnerSelection");

        Log.i(TAG, "QueryString " + qs);
        Log.i(TAG, "SpinnerSelection " + ss);

        Toolbar toolbar = findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView t = findViewById(R.id.textview_search_result);
        t.setText("Product for '" + qs + "' in '" + ss + "':");

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
