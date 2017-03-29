/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Superbeing;
import com.sg.superherosightings.model.Superpower;
import java.util.List;

/**
 *
 * @author husst
 */
public interface SuperbeingServiceLayer {
//////////////////////////////////
//superbeing
//////////////////////////////////

    public Superbeing addSuperbeing(Superbeing superbeing) throws ValidationException;

    public Superbeing getSuperbeing(int superbeingID);

    public void updateSuperbeing(Superbeing superbeing) throws ValidationException;

    public void deleteSuperbeing(int superbeingID);

    public List<Superbeing> getAllSuperbeings();

//////////////////////////////////
//superpower
//////////////////////////////////
    public Superpower addSuperpower(Superpower superpower) throws ValidationException;

    public Superpower getSuperpower(int superpowerID) throws ValidationException;

    public void updateSuperpower(Superpower superpower) throws ValidationException;

    public void deleteSuperpower(int superpowerID);

    public List<Superpower> getAllSuperpowers();

///////////////////////////////////
//bridge
//////////////////////////////////
    public List<Superpower> getSuperpowersOfBeing(int superbeingID);

    public void associateSuperbeingWithSuperpower(int superbeingID, int superpowerID);

    public void deleteSuperbeingSuperpowerBridge(int superbeingID, int superpowerID);
}
