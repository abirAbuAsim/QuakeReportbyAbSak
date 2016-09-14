package com.trynlearn.android.quakereportbyabsak;

/**
 * Created by absakDev on 9/12/2016.
 */
public class Earthquake {
    private double magValue;
    private String cityName;
    private long dateValue;

    public Earthquake(double magValue, String cityName, long dateValue){
        this.magValue = magValue;
        this.cityName = cityName;
        this.dateValue = dateValue;
    }

    public String getCityName() {
        return cityName;
    }

    public long getDateValue() {
        return dateValue;
    }

    public double getMagValue() {

        return magValue;
    }
}
