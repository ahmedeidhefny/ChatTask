package com.ahmed.eid.chattask.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MassageModel {

    @SerializedName("Message")
    @Expose
    String Message;

    @SerializedName("Sender")
    @Expose
    int Sender ;

    public MassageModel() {
    }

    public MassageModel(String message, int sender) {
        Message = message;
        Sender = sender;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getSender() {
        return Sender;
    }

    public void setSender(int sender) {
        Sender = sender;
    }
}
