/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.AddressDao;
import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Location;
import java.util.List;

/**
 *
 * @author husst
 */
public class LocationServiceImpl implements LocationServiceLayer {

    LocationDao locationDao;
    AddressDao addressDao;

    public LocationServiceImpl(LocationDao locationDao, AddressDao addressDao) {
        this.locationDao = locationDao;
        this.addressDao = addressDao;
    }

//////////////////////////////////
//create location with address
//////////////////////////////////
    @Override
    public Location createLocationWithAddress(Location location, Address address) throws ValidationException {
        location = addLocation(location);
        int locationID = location.getLocationID();
        address = addAddress(address);
        int addressID = address.getAddressID();
        location.setAddressID(addressID);
        updateLocation(location);
        return getLocation(locationID);
    }

//////////////////////////////////
//location
//////////////////////////////////
    @Override
    public Location addLocation(Location location) throws ValidationException {
        locationDao.addLocation(location);
        return location;
    }

    @Override
    public Location getLocation(int locationID) {
        return locationDao.getLocationByID(locationID);
    }

    @Override
    public void updateLocation(Location location) throws ValidationException {
        locationDao.updateLocation(location);
    }

    @Override
    public void deleteLocation(int locationID) {
        locationDao.deleteLocation(locationID);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationDao.getAllLocations();
    }

//////////////////////////////////
//address
//////////////////////////////////
    @Override
    public Address addAddress(Address address) {
        addressDao.addAddress(address);
        return address;
    }

    @Override
    public Address getAddress(int addressID) {
        return addressDao.getAddress(addressID);
    }

    @Override
    public void updateAddress(Address address) {
        addressDao.updateAddress(address);
    }

    @Override
    public void deleteaddress(int addressID) {
        addressDao.deleteAddress(addressID);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressDao.getAllAddresses();
    }

//////////////////////////////////
//helper
//////////////////////////////////
    @Override
    public List<Location> findLocationsForAddress(Address address) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void associateLocationWithAddress(Address address, Location location) {
        location.setAddressID(address.getAddressID());
        locationDao.updateLocation(location);

    }

}
