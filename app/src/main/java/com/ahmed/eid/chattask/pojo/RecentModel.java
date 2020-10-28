package com.ahmed.eid.chattask.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecentModel {

    @SerializedName("Name")
    @Expose
    private String Name;

    @SerializedName("Pic")
    @Expose
    private String Pic;

    @SerializedName("Message")
    @Expose
    private String Message;

    @SerializedName("Time")
    @Expose
    private String Time;

    @SerializedName("New")
    @Expose
    private int New;

    public RecentModel() {
    }

    public RecentModel(String name, String pic, String message, String time, int aNew) {
        Name = name;
        Pic = pic;
        Message = message;
        Time = time;
        New = aNew;
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

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getNew() {
        return New;
    }

    public void setNew(int aNew) {
        New = aNew;
    }
}
