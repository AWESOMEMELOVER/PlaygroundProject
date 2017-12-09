package com.borscha.micka.playgroundproject.Activities.Activities.RecycleViews.TrackingRecycleView;

/**
 * Created by micka on 05.12.2017.
 */

public class Track {

    private String name;
    private String descr;
    private String imageURL;
    private float locationX;
    private float locationY;
    private String city;
    private String street;
    private String streetNum;

    public Track(String name, String descr, String imageURL) {
        this.name = name;
        this.descr = descr;
        this.imageURL = imageURL;
    }

    public Track(String name) {
        this.name = name;
    }

    public Track(String name, String descr, String imageURL, float locationX, float locationY, String city, String street, String streetNum) {
        this.name = name;
        this.descr = descr;
        this.imageURL = imageURL;
        this.locationX = locationX;
        this.locationY = locationY;
        this.city = city;
        this.street = street;
        this.streetNum = streetNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public float getLocationX() {
        return locationX;
    }

    public void setLocationX(float locationX) {
        this.locationX = locationX;
    }

    public float getLocationY() {
        return locationY;
    }

    public void setLocationY(float locationY) {
        this.locationY = locationY;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }
}
