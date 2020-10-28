package com.ahmed.eid.chattask.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ScreenTwoResponse {

    @SerializedName("Name")
    @Expose
    private String Name;

    @SerializedName("Pic")
    @Expose
    private String Pic;

    @SerializedName("Messages")
    @Expose
    private ArrayList<MassageModel> Messages;

    public ScreenTwoResponse() {
    }

    public ScreenTwoResponse(String name, String pic, ArrayList<MassageModel> messages) {
        Name = name;
        Pic = pic;
        Messages = messages;
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

    public ArrayList<MassageModel> getMessages() {
        return Messages;
    }

    public void setMessages(ArrayList<MassageModel> messages) {
        Messages = messages;
    }
}
