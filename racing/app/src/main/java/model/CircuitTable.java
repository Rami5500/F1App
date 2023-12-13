package model;

import java.util.ArrayList;

public class CircuitTable {

    private ArrayList<Circuit> circuits;
    private String season;

    public ArrayList<Circuit> getCircuits() {
        return circuits;
    }

    public void setCircuits(ArrayList<Circuit> circuits) {
        this.circuits = circuits;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
