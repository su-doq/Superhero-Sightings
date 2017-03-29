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
public class SuperbeingPowerBridge {

    private int superbeingID;
    private int superpowerID;

    public int getSuperbeingID() {
        return superbeingID;
    }

    public void setSuperbeingID(int superbeingID) {
        this.superbeingID = superbeingID;
    }

    public int getSuperpowerID() {
        return superpowerID;
    }

    public void setSuperpowerID(int superpowerID) {
        this.superpowerID = superpowerID;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.superbeingID);
        hash = 53 * hash + Objects.hashCode(this.superpowerID);
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
        final SuperbeingPowerBridge other = (SuperbeingPowerBridge) obj;
        if (!Objects.equals(this.superbeingID, other.superbeingID)) {
            return false;
        }
        if (!Objects.equals(this.superpowerID, other.superpowerID)) {
            return false;
        }
        return true;
    }

}
