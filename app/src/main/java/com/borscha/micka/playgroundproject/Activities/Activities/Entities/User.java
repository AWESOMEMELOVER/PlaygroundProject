package com.borscha.micka.playgroundproject.Activities.Activities.Entities;

/**
 * Created by micka on 11/22/2017.
 */

public class User {

    private float id;
    private String firstName;
    private String lastName;
    private String MSISDN;
    private boolean isApp;
    private boolean isKiosk;

    public User(float id, String firstName, String lastName, String MSISDN, boolean isApp, boolean isKiosk) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.MSISDN = MSISDN;
        this.isApp = isApp;
        this.isKiosk = isKiosk;
    }



    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMSISDN() {
        return MSISDN;
    }

    public void setMSISDN(String MSISDN) {
        this.MSISDN = MSISDN;
    }

    public boolean isApp() {
        return isApp;
    }

    public void setApp(boolean app) {
        isApp = app;
    }

    public boolean isKiosk() {
        return isKiosk;
    }

    public void setKiosk(boolean kiosk) {
        isKiosk = kiosk;
    }
}
