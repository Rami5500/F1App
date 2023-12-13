package model;

import java.io.Serializable;

public class Circuit implements Serializable {

    private String circuitId;
    private String circuitName;
    private Location location;

    public String getCircuitId() {
        return circuitId;
    }

    public void setCircuitId(String circuitId) {
        this.circuitId = circuitId;
    }

    public String getCircuitName() {
        return circuitName;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getCircuitName());
        sb.append(" ");
        sb.append(getLocation().getLocality());
        sb.append(" ");
        sb.append(getLocation().getCountry());
        return sb.toString();
    }
}
