package com.ahmed.eid.chattask.ui.main;

import android.content.Intent;
import android.os.Bundle;

import com.ahmed.eid.chattask.R;
import com.ahmed.eid.chattask.pojo.ScreenOneResponse;
import com.ahmed.eid.chattask.ui.screenOne.FavoriteFragment;
import com.ahmed.eid.chattask.ui.screenOne.RecentFragment;
import com.ahmed.eid.chattask.ui.screenOne.ScreenOneViewModel;
import com.ahmed.eid.chattask.ui.screenTwo.ChatMassagesActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private TabLayout mTabs;
    private FloatingActionButton fab;
    private ScreenOneViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUI();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent screenTwoIntent = new Intent(MainActivity.this, ChatMassagesActivity.class);
                startActivity(screenTwoIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getDataAndSendDataToFragments();
    }

    private void initializeUI() {
        mViewPager = findViewById(R.id.view_pager);
        mTabs = findViewById(R.id.tabs);
        fab = findViewById(R.id.fab);
        mViewModel = ViewModelProviders.of(this).get(ScreenOneViewModel.class);
        mPagerAdapter = new PagerAdapter(this, getSupportFragmentManager());
        //mPagerAdapter.addFragments(RecentFragment.newInstance());
        //mPagerAdapter.addFragments(FavoriteFragment.newInstance());
        mViewPager.setAdapter(mPagerAdapter);
        mTabs.setupWithViewPager(mViewPager);

    }



    private void getDataAndSendDataToFragments() {
        mViewModel.getDataScreenOne().observe(this, new Observer<ScreenOneResponse>() {
            @Override
            public void onChanged(ScreenOneResponse mData) {
                handleTabs(mData);
                }
        });
    }
    private void handleTabs(ScreenOneResponse mData) {
        mPagerAdapter.addFragments(RecentFragment.newInstance(mData.getRecentList()));
        mPagerAdapter.addFragments(FavoriteFragment.newInstance(mData.getFavoriteList()));
        mPagerAdapter.notifyDataSetChanged();
    }
}