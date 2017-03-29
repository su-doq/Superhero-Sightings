/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import java.util.List;

/**
 *
 * @author husst
 */
public interface SightingServiceLayer {

    public Sighting createSightingWithLocation(Sighting sighting, Location location, Address address) throws ValidationException;

    public Sighting addSighting(Sighting sighting) throws ValidationException;

    public void updateSighting(Sighting sighting) throws ValidationException;

    public void deleteSighting(int sightingID);

    public Sighting getSighting(int sightingID);

    public List<Sighting> getAllSightings();

}
