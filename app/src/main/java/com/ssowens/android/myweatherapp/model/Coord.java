package com.ssowens.android.myweatherapp.model;

/**
 * Created by Sheila Owens on 2/24/19.
 */
public class Coord {
    private Double lon;
    private Double lat;

    /**
     * @return The lon
     */
    public Double getLon() {
        return lon;
    }

    /**
     * @param lon The lon
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * @return The lat
     */
    public Double getLat() {
        return lat;
    }

    /**
     * @param lat The lat
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }
}
