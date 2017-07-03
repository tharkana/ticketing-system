package com.duosoft.duosoftticketingsystem.adaptors;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.duosoft.duosoftticketingsystem.R;
import com.duosoft.duosoftticketingsystem.TicketDetails;
import com.duosoft.duosoftticketingsystem.rest_api.pojo.Comments;
import com.duosoft.duosoftticketingsystem.rest_api.pojo.Ticket;
import com.duosoft.duosoftticketingsystem.rest_api.pojo.UserDetails;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Tharkana on 6/25/2017.
 */
public class CommentListAdaptor extends RecyclerView.Adapter<CommentListAdaptor.CommentViewHolder> {

    private final LayoutInflater inflator;
    private final Context context;
    List<Comments> data;

    public CommentListAdaptor(Context context, List<Comments> data) {
        inflator = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.comment_list_view, parent, false);

        CommentViewHolder myViewHolder = new CommentViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {

        ImageLoader imageLoader = ImageLoader.getInstance();
        Comments comment = data.get(position);
        holder.textViewComment.setText(comment.getBody());
        UserDetails user = comment.getAuthor();
        if(user != null){
            if(user.getAvatar() != "unknown"){
                imageLoader.displayImage(user.getAvatar() , holder.userImage);
            }
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {

        TextView textViewComment;
        ImageView userImage;


        public CommentViewHolder(View itemView) {
            super(itemView);

            textViewComment = (TextView) itemView.findViewById(R.id.textViewComment);
            userImage = (ImageView) itemView.findViewById(R.id.userImage);

        }
    }


}
