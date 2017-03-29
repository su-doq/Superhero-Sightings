/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Superbeing;
import java.util.List;

/**
 *
 * @author husst
 */
public interface SuperbeingDao {

    public void addSuperbeing(Superbeing superbeing);

    public void deleteSuperbeing(int superbeingID);

    public void updateSuperbeing(Superbeing superbeing);

    public Superbeing getSuperbeingByID(int superbeingID);

    public List<Superbeing> getSuperbeingsByLocation(int locationID);

    public List<Superbeing> getAllSuperbeings();

    public List<Superbeing> getSuperbeingsByOrganizationID(int organizationID);

}
