package com.ahmed.eid.chattask.ui.screenOne;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmed.eid.chattask.R;
import com.ahmed.eid.chattask.pojo.ScreenOneResponse;

public class FavoriteFragment extends Fragment {

    private View mRootView;
    private RecyclerView mFavoriteRecycler;
    private FavoriteAdapter adapter;
    private ScreenOneViewModel mViewModel;

    public FavoriteFragment() {
    }

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_favorite, container, false);
        initializeUI();

        mViewModel = ViewModelProviders.of(getActivity()).get(ScreenOneViewModel.class);
        adapter = new FavoriteAdapter(getActivity());
        //int spanCount = getResources().getDimension(R.dimen.list);
        StaggeredGridLayoutManager sglManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mFavoriteRecycler.setLayoutManager(sglManager);

        getDataAndPopulateUI();
        return mRootView;
    }

    private void initializeUI() {
        mFavoriteRecycler = mRootView.findViewById(R.id.favorite_recyclerView);
    }

    private void getDataAndPopulateUI() {
        mViewModel.getDataScreenOne().observe(getActivity(), new Observer<ScreenOneResponse>() {
            @Override
            public void onChanged(ScreenOneResponse mData) {
                adapter.setFavorites(mData.getFavoriteList());
                mFavoriteRecycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }
}