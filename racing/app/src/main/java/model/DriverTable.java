package model;

import java.util.ArrayList;

public class DriverTable {
    private ArrayList<Driver> Drivers;
    private String season;

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public ArrayList<Driver> getDrivers() {
        return Drivers;
    }

    public void setDrivers(ArrayList<Driver> drivers) {
        this.Drivers = drivers;
    }
}
