/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.AddressDao;
import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.SuperbeingDao;
import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import java.util.List;

/**
 *
 * @author husst
 */
public class SightingServiceImpl implements SightingServiceLayer {

    SuperbeingDao superbeingDao;
    LocationDao locationDao;
    AddressDao addressDao;

    public SightingServiceImpl(SuperbeingDao superbeingDao, LocationDao locationDao) {
        this.superbeingDao = superbeingDao;
        this.locationDao = locationDao;
    }

//////////////////////////////////
//create sighting with location
//////////////////////////////////
    @Override
    public Sighting createSightingWithLocation(Sighting sighting, Location location, Address address) throws ValidationException {
        addSighting(sighting);
        locationDao.addLocation(location);
        addressDao.addAddress(address);
        location.setAddressID(address.getAddressID());
        sighting.setLocationID(location.getLocationID());
        updateSighting(sighting);
        return sighting;
    }

//////////////////////////////////
//sighting
//////////////////////////////////
    @Override
    public Sighting addSighting(Sighting sighting) throws ValidationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateSighting(Sighting sighting) throws ValidationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteSighting(int sightingID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sighting getSighting(int sightingID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sighting> getAllSightings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
