package com.duosoft.duosoftticketingsystem.rest_api.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.duosoft.duosoftticketingsystem.TicketDetails;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tkodagoda on 7/3/17.
 */

public class TicketDetailsResponse  {

    @SerializedName("Result")
    @Expose
    private TicketDetailsObject result = null;

    public TicketDetailsObject getResult() {
        return result;
    }

    public void setResult(TicketDetailsObject result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "TicketDetailsObject{" +
                "result=" + result +
                '}';
    }

}
