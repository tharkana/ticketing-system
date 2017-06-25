package com.duosoft.duosoftticketingsystem.rest_api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tharkana on 6/25/2017.
 */
public class UserAuth {
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("scope")
    @Expose
    private List<String> scope = null;
    @SerializedName("console")
    @Expose
    private String console;
    @SerializedName("clientID")
    @Expose
    private String clientID;

    public UserAuth(String userName, String password, List<String> scope, String console, String clientID) {
        this.userName = userName;
        this.password = password;
        this.scope = scope;
        this.console = console;
        this.clientID = clientID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getScope() {
        return scope;
    }

    public void setScope(List<String> scope) {
        this.scope = scope;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }
}
