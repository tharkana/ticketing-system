package com.duosoft.duosoftticketingsystem.rest_api;

import com.duosoft.duosoftticketingsystem.rest_api.pojo.TicketListResponse;
import com.duosoft.duosoftticketingsystem.rest_api.pojo.UserAuth;
import com.duosoft.duosoftticketingsystem.rest_api.pojo.UserAuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Tharkana on 6/25/2017.
 */
public interface ApiInterface {


    @POST("/auth/login")
    Call<UserAuthResponse> authenticate(@Body UserAuth user);

    @GET("MyTickets/10/1?status=new")
    Call<TicketListResponse> getTicketList();
}
