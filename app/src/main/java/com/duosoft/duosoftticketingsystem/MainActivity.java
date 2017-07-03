package com.duosoft.duosoftticketingsystem;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.duosoft.duosoftticketingsystem.adaptors.EndlessRecyclerViewScrollListener;
import com.duosoft.duosoftticketingsystem.adaptors.TicketListAdaptor;
import com.duosoft.duosoftticketingsystem.rest_api.ApiInterface;
import com.duosoft.duosoftticketingsystem.rest_api.SessionManager;
import com.duosoft.duosoftticketingsystem.rest_api.TicketApiClient;
import com.duosoft.duosoftticketingsystem.rest_api.pojo.Ticket;
import com.duosoft.duosoftticketingsystem.rest_api.pojo.TicketListResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.app_bar) Toolbar toolbar;

    private SessionManager session;
    private ApiInterface apiInterface;

    @Bind(R.id.ticketList) RecyclerView recyclerView;

    private TicketListAdaptor adapter;
    private  TicketListResponse ticketListResponse;
    private List<Ticket> tickets = new ArrayList<>();
    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = new SessionManager(getApplicationContext());
        if( !session.checkLogin() ){
            finish();
        }


        apiInterface = TicketApiClient.getClient().create(ApiInterface.class);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ticket List");

        adapter = new TicketListAdaptor( this, tickets );
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        getTicketList();

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                loadNextDataFromApi(page);
            }
        };

    }

    private void loadNextDataFromApi(int page) {
        Call<TicketListResponse> call = apiInterface.getTicketList();
        call.enqueue(new Callback<TicketListResponse>() {
            @Override
            public void onResponse(Call<TicketListResponse> call, Response<TicketListResponse> response) {
                if(response.isSuccessful()){
                    ticketListResponse = response.body();
                    tickets.addAll(ticketListResponse.getResult());
                    adapter.notifyDataSetChanged();

                }else{
                    try {
                        Log.d("TK", response.errorBody().string() );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<TicketListResponse> call, Throwable t) {
                Log.d("TK", t.toString() );
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.logout) {

            session.logoutUser();

        }

        return super.onOptionsItemSelected(item);
    }

    private void getTicketList() {
        Call<TicketListResponse> call = apiInterface.getTicketList(1);
        call.enqueue(new Callback<TicketListResponse>() {
            @Override
            public void onResponse(Call<TicketListResponse> call, Response<TicketListResponse> response) {
                if(response.isSuccessful()){
                    ticketListResponse = response.body();
                    tickets.addAll(ticketListResponse.getResult());
                    adapter.notifyDataSetChanged();
//                    adapter.notifyItemRangeChanged();

                }else{
                    try {
                        Log.d("TK", response.errorBody().string() );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<TicketListResponse> call, Throwable t) {
                Log.d("TK", t.toString() );
            }
        });
    }

}
