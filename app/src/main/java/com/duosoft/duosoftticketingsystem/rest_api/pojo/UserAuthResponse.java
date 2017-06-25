package com.duosoft.duosoftticketingsystem.rest_api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tharkana on 6/25/2017.
 */
public class UserAuthResponse {

    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("token")
    @Expose
    private String token;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserAuthResponse{" +
                "state='" + state + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
