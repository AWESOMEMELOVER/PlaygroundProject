package com.borscha.micka.playgroundproject.Activities.Activities.RecycleViews.PlaygroundRecycleView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.borscha.micka.playgroundproject.Activities.Activities.EditBeaconActivity;
import com.borscha.micka.playgroundproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by micka on 12/2/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.beacons_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ListItem listItem = listItems.get(position);

        holder.textViewHead.setText(listItem.getName());
        holder.textViewDesc.setText(listItem.getDescription());
        Picasso.with(context).load(listItem.getImageUrl()).into(holder.imageView);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO set on click start new Intent
                Toast.makeText(context,"You clicked "+listItem.getName(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, EditBeaconActivity.class);
                intent.putExtra("object",listItem);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewHead;
        public TextView textViewDesc;
        public ImageView imageView;
        public RelativeLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewHead = (TextView)  itemView.findViewById(R.id.textViewHead);
            textViewDesc = (TextView)  itemView.findViewById(R.id.textViewDescription);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            linearLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
        }
    }

}
