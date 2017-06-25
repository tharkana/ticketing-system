package com.duosoft.duosoftticketingsystem.rest_api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tharkana on 6/25/2017.
 */
public class ApiClient {


    private static Retrofit retrofit = null;
    private static String BASE_URL = "http://userservice.app.veery.cloud";

    public static Retrofit getClient() {

//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return retrofit;
    }

}
