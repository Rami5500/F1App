package model;

import java.io.Serializable;

public class Location implements Serializable {

    private double lat;

    private double lng;
    private String locality;
    private String country;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public double getLong() {
        return lng;
    }

    public void setLong(double lng) {
        this.lng = lng;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
