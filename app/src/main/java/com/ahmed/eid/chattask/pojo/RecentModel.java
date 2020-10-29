package com.ahmed.eid.chattask.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecentModel implements Parcelable {

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

    protected RecentModel(Parcel in) {
        Name = in.readString();
        Pic = in.readString();
        Message = in.readString();
        Time = in.readString();
        New = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(Pic);
        dest.writeString(Message);
        dest.writeString(Time);
        dest.writeInt(New);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RecentModel> CREATOR = new Creator<RecentModel>() {
        @Override
        public RecentModel createFromParcel(Parcel in) {
            return new RecentModel(in);
        }

        @Override
        public RecentModel[] newArray(int size) {
            return new RecentModel[size];
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
