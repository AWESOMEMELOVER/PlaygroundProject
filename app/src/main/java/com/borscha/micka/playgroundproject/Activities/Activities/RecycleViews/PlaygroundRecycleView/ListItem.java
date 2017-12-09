package com.borscha.micka.playgroundproject.Activities.Activities.RecycleViews.PlaygroundRecycleView;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by micka on 12/2/2017.
 */

public class ListItem implements Parcelable{

    private String name;
    private String description;
    private String imageUrl;
    private float locationX;
    private float locationY;
    private String city;
    private String street;
    private String streetNum;


    public ListItem(String name, String description, String imageUrl, float locationX, float locationY, String city, String street, String streetNum) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.locationX = locationX;
        this.locationY = locationY;
        this.city = city;
        this.street = street;
        this.streetNum = streetNum;
    }

    protected ListItem(Parcel in) {
        name = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        locationX = in.readFloat();
        locationY = in.readFloat();
        city = in.readString();
        street = in.readString();
        streetNum = in.readString();
    }

    public static final Creator<ListItem> CREATOR = new Creator<ListItem>() {
        @Override
        public ListItem createFromParcel(Parcel in) {
            return new ListItem(in);
        }

        @Override
        public ListItem[] newArray(int size) {
            return new ListItem[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(imageUrl);
        parcel.writeFloat(locationX);
        parcel.writeFloat(locationY);
        parcel.writeString(city);
        parcel.writeString(street);
        parcel.writeString(streetNum);
    }
}
