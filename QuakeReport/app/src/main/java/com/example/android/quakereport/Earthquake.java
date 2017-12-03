package com.example.android.quakereport;


import static com.example.android.quakereport.R.id.date;

/**
 * Created by jakub on 10/12/2017.
 */

public class Earthquake{

    private double magnitude;
    private String location;
    private long timeInMilliseconds;
    private String url;

    public Earthquake(double magnitude, String location, long timeInMilliseconds, String url){
        this.magnitude = magnitude;
        this.location = location;
        this.timeInMilliseconds = timeInMilliseconds;
        this.url = url;
    }

    public double getMagnitude(){
        return magnitude;
    }

    public String getUrl(){
        return url;
    }

    public String getLocation(){
        return location;
    }

    public Long getTimeInMilliseconds() { return timeInMilliseconds; }
}
