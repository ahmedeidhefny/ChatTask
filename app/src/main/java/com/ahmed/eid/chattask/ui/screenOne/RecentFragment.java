package com.ahmed.eid.chattask.ui.screenOne;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmed.eid.chattask.R;
import com.ahmed.eid.chattask.pojo.ScreenOneResponse;


public class RecentFragment extends Fragment {

    private ScreenOneViewModel mViewModel;
    private View mRootView;
    private RecyclerView mRecentRecycler;
    private RecentAdapter mAdapter;

    public RecentFragment() {
    }

    public static RecentFragment newInstance() {
        RecentFragment fragment = new RecentFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_recent, container, false);
        initializeUI();

        mViewModel = ViewModelProviders.of(getActivity()).get(ScreenOneViewModel.class);
        mAdapter = new RecentAdapter(getActivity());
        mRecentRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        getDataAndPopulateUI();
        return mRootView;
    }

    private void initializeUI() {
        mRecentRecycler = mRootView.findViewById(R.id.recent_recyclerView);
    }

    private void getDataAndPopulateUI() {
        mViewModel.getDataScreenOne().observe(getActivity(), new Observer<ScreenOneResponse>() {
            @Override
            public void onChanged(ScreenOneResponse mData) {
                mAdapter.setRecent(mData.getRecentList());
                mRecentRecycler.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}