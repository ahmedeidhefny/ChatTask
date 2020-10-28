package com.ahmed.eid.chattask.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavoriteModel {

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
