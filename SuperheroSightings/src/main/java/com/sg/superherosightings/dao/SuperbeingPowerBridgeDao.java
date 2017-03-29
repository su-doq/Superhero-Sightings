/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Superbeing;
import com.sg.superherosightings.model.Superpower;
import java.util.List;

/**
 *
 * @author husst
 */
public interface SuperbeingPowerBridgeDao {

    public int getSuperbeingIDBySuperpowerID(int superpowerID);

    public List<Superpower> getSuperpowersBySuperbeingID(int superbeingID);

    public void associateSuperpowerWithSuperbeing(int superpowerID, int superbeingID);

}
