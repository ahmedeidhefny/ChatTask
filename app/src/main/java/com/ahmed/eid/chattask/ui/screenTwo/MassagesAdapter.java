package com.ahmed.eid.chattask.ui.screenTwo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.eid.chattask.R;
import com.ahmed.eid.chattask.pojo.MassageModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MassagesAdapter extends RecyclerView.Adapter<MassagesAdapter.MassagesViewHolder> {

    private ArrayList<MassageModel> massagesList;
    private String mReceiverImageURL ;
    private Context mContext;

    public MassagesAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setMassages(ArrayList<MassageModel> massagesList) {
        this.massagesList = massagesList;
    }

    public void setReceiverImageURL(String mReceiverImageURL) {
        this.mReceiverImageURL = mReceiverImageURL;
    }

    @NonNull
    @Override
    public MassagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_massages, parent, false);
        return new MassagesViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MassagesViewHolder holder, int position) {
        MassageModel massage = massagesList.get(position);
        if (massage.getSender() > 0) {
            holder.mReceiverMassage.setVisibility(View.GONE);
            holder.mReceiverImage.setVisibility(View.GONE);
            holder.mSenderMassage.setText(massage.getMessage());
        } else {
            holder.mReceiverMassage.setText(massage.getMessage());
            holder.mSenderMassage.setVisibility(View.GONE);
            Picasso.get().load(mReceiverImageURL)
                    .placeholder(R.drawable.unknown_user)
                    .error(R.drawable.unknown_user)
                    .into(holder.mReceiverImage);
        }

    }

    @Override
    public int getItemCount() {
        return massagesList.size();
    }

    public class MassagesViewHolder extends RecyclerView.ViewHolder {

        TextView mSenderMassage, mReceiverMassage;
        CircleImageView mReceiverImage;

        public MassagesViewHolder(@NonNull View itemView) {
            super(itemView);
            mSenderMassage = itemView.findViewById(R.id.massage_sender_text);
            mReceiverMassage = itemView.findViewById(R.id.massage_receiver_text);
            mReceiverImage = itemView.findViewById(R.id.chat_user_image);
        }
    }
}
