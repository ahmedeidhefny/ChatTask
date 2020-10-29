package com.ahmed.eid.chattask.ui.screenOne;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.eid.chattask.R;
import com.ahmed.eid.chattask.pojo.FavoriteModel;
import com.ahmed.eid.chattask.pojo.RecentModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.RecentViewHolder> {

    private ArrayList<RecentModel> recentList;
    private Context mContext;
    private OnClickItemListener mOnClickItemListener;

    public RecentAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setRecent(ArrayList<RecentModel> recentList) {
        this.recentList = recentList;
    }

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.mOnClickItemListener = onClickItemListener;
    }

    @NonNull
    @Override
    public RecentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recent, parent, false);
        return new RecentViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentViewHolder holder, int position) {
        RecentModel recent = recentList.get(position);

        holder.mUserName.setText(recent.getName());
        holder.mRecentMassage.setText(recent.getMessage());
        holder.mMassageDate.setText(recent.getTime());
        Picasso.get().load(recent.getPic())
                .placeholder(R.drawable.unknown_user)
                .error(R.drawable.unknown_user)
                .into(holder.mUserImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickItemListener.onItemClicked(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recentList.size();
    }

    public class RecentViewHolder extends RecyclerView.ViewHolder {
        private TextView mUserName, mRecentMassage, mMassageDate;
        private CircleImageView mUserImage;
        private View itemView;

        public RecentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            mUserName = itemView.findViewById(R.id.recent_userName_textView);
            mUserImage = itemView.findViewById(R.id.recent_user_imageView);
            mRecentMassage = itemView.findViewById(R.id.recent_massage_textView);
            mMassageDate = itemView.findViewById(R.id.massage_date_textView);
        }
    }

    public interface OnClickItemListener {
        void onItemClicked(int position);
    }
}
