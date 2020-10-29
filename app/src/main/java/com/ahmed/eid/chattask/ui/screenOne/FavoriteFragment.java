package com.ahmed.eid.chattask.ui.screenOne;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmed.eid.chattask.R;
import com.ahmed.eid.chattask.pojo.FavoriteModel;
import com.ahmed.eid.chattask.pojo.ScreenOneResponse;
import com.ahmed.eid.chattask.ui.main.MainActivity;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    public static final String ARG_FAVORITES_LIST_KEY = "favorite_list";
    private ArrayList<FavoriteModel> mFavoriteList = new ArrayList<>();

    private View mRootView;
    private RecyclerView mFavoriteRecycler;
    private FavoriteAdapter adapter;
    private StaggeredGridLayoutManager sglManager;

    public FavoriteFragment() {
    }

    public static FavoriteFragment newInstance() {
        FavoriteFragment favoriteFragment = new FavoriteFragment();
        return favoriteFragment;
    }

    public static FavoriteFragment newInstance(ArrayList<FavoriteModel> favoritesList) {

        FavoriteFragment favoriteFragment = new FavoriteFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_FAVORITES_LIST_KEY, favoritesList);
        favoriteFragment.setArguments(args);
        return favoriteFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFavoriteList = getArguments().getParcelableArrayList(ARG_FAVORITES_LIST_KEY);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_favorite, container, false);
        initializeUI();

        return mRootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        PopulateUI();
    }

    private void initializeUI() {
        mFavoriteRecycler = mRootView.findViewById(R.id.favorite_recyclerView);
        adapter = new FavoriteAdapter(getActivity());
        sglManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    private void PopulateUI() {

        if (mFavoriteList != null) {
            mFavoriteRecycler.setLayoutManager(sglManager);
            adapter.setFavorites(mFavoriteList);
            mFavoriteRecycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else {
            Toast.makeText(getActivity(), "fav is null", Toast.LENGTH_SHORT).show();
        }
    }
}