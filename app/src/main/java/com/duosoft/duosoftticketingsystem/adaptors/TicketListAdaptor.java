package com.duosoft.duosoftticketingsystem.adaptors;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.duosoft.duosoftticketingsystem.R;
import com.duosoft.duosoftticketingsystem.rest_api.pojo.TicketListResponse;

import java.util.List;

/**
 * Created by Tharkana on 6/25/2017.
 */
public class TicketListAdaptor extends RecyclerView.Adapter<TicketListAdaptor.TicketViewHolder> {

    private final LayoutInflater inflator;
    private final Context context;
    List<TicketListResponse.Ticket> data;

    public TicketListAdaptor(Context context, List<TicketListResponse.Ticket> data) {
        inflator = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @Override
    public TicketListAdaptor.TicketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.ticket_list_view, parent, false);

        TicketViewHolder myViewHolder = new TicketViewHolder(view);
        return myViewHolder;

    }

    private String makeSentanceCase(String sentance){
        return sentance.substring(0,1).toUpperCase() + sentance.substring(1);
    }

    @Override
    public void onBindViewHolder( TicketListAdaptor.TicketViewHolder holder, int position) {
        TicketListResponse.Ticket ticket = data.get(position);

//        holder.textViewPriority.setText(ticket.getPriority().substring(0,1).toUpperCase() + ticket.getPriority().substring(1) );
//        holder.textViewStatus.setText( ticket.getStatus().substring(0,1).toUpperCase() + ticket.getStatus().substring(1) );
//        holder.textViewType.setText( ticket.getType().substring(0,1).toUpperCase() + ticket.getType().substring(1) );
        holder.textViewPriority.setText( makeSentanceCase( ticket.getPriority() )  );
        holder.textViewStatus.setText( makeSentanceCase(  ticket.getStatus() ) );
        holder.textViewType.setText( makeSentanceCase(  ticket.getType() ) );
        holder.textViewSubject.setText( ticket.getSubject());

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

        AppCompatTextView textViewPriority;
        AppCompatTextView textViewStatus;
        AppCompatTextView textViewSubject;
        AppCompatTextView textViewType;
        View viewSeperator;
        RelativeLayout relativeLayoutListView;

        public TicketViewHolder(View itemView) {
            super(itemView);
            textViewPriority = (AppCompatTextView) itemView.findViewById(R.id.textViewPriority);
            textViewStatus = (AppCompatTextView) itemView.findViewById(R.id.textViewStatus);
            textViewSubject = (AppCompatTextView) itemView.findViewById(R.id.textViewSubject);
            textViewType = (AppCompatTextView) itemView.findViewById(R.id.textViewType);
            viewSeperator = (View) itemView.findViewById(R.id.priorityStrip);
            relativeLayoutListView = (RelativeLayout) itemView.findViewById(R.id.relativeLayoutListView);
        }
    }


}
