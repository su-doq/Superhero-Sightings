/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Sighting;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author husst
 */
public interface SightingDao {

    public void addSighting(Sighting sighting);

    public void deleteSighting(int sightingID);

    public void updateSighting(Sighting sighting);

    public Sighting getSightingByDate(LocalDate sightingDate);

    public Sighting getSightingByID(int sightingID);

    public List<Sighting> getAllSightings();
}
