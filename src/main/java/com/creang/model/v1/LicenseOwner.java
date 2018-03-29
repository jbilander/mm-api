package com.creang.model.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LicenseOwner {

    private String firstName;
    private String lastName;
    private String shortName;
    private boolean amateur;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setAmateur(boolean amateur) {
        this.amateur = amateur;
    }

    @JsonProperty("FirstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("LastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("ShortName")
    public String getShortName() {
        return shortName;
    }

    @JsonProperty("Amateur")
    public boolean isAmateur() {
        return amateur;
    }
}
