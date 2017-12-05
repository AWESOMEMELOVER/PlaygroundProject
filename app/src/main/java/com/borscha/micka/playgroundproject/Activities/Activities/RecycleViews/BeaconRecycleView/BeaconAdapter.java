package com.borscha.micka.playgroundproject.Activities.Activities.RecycleViews.BeaconRecycleView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.borscha.micka.playgroundproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by micka on 05.12.2017.
 */

public class BeaconAdapter extends RecyclerView.Adapter<BeaconAdapter.ViewHolder> {
    private List<Beacon> beaconList;
    private Context context;

    public BeaconAdapter(List<Beacon> beaconList, Context context) {
        this.beaconList = beaconList;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beacons_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Beacon beacon = beaconList.get(position);
        holder.beaconFriendlyName.setText(beacon.getFriendlyName());
        //Picasso.with(context).setLoggingEnabled(true);
        Picasso.with(context).load("http://placehold.it/120x120&text=image1")
                .into(holder.beaconImageView);
    }



    @Override
    public int getItemCount() {
        return beaconList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView beaconFriendlyName;
        public ImageView beaconImageView;


        public ViewHolder(View itemView) {
            super(itemView);
            beaconFriendlyName = (TextView) itemView.findViewById(R.id.beaconFriendlyName);
            beaconImageView = (ImageView) itemView.findViewById(R.id.beaconImageView);



        }
    }

}


