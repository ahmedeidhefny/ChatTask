package com.ahmed.eid.chattask.ui.main;

import android.content.Intent;
import android.os.Bundle;

import com.ahmed.eid.chattask.R;
import com.ahmed.eid.chattask.pojo.ScreenOneResponse;
import com.ahmed.eid.chattask.ui.InternetConnectivity;
import com.ahmed.eid.chattask.ui.screenOne.FavoriteFragment;
import com.ahmed.eid.chattask.ui.screenOne.RecentFragment;
import com.ahmed.eid.chattask.ui.screenOne.ScreenOneViewModel;
import com.ahmed.eid.chattask.ui.screenTwo.ChatMassagesActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private TabLayout mTabs;
    private FloatingActionButton fab;
    private ContentLoadingProgressBar mLoadingBar;
    private RelativeLayout mProgressLoadingLayout;
    private LinearLayout mNoInternetConnectionLayout;
    private Button mRetryConnectionBtn;
    private ScreenOneViewModel mViewModel;
    private InternetConnectivity mInternetConnectivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUI();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SendToScreenTwoIntent = new Intent(MainActivity.this, ChatMassagesActivity.class);
                startActivity(SendToScreenTwoIntent);
            }
        });

        mRetryConnectionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });
    }

    private void initializeUI() {
        mViewPager = findViewById(R.id.view_pager);
        mTabs = findViewById(R.id.tabs);
        fab = findViewById(R.id.fab);

        mLoadingBar = findViewById(R.id.progress_bar);
        mProgressLoadingLayout = findViewById(R.id.progress_layout);
        mProgressLoadingLayout.setVisibility(View.VISIBLE);
        mLoadingBar.show();

        mNoInternetConnectionLayout = findViewById(R.id.noInternet_layout);
        mRetryConnectionBtn = findViewById(R.id.retry_btn);

        mInternetConnectivity = new InternetConnectivity(this);

        mViewModel = ViewModelProviders.of(this).get(ScreenOneViewModel.class);
        mPagerAdapter = new PagerAdapter(this, getSupportFragmentManager());
        //mPagerAdapter.addFragments(RecentFragment.newInstance());
        //mPagerAdapter.addFragments(FavoriteFragment.newInstance());
        mViewPager.setAdapter(mPagerAdapter);
        mTabs.setupWithViewPager(mViewPager);

    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mInternetConnectivity.isConnected()) {
            toggleShowData();
            getDataAndSendDataToFragments();
        } else {
            toggleError();
        }
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
        mPagerAdapter.clearAllOldFragments();
        mPagerAdapter.addFragments(RecentFragment.newInstance(mData.getRecentList()));
        mPagerAdapter.addFragments(FavoriteFragment.newInstance(mData.getFavoriteList()));
        mPagerAdapter.notifyDataSetChanged();
    }


    private void toggleShowData() {
        mProgressLoadingLayout.setVisibility(View.GONE);
        mLoadingBar.hide();
        mNoInternetConnectionLayout.setVisibility(View.GONE);
        mTabs.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);
    }

    private void toggleError() {
        mProgressLoadingLayout.setVisibility(View.GONE);
        mLoadingBar.hide();
        mNoInternetConnectionLayout.setVisibility(View.VISIBLE);
        mTabs.setVisibility(View.GONE);
        mViewPager.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);
    }
}