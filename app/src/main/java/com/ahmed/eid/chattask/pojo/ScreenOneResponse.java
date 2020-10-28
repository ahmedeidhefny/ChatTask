package com.ahmed.eid.chattask.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ScreenOneResponse {

    @SerializedName("Recent")
    @Expose
    private ArrayList<RecentModel> Recent;

    @SerializedName("Favorites")
    @Expose
    private ArrayList<FavoriteModel> Favorites;

    public ScreenOneResponse() {
    }

    public ScreenOneResponse(ArrayList<RecentModel> recentList, ArrayList<FavoriteModel> favoriteList) {
        this.Recent = recentList;
        this.Favorites = favoriteList;
    }

    public ArrayList<RecentModel> getRecentList() {
        return Recent;
    }

    public void setRecentList(ArrayList<RecentModel> recentList) {
        this.Recent = recentList;
    }

    public ArrayList<FavoriteModel> getFavoriteList() {
        return Favorites;
    }

    public void setFavoriteList(ArrayList<FavoriteModel> favoriteList) {
        this.Favorites = favoriteList;
    }
}
