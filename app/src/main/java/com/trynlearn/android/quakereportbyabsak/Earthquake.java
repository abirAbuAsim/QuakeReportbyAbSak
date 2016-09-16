package com.trynlearn.android.quakereportbyabsak;

/**
 * Created by absakDev on 9/12/2016.
 */
public class Earthquake {
    private double magValue;
    private String fullLocation;
    private long dateValue;
    private String urlName;

    public Earthquake(double magValue, String cityName, long dateValue, String urlName){
        this.magValue = magValue;
        this.fullLocation = cityName;
        this.dateValue = dateValue;
        this.urlName = urlName;
    }

    public String getFullLocation() {
        return fullLocation;
    }

    public long getDateValue() {
        return dateValue;
    }

    public double getMagValue() {

        return magValue;
    }

    public String getUrlName(){
        return urlName;
    }

}
