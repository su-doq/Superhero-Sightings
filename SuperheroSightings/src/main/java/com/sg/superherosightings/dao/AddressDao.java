/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Location;
import java.util.List;

/**
 *
 * @author husst
 */
public interface AddressDao {

    public void addAddress(Address address);

    public void deleteAddress(int addressID);

    public void updateAddress(Address address);

    public Address getAddress(int addressID);

    public List<Address> getAllAddresses();

    public List<Location> findLocationsForAddress(Address address);

    public List<Location> associateLocationWithAddress(List<Location> locationList);
}
