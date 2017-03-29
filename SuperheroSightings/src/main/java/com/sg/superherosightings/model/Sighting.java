/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author husst
 */
public class Sighting {

    private int SightingID;
    private LocalDate sightingDate;
    private String sightingDescription;
    private int superbeingID;
    private int locationID;

    public int getSightingID() {
        return SightingID;
    }

    public void setSightingID(int SightingID) {
        this.SightingID = SightingID;
    }

    public LocalDate getSightingDate() {
        return sightingDate;
    }

    public void setSightingDate(LocalDate sightingDate) {
        this.sightingDate = sightingDate;
    }

    public String getSightingDescription() {
        return sightingDescription;
    }

    public void setSightingDescription(String sightingDescription) {
        this.sightingDescription = sightingDescription;
    }

    public int getSuperbeingID() {
        return superbeingID;
    }

    public void setSuperbeingID(int superbeingID) {
        this.superbeingID = superbeingID;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.SightingID;
        hash = 43 * hash + Objects.hashCode(this.sightingDate);
        hash = 43 * hash + Objects.hashCode(this.sightingDescription);
        hash = 43 * hash + this.superbeingID;
        hash = 43 * hash + this.locationID;
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
        final Sighting other = (Sighting) obj;
        if (this.SightingID != other.SightingID) {
            return false;
        }
        if (this.superbeingID != other.superbeingID) {
            return false;
        }
        if (this.locationID != other.locationID) {
            return false;
        }
        if (!Objects.equals(this.sightingDescription, other.sightingDescription)) {
            return false;
        }
        if (!Objects.equals(this.sightingDate, other.sightingDate)) {
            return false;
        }
        return true;
    }

}
