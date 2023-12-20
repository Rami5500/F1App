package model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Location implements Serializable {

    private double lat;
    @SerializedName("long")
    private double lon;
    private String locality;
    private String country;

    /*
    private double getPositiveValue(double v){
        return v < 0 ? -v : v;
    }

     */

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
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

//    @Override
//    public String toString() {
//        Log.i("Location", "Locality: " + locality + ", Country: " + country + ", Latitude: " + lat + ", Longitude: " + lng);
//        return locality + ", " + country;
//    }

}
