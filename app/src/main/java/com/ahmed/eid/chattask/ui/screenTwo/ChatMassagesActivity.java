package com.ahmed.eid.chattask.ui.screenTwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ahmed.eid.chattask.R;
import com.ahmed.eid.chattask.pojo.ScreenTwoResponse;
import com.ahmed.eid.chattask.ui.InternetConnectivity;
import com.ahmed.eid.chattask.ui.main.MainActivity;
import com.ahmed.eid.chattask.ui.screenOne.ScreenOneViewModel;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatMassagesActivity extends AppCompatActivity {

    private TextView mUserName;
    private CircleImageView mUserImage;
    private ImageView mBackActivity;
    private RecyclerView mMassagesRecycler;
    private ContentLoadingProgressBar mLoadingBar;
    private RelativeLayout mProgressLoadingLayout;
    private LinearLayout mNoInternetConnectionLayout;
    private Button mRetryConnectionBtn;
    private ConstraintLayout mListLayout;

    private MassagesAdapter mAdapter;
    private ScreenTwoViewModel mViewModel;
    private InternetConnectivity mInternetConnectivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_massages);
        initializeUI();
        handleToolBar();

        mRetryConnectionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mInternetConnectivity.isConnected()) {
            toggleShowData();
            getDataAndPopulateUI();
        } else {
            toggleError();
        }
    }

    private void initializeUI() {
        mUserName = findViewById(R.id.toolbar_chat_user_name);
        mUserImage = findViewById(R.id.toolbar_chat_user_image);
        mMassagesRecycler = findViewById(R.id.chat_recyclerView);
        mBackActivity = findViewById(R.id.toolbar_back_arrow_imageView);
        mListLayout = findViewById(R.id.list_layout);

        mLoadingBar = findViewById(R.id.progress_bar);
        mProgressLoadingLayout = findViewById(R.id.progress_layout);
        mProgressLoadingLayout.setVisibility(View.VISIBLE);
        mLoadingBar.show();

        mNoInternetConnectionLayout = findViewById(R.id.noInternet_layout);
        mRetryConnectionBtn = findViewById(R.id.retry_btn);

        mInternetConnectivity = new InternetConnectivity(this);
        mViewModel = ViewModelProviders.of(this).get(ScreenTwoViewModel.class);
        mAdapter = new MassagesAdapter(ChatMassagesActivity.this);
        mMassagesRecycler.setLayoutManager(new LinearLayoutManager(this));

    }

    private void handleToolBar() {
        mBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMainActivity();
            }
        });
    }

    private void backToMainActivity() {
        Intent mainActivityIntent = new Intent(ChatMassagesActivity.this, MainActivity.class);
        startActivity(mainActivityIntent);
    }

    private void getDataAndPopulateUI() {
        mViewModel.getDataScreenTwo().observe(this, new Observer<ScreenTwoResponse>() {
            @Override
            public void onChanged(ScreenTwoResponse mData) {

                mAdapter.setMassages(mData.getMessages());
                mAdapter.setReceiverImageURL(mData.getPic());
                mMassagesRecycler.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();

                mUserName.setText(mData.getName());
                Picasso.get().load(mData.getPic())
                        .placeholder(R.drawable.unknown_user)
                        .error(R.drawable.unknown_user)
                        .into(mUserImage);
            }
        });
    }


    private void toggleShowData() {
        mProgressLoadingLayout.setVisibility(View.GONE);
        mLoadingBar.hide();
        mNoInternetConnectionLayout.setVisibility(View.GONE);
        mListLayout.setVisibility(View.VISIBLE);
    }

    private void toggleError() {
        mProgressLoadingLayout.setVisibility(View.GONE);
        mLoadingBar.hide();
        mNoInternetConnectionLayout.setVisibility(View.VISIBLE);
        mListLayout.setVisibility(View.GONE);
    }
}