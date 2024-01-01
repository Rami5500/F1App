package model;

import java.io.Serializable;

public class Driver implements Serializable {
    private String driverId;
    private Integer permanentNumber;

    public Integer getPermanentNumber() {
        return permanentNumber;
    }

    public void setPermanentNumber(Integer permanentNumber) {
        this.permanentNumber = permanentNumber;
    }

    private String code;
    private String givenName;
    private String familyName;
    private String nationality;

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverID) {
        this.driverId = driverID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getGivenName());
        sb.append(" ");
        sb.append(getFamilyName());
        sb.append(" ");
        sb.append(getNationality());
        return sb.toString();
    }

}