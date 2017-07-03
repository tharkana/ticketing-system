package com.duosoft.duosoftticketingsystem;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.duosoft.duosoftticketingsystem.adaptors.CommentListAdaptor;
import com.duosoft.duosoftticketingsystem.adaptors.TicketListAdaptor;
import com.duosoft.duosoftticketingsystem.rest_api.ApiInterface;
import com.duosoft.duosoftticketingsystem.rest_api.TicketApiClient;
import com.duosoft.duosoftticketingsystem.rest_api.pojo.Comments;
import com.duosoft.duosoftticketingsystem.rest_api.pojo.Ticket;

import com.duosoft.duosoftticketingsystem.rest_api.pojo.TicketDetailsResponse;
import com.duosoft.duosoftticketingsystem.rest_api.pojo.TicketListResponse;
import com.duosoft.duosoftticketingsystem.rest_api.pojo.UserDetails;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketDetails extends AppCompatActivity {

    @Bind(R.id.textViewId)
    TextView textViewId;

    @Bind(R.id.priorityStrip)
    ImageView priorityStrip;

    @Bind(R.id.textViewType)
    TextView textViewType;

    @Bind(R.id.textViewPriority)
    AppCompatTextView textViewPriority;

    @Bind(R.id.textViewStatus)
    AppCompatTextView textViewStatus;

    @Bind(R.id.requesteerImg)
    ImageView requesteerImg;

    @Bind(R.id.assigneeImg)
    ImageView assigneeImg;

    @Bind(R.id.submitterImg)
    ImageView submitterImg;

    @Bind(R.id.commentList)
    RecyclerView recyclerView;

    @Bind(R.id.app_bar)
    Toolbar toolbar;


    private Ticket ticket;
    private  TicketDetailsResponse ticketDetailsResponse;

    private ApiInterface apiInterface;

    private CommentListAdaptor commentListAdaptor;
    private List<Comments> comments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details);
        ButterKnife.bind(this);

        apiInterface = TicketApiClient.getClient().create(ApiInterface.class);

        initImageLoader(getApplicationContext());

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ticket Details");

        ticket = getIntent().getParcelableExtra("ticket");
        setData(getApplicationContext());

        commentListAdaptor = new CommentListAdaptor( this, comments );
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(commentListAdaptor);

        getTicketData(ticket.getId());

    }

    private void getTicketData(String ticketId) {
        Call<TicketDetailsResponse> call = apiInterface.getTicketDetails(ticketId);
        call.enqueue(new Callback<TicketDetailsResponse>() {
            @Override
            public void onResponse(Call<TicketDetailsResponse> call, Response<TicketDetailsResponse> response) {
                if(response.isSuccessful()){

                    if(response.isSuccessful()){
                        ticketDetailsResponse = response.body();



                        if(ticketDetailsResponse.getResult().getComments() != null){
                            comments.addAll(ticketDetailsResponse.getResult().getComments());
                            commentListAdaptor.notifyDataSetChanged();
                        }
                    }



                }else{
                    try {
                        Log.d("TK", response.errorBody().string() );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<TicketDetailsResponse> call, Throwable t) {

            }
        });
    }


    private void setData(Context context) {

        textViewId.setText(ticket.getSubject());
        textViewType.setText(ticket.getType());
        textViewStatus.setText(ticket.getStatus());
        textViewPriority.setText(ticket.getPriority());

        ImageLoader imageLoader = ImageLoader.getInstance();

        UserDetails requstee = ticket.getRequester();
        UserDetails assignee = ticket.getAssignee();
        UserDetails submitter = ticket.getSubmitter();

        if(requstee != null){
            if(requstee.getAvatar() != "unknown")
                imageLoader.displayImage(requstee.getAvatar() , requesteerImg);
        }

        if(assignee != null){
            if(assignee.getAvatar() != "unknown")
                imageLoader.displayImage(assignee.getAvatar() , assigneeImg);
        }

        if(submitter != null){
            if(submitter.getAvatar() != "unknown")
                imageLoader.displayImage(submitter.getAvatar() , submitterImg);
        }


        setPriorityData();
    }

    private void setPriorityData() {

        Drawable background = priorityStrip.getBackground();

        switch (ticket.getPriority()){
            case "urgent": {

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                    setBackgroundOfPriority(background, getResources().getColor(R.color.priorityUrgent, null));
                } else{

                    setBackgroundOfPriority(background, getResources().getColor(R.color.priorityUrgent));
                }
                break;
            }
            case "high": {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                    setBackgroundOfPriority(background, getResources().getColor(R.color.priorityHigh, null));
                } else{

                    setBackgroundOfPriority(background, getResources().getColor(R.color.priorityHigh));
                }
                break;
            }
            case "normal": {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                    setBackgroundOfPriority(background, getResources().getColor(R.color.priorityNormal, null));
                } else{

                    setBackgroundOfPriority(background, getResources().getColor(R.color.priorityNormal));
                }
                break;
            }

        }
    }

    private void setBackgroundOfPriority(Drawable background, int color ){
        if (background instanceof ShapeDrawable) {
            // cast to 'ShapeDrawable'
            ShapeDrawable shapeDrawable = (ShapeDrawable) background;
            shapeDrawable.getPaint().setColor(color);
        } else if (background instanceof GradientDrawable) {
            // cast to 'GradientDrawable'
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            gradientDrawable.setColor(color);
        } else if (background instanceof ColorDrawable) {
            // alpha value may need to be set again after this call
            ColorDrawable colorDrawable = (ColorDrawable) background;
            colorDrawable.setColor(color);
        }
    }

    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }
}
