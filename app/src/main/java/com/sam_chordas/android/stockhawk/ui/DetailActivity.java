package com.sam_chordas.android.stockhawk.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;
import com.sam_chordas.android.stockhawk.R;

import static com.sam_chordas.android.stockhawk.R.id.viewPager;

public class DetailActivity extends AppCompatActivity {

    private static final int NUMBER_OF_PAGES = 2;
    private ViewPager pager;
    MyFragmentPagerAdapter mPagerAdapter;
    Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        data = getIntent().getBundleExtra(getString(R.string.string_bundle_extra));
        setTitle(data.getString(getString(R.string.key_name)));

        pager = (ViewPager) findViewById(viewPager);
        mPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(mPagerAdapter);
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabsStrip.setViewPager(pager);

    }

//    @Override
//    public void onBackPressed() {
//        finish();
//    }

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private String tabTitles[] = new String[] { "Information", "Graph"};

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUMBER_OF_PAGES;
        }

        @Override
        public Fragment getItem(int position) {

            switch(position){
                case 0:
                    DetailFragment detailFragment = new DetailFragment();
                    detailFragment.setArguments(data);
                    return detailFragment;
                case 1:
                    GraphFragment graphFragment = new GraphFragment();
                    graphFragment.setArguments(data);
                    return graphFragment;
            }
            return null;

        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }
    }

}
