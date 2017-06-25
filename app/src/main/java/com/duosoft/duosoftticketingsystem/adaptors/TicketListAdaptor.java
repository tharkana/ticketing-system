package com.duosoft.duosoftticketingsystem.adaptors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.duosoft.duosoftticketingsystem.R;
import com.duosoft.duosoftticketingsystem.rest_api.pojo.TicketListResponse;

import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;

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
        holder.textViewPriority.setText("Priority: " + ticket.getPriority());
        holder.textViewStatus.setText("Status: " + ticket.getStatus());
        holder.textViewSubject.setText("Subject: " + ticket.getSubject());
        holder.textViewType.setText("Type: " + ticket.getType());
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

        TextView textViewPriority;
        TextView textViewStatus;
        TextView textViewSubject;
        TextView textViewType;

        public TicketViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(itemView);
            textViewPriority = (TextView) itemView.findViewById(R.id.textViewPriority);
            textViewStatus = (TextView) itemView.findViewById(R.id.textViewStatus);
            textViewSubject = (TextView) itemView.findViewById(R.id.textViewSubject);
            textViewType = (TextView) itemView.findViewById(R.id.textViewType);
        }
    }


}
