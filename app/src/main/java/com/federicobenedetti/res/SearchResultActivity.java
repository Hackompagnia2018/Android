package com.federicobenedetti.res;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

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

        ArrayList<Product> productList = new ArrayList<Product>();
        productList.add(new Product("Vendesi Olio Esausto", "Vendesi olio esausto, che è stato utilizzato per motori", "Serra San Quirico", "Via Della Collina, 1", "+3931132532569", false, 100, 0.30));
        productList.add(new Product("Vendo letame in eccesso", "Vendo letame non utilizzato", "Montelabbate", "Corso Carlo Bello, 92", "+39387458745", true, 200, 0.50));
        productList.add(new Product("Vendesi materiale agricolo", "Materiale agricolo di scarto di vario tipo", "Pesaro", "Via delle lanterne, 33", "+393552562565", true, 50, 1.00));

        CardView c1 = findViewById(R.id.cw_1);
        CardView c2 = findViewById(R.id.cw_2);
        CardView c3 = findViewById(R.id.cw_3);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
