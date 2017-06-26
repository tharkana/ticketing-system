package com.duosoft.duosoftticketingsystem.adaptors;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duosoft.duosoftticketingsystem.R;
import com.duosoft.duosoftticketingsystem.rest_api.pojo.TicketListResponse;

import java.util.Collections;
import java.util.List;

/**
 * Created by Tharkana on 6/25/2017.
 */
public class TicketListAdaptor extends RecyclerView.Adapter<TicketListAdaptor.TicketViewHolder> {

    private final LayoutInflater inflator;
    private final Context context;
    List<TicketListResponse.Ticket> data = Collections.emptyList();

    public TicketListAdaptor(Context context, List<TicketListResponse.Ticket> data) {
        inflator = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    public void updateDataSet(List<TicketListResponse.Ticket> list) {
        this.data = list;
        notifyDataSetChanged();
    }

    @Override
    public TicketListAdaptor.TicketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.ticket_list_view, parent, false);

        TicketViewHolder myViewHolder = new TicketViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(TicketListAdaptor.TicketViewHolder holder, int position) {
        TicketListResponse.Ticket ticket = data.get(position);
        Log.d("TK", ticket.toString() );
        holder.textViewPriority.setText(ticket.getPriority());
        holder.textViewStatus.setText( ticket.getStatus());
        holder.textViewSubject.setText( ticket.getSubject());
        holder.textViewType.setText( ticket.getType());

        switch (ticket.getPriority()){
            case "urgent": {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    holder.viewSeperator.setBackgroundColor(context.getResources().getColor(R.color.priorityUrgent, null));
                } else{
                    holder.viewSeperator.setBackgroundColor(context.getResources().getColor(R.color.priorityUrgent));
                }
                break;
            }
            case "high": {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    holder.viewSeperator.setBackgroundColor(context.getResources().getColor(R.color.priorityHigh, null));
                } else{
                    holder.viewSeperator.setBackgroundColor(context.getResources().getColor(R.color.priorityHigh));
                }
                break;
            }
            case "normal": {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    holder.viewSeperator.setBackgroundColor(context.getResources().getColor(R.color.priorityNormal, null));
                } else{
                    holder.viewSeperator.setBackgroundColor(context.getResources().getColor(R.color.priorityNormal));
                }
                break;
            }
            default: {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    holder.viewSeperator.setBackgroundColor(context.getResources().getColor(R.color.priorityNormal, null));
                } else{
                    holder.viewSeperator.setBackgroundColor(context.getResources().getColor(R.color.priorityNormal));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class TicketViewHolder extends RecyclerView.ViewHolder {

//        @Bind(R.id.textViewPriority)  TextView textViewPriority;
//        @Bind(R.id.textViewStatus)  TextView textViewStatus;
//        @Bind(R.id.textViewSubject)  TextView textViewSubject;
//        @Bind(R.id.textViewType)  TextView textViewType;

        AppCompatTextView textViewPriority;
        AppCompatTextView textViewStatus;
        AppCompatTextView textViewSubject;
        AppCompatTextView textViewType;
        View viewSeperator;

        public TicketViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(itemView);
            textViewPriority = (AppCompatTextView) itemView.findViewById(R.id.textViewPriority);
            textViewStatus = (AppCompatTextView) itemView.findViewById(R.id.textViewStatus);
            textViewSubject = (AppCompatTextView) itemView.findViewById(R.id.textViewSubject);
            textViewType = (AppCompatTextView) itemView.findViewById(R.id.textViewType);
            viewSeperator = (View) itemView.findViewById(R.id.priorityStrip);
        }
    }


}
