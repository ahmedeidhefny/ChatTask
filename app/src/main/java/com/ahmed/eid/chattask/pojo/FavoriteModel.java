package com.ahmed.eid.chattask.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavoriteModel implements Parcelable {

    @SerializedName("Name")
    @Expose
    private String Name;
    @SerializedName("Pic")
    @Expose
    private String Pic;

    public FavoriteModel() {
    }

    public FavoriteModel(String name, String pic) {
        Name = name;
        Pic = pic;
    }

    protected FavoriteModel(Parcel in) {
        Name = in.readString();
        Pic = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(Pic);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FavoriteModel> CREATOR = new Creator<FavoriteModel>() {
        @Override
        public FavoriteModel createFromParcel(Parcel in) {
            return new FavoriteModel(in);
        }

        @Override
        public FavoriteModel[] newArray(int size) {
            return new FavoriteModel[size];
        }
    };

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPic() {
        return Pic;
    }

    public void setPic(String pic) {
        Pic = pic;
    }


}
