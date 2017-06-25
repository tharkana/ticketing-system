package com.duosoft.duosoftticketingsystem;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.duosoft.duosoftticketingsystem.adaptors.TicketListAdaptor;
import com.duosoft.duosoftticketingsystem.rest_api.ApiClient;
import com.duosoft.duosoftticketingsystem.rest_api.ApiInterface;
import com.duosoft.duosoftticketingsystem.rest_api.SessionManager;
import com.duosoft.duosoftticketingsystem.rest_api.TicketApiClient;
import com.duosoft.duosoftticketingsystem.rest_api.pojo.TicketListResponse;
import com.duosoft.duosoftticketingsystem.rest_api.pojo.UserAuthResponse;

import java.io.IOException;
import java.util.Collections;
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
    private List<TicketListResponse.Ticket> tickets = Collections.emptyList();

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
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        getTicketList();


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
        Call<TicketListResponse> call = apiInterface.getTicketList();
        call.enqueue(new Callback<TicketListResponse>() {
            @Override
            public void onResponse(Call<TicketListResponse> call, Response<TicketListResponse> response) {
                if(response.isSuccessful()){
                    ticketListResponse = response.body();
                    tickets = ticketListResponse.getResult();
                    Log.d("TK",tickets.toString() );
//                    adapter.notifyDataSetChanged();
                    adapter.updateDataSet(tickets);

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

            }
        });
    }

}
