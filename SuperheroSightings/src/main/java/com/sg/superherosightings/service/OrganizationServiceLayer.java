/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import java.util.List;

/**
 *
 * @author husst
 */
public interface OrganizationServiceLayer {

    public Organization addOrganization(Organization organization) throws ValidationException;

    public Organization getOrganization(int organizationID);

    public void updateLocation(Location location) throws ValidationException;

    public void deleteLocation(int locationID);

    public List<Organization> getAllOrganizations();

}
