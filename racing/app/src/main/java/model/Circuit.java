package model;

import java.io.Serializable;

public class Circuit implements Serializable {

    private String circuitId;
    private String circuitName;
    private Location Location;
    private int imageResource;

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

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
        return Location;
    }

    public void setLocation(Location location) {
        this.Location = location;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (getCircuitName() != null) {
            sb.append(getCircuitName());
        } else {
            sb.append("N/A");
        }
        sb.append(" ");
        if (getLocation() != null) {
            if (getLocation().getLocality() != null) {
                sb.append(getLocation().getLocality());
            } else {
                sb.append("N/A");
            }

            sb.append(" ");
            if (getLocation().getCountry() != null) {
                sb.append(getLocation().getCountry());
            } else {
                sb.append("N/A");
            }
            sb.append(" ");
            sb.append("Latitude: ").append(getLocation().getLat());
            sb.append(", Longitude: ").append(getLocation().getLon());
        } else {
            sb.append("N/A N/A");
        }

        return sb.toString();
    }
}
