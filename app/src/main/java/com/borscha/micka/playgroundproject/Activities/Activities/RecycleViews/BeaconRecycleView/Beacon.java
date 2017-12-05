package com.borscha.micka.playgroundproject.Activities.Activities.RecycleViews.BeaconRecycleView;

/**
 * Created by micka on 05.12.2017.
 */

public class Beacon {

    private Object idBeacon;
    private String friendlyName;
    private String imageUrl;

    public Beacon(Object idBeacon, String friendlyName, String imageUrl) {
        this.idBeacon = idBeacon;
        this.friendlyName = friendlyName;
        this.imageUrl = imageUrl;
    }

    public Object getIdBeacon() {
        return idBeacon;
    }

    public void setIdBeacon(Object idBeacon) {
        this.idBeacon = idBeacon;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
