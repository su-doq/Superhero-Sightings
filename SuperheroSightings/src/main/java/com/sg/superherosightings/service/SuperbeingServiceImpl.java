/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.SuperbeingDao;
import com.sg.superherosightings.dao.SuperbeingPowerBridgeDao;
import com.sg.superherosightings.dao.SuperpowerDao;
import com.sg.superherosightings.model.Superbeing;
import com.sg.superherosightings.model.SuperbeingPowerBridge;
import com.sg.superherosightings.model.Superpower;

import java.util.List;

/**
 *
 * @author husst
 */
public class SuperbeingServiceImpl implements SuperbeingServiceLayer {

    SuperbeingDao superbeingDao;
    SuperpowerDao superpowerDao;
    SuperbeingPowerBridgeDao superbeingPowerBridgeDao;

    public SuperbeingServiceImpl(SuperbeingDao superbeingDao,
            SuperpowerDao superpowerDao,
            SuperbeingPowerBridgeDao superbeingPowerBridgeDao) {
        this.superbeingDao = superbeingDao;
        this.superpowerDao = superpowerDao;
        this.superbeingPowerBridgeDao = superbeingPowerBridgeDao;

    }

//////////////////////////////////
//assemble superbeing with powers
//////////////////////////////////
    public Superbeing createSuperbeing(Superbeing superbeing, Superpower superpower) throws ValidationException {
        superbeing = addSuperbeing(superbeing);
        int superbeingID = superbeing.getSuperbeingID();
        superpower = addSuperpower(superpower);
        int superpowerID = superpower.getSuperpowerID();
        associateSuperbeingWithSuperpower(superbeingID, superpowerID);
        return superbeing;
    }
//////////////////////////////////
// superbeing
//////////////////////////////////

    @Override
    public Superbeing addSuperbeing(Superbeing superbeing) throws ValidationException {
        superbeingDao.addSuperbeing(superbeing);
        return superbeingDao.getSuperbeingByID(superbeing.getSuperbeingID());
    }

    @Override
    public Superbeing getSuperbeing(int superbeingID) {
        return superbeingDao.getSuperbeingByID(superbeingID);
    }

    @Override
    public void updateSuperbeing(Superbeing superbeing) throws ValidationException {
        superbeingDao.updateSuperbeing(superbeing);
    }

    @Override
    public void deleteSuperbeing(int superbeingID) {
        superbeingDao.deleteSuperbeing(superbeingID);
    }

    @Override
    public List<Superbeing> getAllSuperbeings() {
        return superbeingDao.getAllSuperbeings();
    }

//////////////////////////////////
//superpowers
//////////////////////////////////
    @Override
    public Superpower addSuperpower(Superpower superpower) throws ValidationException {
        superpowerDao.addSuperpower(superpower);
        return superpowerDao.getSuperpowerByID(superpower.getSuperpowerID());
    }

    @Override
    public Superpower getSuperpower(int superpowerID) throws ValidationException {
        return superpowerDao.getSuperpowerByID(superpowerID);
    }

    @Override
    public void updateSuperpower(Superpower superpower) throws ValidationException {
        superpowerDao.updateSuperpower(superpower);
    }

    @Override
    public void deleteSuperpower(int superpowerID) {
        superpowerDao.deleteSuperpower(superpowerID);
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        return superpowerDao.getAllSuperpowers();
    }

//////////////////////////////////
//bridge
//////////////////////////////////
    @Override
    public List<Superpower> getSuperpowersOfBeing(int superbeingID) {
        return superbeingPowerBridgeDao.getSuperpowersBySuperbeingID(superbeingID);
    }

    @Override
    public void associateSuperbeingWithSuperpower(int superbeingID, int superpowerID) {
        superbeingPowerBridgeDao.associateSuperpowerWithSuperbeing(superpowerID, superbeingID);
    }

    @Override
    public void deleteSuperbeingSuperpowerBridge(int superbeingID, int superpowerID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
