package com.ahmed.eid.chattask.ui.screenTwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmed.eid.chattask.R;
import com.ahmed.eid.chattask.pojo.ScreenTwoResponse;
import com.ahmed.eid.chattask.ui.main.MainActivity;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatMassagesActivity extends AppCompatActivity {

    private TextView mUserName;
    private CircleImageView mUserImage;
    private ImageView mBackActivity;
    private RecyclerView mMassagesRecycler;
    private MassagesAdapter mAdapter;
    private ScreenTwoViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_massages);
        initializeUI();
        handleToolBar();

        mViewModel = ViewModelProviders.of(this).get(ScreenTwoViewModel.class);
        mAdapter = new MassagesAdapter(ChatMassagesActivity.this);
        mMassagesRecycler.setLayoutManager(new LinearLayoutManager(this));

        getDataAndPopulateUI();

    }

    private void initializeUI() {
        mUserName = findViewById(R.id.toolbar_chat_user_name);
        mUserImage = findViewById(R.id.toolbar_chat_user_image);
        mMassagesRecycler = findViewById(R.id.chat_recyclerView);
        mBackActivity = findViewById(R.id.toolbar_back_arrow_imageView);
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
}