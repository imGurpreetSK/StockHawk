package com.sam_chordas.android.stockhawk.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sam_chordas.android.stockhawk.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle data = getIntent().getBundleExtra(getString(R.string.string_bundle_extra));

        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(data);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detail_fragment_container, detailFragment)
                .commit();

    }
}
