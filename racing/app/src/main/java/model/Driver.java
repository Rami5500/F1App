package model;

import java.io.Serializable;

public class Driver implements Serializable {
    //{"driverId":"albon","permanentNumber":"23","code":"ALB",
    // "url":"http:\/\/en.wikipedia.org\/wiki\/Alexander_Albon",
    // "givenName":"Alexander",
    // "familyName":"Albon","dateOfBirth":"1996-03-23",
    // "nationality":"Thai"}
    //private DriverTable driverTable;
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


    //public DriverTable getDriverTable() {
      //  return driverTable;
    //}

    //public void setDriverTable(DriverTable driverTable) {
      //  this.driverTable = driverTable;
    //}

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
    /*
    private DriverTable driverTable;
    private String driverID;
    private String code;
    private String givenName;
    private String familyName;
    private String nationality;
     */

}

/*
    "driverId": "alonso",
    "permanentNumber": "14",
    "code": "ALO",
    "url": "http://en.wikipedia.org/wiki/Fernando_Alonso",
    "givenName": "Fernando",
    "familyName": "Alonso",
    "dateOfBirth": "1981-07-29",
    "nationality": "Spanish"

 */