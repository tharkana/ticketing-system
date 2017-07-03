package com.duosoft.duosoftticketingsystem.rest_api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tharkana on 6/25/2017.
 */


/**
 * {
 * "Result": [{
 * "_id": "5943ad20dd9a640001638c80",
 * "type": "Inquery",
 * "subject": "Account balance",
 * "description": "Customer called to find out account balance",
 * "status": "new",
 * "priority": "normal"
 * }]
 * }
 */

public class TicketListResponse {

    @SerializedName("Result")
    @Expose
    private List<Ticket> result = null;

    public List<Ticket> getResult() {
        return result;
    }

    public void setResult(List<Ticket> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "TicketListResponse{" +
                "result=" + result +
                '}';
    }

}
