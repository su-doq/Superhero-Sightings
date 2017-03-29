/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Location;
import java.util.List;

/**
 *
 * @author husst
 */
//TODO dont forget to move beans to spring persistence
public interface LocationServiceLayer {

//////////////////////////////////
//create location with address
//////////////////////////////////
    public Location createLocationWithAddress(Location location, Address address) throws ValidationException;

//////////////////////////////////
//location
//////////////////////////////////
    public Location addLocation(Location location) throws ValidationException;

    public Location getLocation(int locationID);

    public void updateLocation(Location location) throws ValidationException;

    public void deleteLocation(int locationID);

    public List<Location> getAllLocations();
//////////////////////////////////
//address
//////////////////////////////////

    public Address addAddress(Address address);

    public Address getAddress(int addressID);

    public void updateAddress(Address address);

    public void deleteaddress(int addressID);

    public List<Address> getAllAddresses();

//////////////////////////////////
//bridge
//////////////////////////////////
    public List<Location> findLocationsForAddress(Address address);

    public void associateLocationWithAddress(Address address, Location location);

}
