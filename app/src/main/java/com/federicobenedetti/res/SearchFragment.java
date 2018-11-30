package com.federicobenedetti.res;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;


public class SearchFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private final String TAG = " SearchFragment";
    private OnFragmentInteractionListener mListener;
    private String spinnerResult;

    public SearchFragment() {
    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        try {
            MainActivity.requestBuilderWithBearerToken(MainActivity.getUserToken(), "http://90.147.188.51/");
        } catch (Exception e) {
            e.printStackTrace();
        }

        final Spinner spinner = v.findViewById(R.id.search_spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(),
                R.array.places, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        final SearchView sv = v.findViewById(R.id.searchview_fragment);

        Button b = v.findViewById(R.id.search_button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sv_text = sv.getQuery().toString();

                spinnerResult = spinner.getSelectedItem().toString();
                System.out.println("Spinner " + spinnerResult);

                Intent intent = new Intent(getContext(), SearchResultActivity.class);

                intent.putExtra("QueryString", sv_text);
                intent.putExtra("SpinnerSelection", spinnerResult);

                Log.i(TAG, "SearchView text: " + sv_text);
                Log.i(TAG, "SpinnerSelection: " + spinnerResult);

                startActivity(intent);
            }
        });

        return v;

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = parent.findViewById(R.id.search_spinner);
        if (spinner != null) {
            spinnerResult =  spinner.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        return;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
