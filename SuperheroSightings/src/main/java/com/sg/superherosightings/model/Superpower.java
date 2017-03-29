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
public class Superpower {

    private int superpowerID;
    private String superpowerName;
    private String superpowerDescription;

    public int getSuperpowerID() {
        return superpowerID;
    }

    public void setSuperpowerID(int superpowerID) {
        this.superpowerID = superpowerID;
    }

    public String getSuperpowerName() {
        return superpowerName;
    }

    public void setSuperpowerName(String superpowerName) {
        this.superpowerName = superpowerName;
    }

    public String getSuperpowerDescription() {
        return superpowerDescription;
    }

    public void setSuperpowerDescription(String superpowerDescription) {
        this.superpowerDescription = superpowerDescription;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.superpowerID;
        hash = 37 * hash + Objects.hashCode(this.superpowerName);
        hash = 37 * hash + Objects.hashCode(this.superpowerDescription);
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
        final Superpower other = (Superpower) obj;
        if (this.superpowerID != other.superpowerID) {
            return false;
        }
        if (!Objects.equals(this.superpowerName, other.superpowerName)) {
            return false;
        }
        if (!Objects.equals(this.superpowerDescription, other.superpowerDescription)) {
            return false;
        }
        return true;
    }

}
