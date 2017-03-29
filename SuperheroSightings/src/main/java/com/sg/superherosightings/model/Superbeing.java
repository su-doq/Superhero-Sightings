/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.util.Objects;

/**
 *
 * @author husst
 */
public class Superbeing {

    private int superbeingID;
    private String superbeingAlias;
    private String superbeingFirstName;
    private String superbeingLastName;
    private String superbeingDescription;

    public int getSuperbeingID() {
        return superbeingID;
    }

    public void setSuperbeingID(int superbeingID) {
        this.superbeingID = superbeingID;
    }

    public String getSuperbeingAlias() {
        return superbeingAlias;
    }

    public void setSuperbeingAlias(String superbeingAlias) {
        this.superbeingAlias = superbeingAlias;
    }

    public String getSuperbeingFirstName() {
        return superbeingFirstName;
    }

    public void setSuperbeingFirstName(String superbeingFirstName) {
        this.superbeingFirstName = superbeingFirstName;
    }

    public String getSuperbeingLastName() {
        return superbeingLastName;
    }

    public void setSuperbeingLastName(String superbeingLastName) {
        this.superbeingLastName = superbeingLastName;
    }

    public String getSuperbeingDescription() {
        return superbeingDescription;
    }

    public void setSuperbeingDescription(String superbeingDescription) {
        this.superbeingDescription = superbeingDescription;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.superbeingID;
        hash = 67 * hash + Objects.hashCode(this.superbeingAlias);
        hash = 67 * hash + Objects.hashCode(this.superbeingFirstName);
        hash = 67 * hash + Objects.hashCode(this.superbeingLastName);
        hash = 67 * hash + Objects.hashCode(this.superbeingDescription);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Superbeing other = (Superbeing) obj;
        if (this.superbeingID != other.superbeingID) {
            return false;
        }
        if (!Objects.equals(this.superbeingAlias, other.superbeingAlias)) {
            return false;
        }
        if (!Objects.equals(this.superbeingFirstName, other.superbeingFirstName)) {
            return false;
        }
        if (!Objects.equals(this.superbeingLastName, other.superbeingLastName)) {
            return false;
        }
        if (!Objects.equals(this.superbeingDescription, other.superbeingDescription)) {
            return false;
        }
        return true;
    }

}
