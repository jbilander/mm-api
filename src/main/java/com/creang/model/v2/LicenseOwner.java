package com.creang.model.v2;

public class LicenseOwner {

    private String firstName;
    private String lastName;
    private String shortName;
    private boolean amateur;
    private boolean apprenticeAmateur;
    private boolean apprenticePro;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public boolean isAmateur() {
        return amateur;
    }

    public void setAmateur(boolean amateur) {
        this.amateur = amateur;
    }

    public boolean isApprenticeAmateur() {
        return apprenticeAmateur;
    }

    public void setApprenticeAmateur(boolean apprenticeAmateur) {
        this.apprenticeAmateur = apprenticeAmateur;
    }

    public boolean isApprenticePro() {
        return apprenticePro;
    }

    public void setApprenticePro(boolean apprenticePro) {
        this.apprenticePro = apprenticePro;
    }
}
