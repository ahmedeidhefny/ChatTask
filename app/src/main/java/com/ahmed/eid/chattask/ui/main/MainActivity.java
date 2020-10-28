package com.ahmed.eid.chattask.ui.main;

import android.content.Intent;
import android.os.Bundle;

import com.ahmed.eid.chattask.R;
import com.ahmed.eid.chattask.ui.screenOne.FavoriteFragment;
import com.ahmed.eid.chattask.ui.screenOne.RecentFragment;
import com.ahmed.eid.chattask.ui.screenTwo.ChatMassagesActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

public class MainActivity extends AppCompatActivity {
    ViewPager mViewPager;
    TabLayout mTabs;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.view_pager);
        mTabs = findViewById(R.id.tabs);
        fab = findViewById(R.id.fab);

        PagerAdapter mPagerAdapter = new PagerAdapter(this, getSupportFragmentManager());
        mPagerAdapter.addFragments(RecentFragment.newInstance());
        mPagerAdapter.addFragments(FavoriteFragment.newInstance());
        mViewPager.setAdapter(mPagerAdapter);
        mTabs.setupWithViewPager(mViewPager);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent screenTwoIntent = new Intent(MainActivity.this, ChatMassagesActivity.class);
                startActivity(screenTwoIntent);
            }
        });
    }
}