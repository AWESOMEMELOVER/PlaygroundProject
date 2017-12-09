package com.borscha.micka.playgroundproject.Activities.Activities.RecycleViews.TrackingRecycleView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.borscha.micka.playgroundproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by micka on 06.12.2017.
 */

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolder>{

    private ArrayList<Track> trackList;
    private Context context;

    public TrackAdapter(ArrayList<Track> trackList, Context context) {
        this.trackList = trackList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Track track = trackList.get(position);
        holder.trackNameTextView.setText(track.getName());
        holder.safetyZoneTextView.setText(track.getDescr());
        Picasso.with(context).load(track.getImageURL()).into(holder.trackImageView);
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView trackImageView;
        private TextView trackNameTextView;
        private TextView safetyZoneTextView;
        private ImageView safetyZoneIdentificator;

        public ViewHolder(View itemView) {
            super(itemView);
            trackImageView = (ImageView) itemView.findViewById(R.id.trackImageView);
            trackNameTextView = (TextView) itemView.findViewById(R.id.trackNameTextView);
            safetyZoneTextView = (TextView) itemView.findViewById(R.id.safetyZoneTextView);
            safetyZoneIdentificator = (ImageView) itemView.findViewById(R.id.safetyZoneIdentificator);
        }
    }
}
