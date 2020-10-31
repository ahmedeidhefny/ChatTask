package com.ahmed.eid.chattask.ui.screenOne;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmed.eid.chattask.R;
import com.ahmed.eid.chattask.pojo.FavoriteModel;
import com.ahmed.eid.chattask.pojo.RecentModel;
import com.ahmed.eid.chattask.pojo.ScreenOneResponse;
import com.ahmed.eid.chattask.ui.main.MainActivity;
import com.ahmed.eid.chattask.ui.screenTwo.ChatMassagesActivity;

import java.util.ArrayList;


public class RecentFragment extends Fragment {

    public static final String ARG_RECENT_LIST_KEY = "recent_list";
    private ArrayList<RecentModel> mRecentList = new ArrayList<>();

    private View mRootView;
    private RecyclerView mRecentRecycler;
    private TextView mDisplayNoDataMassage;
    private RecentAdapter mAdapter;

    public RecentFragment() {
    }

    public static RecentFragment newInstance() {
        RecentFragment recentFragment = new RecentFragment();
        return recentFragment;
    }

    public static RecentFragment newInstance(ArrayList<RecentModel> recentList) {
        RecentFragment recentFragment = new RecentFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_RECENT_LIST_KEY, recentList);
        recentFragment.setArguments(args);
        return recentFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRecentList = getArguments().getParcelableArrayList(ARG_RECENT_LIST_KEY);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_recent, container, false);
        initializeUI();
        return mRootView;
    }

    private void initializeUI() {
        mRecentRecycler = mRootView.findViewById(R.id.recent_recyclerView);
        mDisplayNoDataMassage = mRootView.findViewById(R.id.display_no_data_massage);
        mAdapter = new RecentAdapter(getActivity());
        mRecentRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onStart() {
        super.onStart();
        PopulateUI();
    }


    private void PopulateUI() {

        if (mRecentList != null) {
            mRecentRecycler.setVisibility(View.VISIBLE);
            mDisplayNoDataMassage.setVisibility(View.GONE);

            mAdapter.setRecent(mRecentList);
            mRecentRecycler.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

            mAdapter.setOnClickItemListener(new RecentAdapter.OnClickItemListener() {
                @Override
                public void onItemClicked(int position) {
                    Intent SendToScreenTwoIntent = new Intent(getActivity(), ChatMassagesActivity.class);
                    startActivity(SendToScreenTwoIntent);
                }
            });
        } else {
            mRecentRecycler.setVisibility(View.GONE);
            mDisplayNoDataMassage.setVisibility(View.VISIBLE);
        }
    }
}