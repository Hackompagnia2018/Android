package com.federicobenedetti.res;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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

    }
}
