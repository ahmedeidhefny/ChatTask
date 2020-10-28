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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    ArrayList<FavoriteModel> favoriteList;
    Context mContext;

    public FavoriteAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setFavorites(ArrayList<FavoriteModel> favoriteList) {
        this.favoriteList = favoriteList;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new FavoriteViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        FavoriteModel favorite = favoriteList.get(position);

        holder.mUserName.setText(favorite.getName());
        Picasso.get().load(favorite.getPic())
                .placeholder(R.drawable.unknown_user)
                .error(R.drawable.unknown_user)
                .into(holder.mUserImage);

    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView mUserName;
        ImageView mUserImage;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            mUserName = itemView.findViewById(R.id.favorite_user_textView);
            mUserImage = itemView.findViewById(R.id.favorite_user_imageView);
        }
    }
}
