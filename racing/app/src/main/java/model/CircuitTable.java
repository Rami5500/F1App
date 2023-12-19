package model;

import java.util.ArrayList;

public class CircuitTable {

    private ArrayList<Circuit> Circuits;
    private String season;

    public ArrayList<Circuit> getCircuits() {
        return Circuits;
    }

    public void setCircuits(ArrayList<Circuit> circuits) {
        this.Circuits = circuits;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
