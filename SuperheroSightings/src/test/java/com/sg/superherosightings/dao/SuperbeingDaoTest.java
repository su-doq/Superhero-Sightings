/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Member;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Superbeing;
import com.sg.superherosightings.model.SuperbeingPowerBridge;
import com.sg.superherosightings.model.Superpower;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author husst
 */
public class SuperbeingDaoTest {

    SuperbeingDao dao = new SuperbeingDaoImpl();

    public SuperbeingDaoTest() {
        this.dao = dao;
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("superbeingDao", SuperbeingDao.class);

        Superbeing sb = new Superbeing();
        sb.setSuperbeingID(1);
        sb.setSuperbeingFirstName("Tim");
        sb.setSuperbeingLastName("Huss");
        sb.setSuperbeingAlias("Bubby");
        sb.setSuperbeingDescription("a binch");
        dao.addSuperbeing(sb);

        Superpower sp = new Superpower();
        sp.setSuperpowerID(1);
        sp.setSuperpowerName("Infinite jumps");

        SuperbeingPowerBridge superpowerBridge = new SuperbeingPowerBridge();
        superpowerBridge.setSuperbeingID(10);
        superpowerBridge.setSuperpowerID(1);

        Sighting sight = new Sighting();
        sight.setSightingID(1);
        sight.setSuperbeingID(100);
        sight.setSightingDescription("saw someone");

        Organization o = new Organization();
        o.setOrganizationID(1);
        o.setOrganizationName("Tim League");
        o.setOrganizationDescription("bunch of tims");

        Member m = new Member();
        m.setMemberID(1);
        m.setSuperbeingID(1);
        m.setOrganizationID(1);

        Address a = new Address();
        a.setAddressID(1);
        a.setCity("Akron");
        a.setState("OH");

        Location l = new Location();
        l.setLocationID(1);
        l.setLocationName("Akron");
        l.setAddressID(1);

    }

    @After
    public void tearDown() {
        dao.deleteSuperbeing(100);
    }

    /**
     * Test of addSuperbeing method, of class SuperbeingDao.
     */
    @Test
    public void testAddSuperbeing() {
        Superbeing sb = new Superbeing();
        sb.setSuperbeingID(2);
        sb.setSuperbeingFirstName("Tim");
        sb.setSuperbeingLastName("Huss");
        sb.setSuperbeingAlias("Bubby");
        sb.setSuperbeingDescription("a binch");

        dao.addSuperbeing(sb);

        //
        Superbeing fromDao = dao.getSuperbeingByID(sb.getSuperbeingID());
        assertEquals(fromDao, sb);
    }

    /**
     * Test of deleteSuperbeing method, of class SuperbeingDao.
     */
    @Test
    public void testDeleteSuperbeing() {
        Superbeing sb = new Superbeing();
        sb.setSuperbeingFirstName("Tim");
        sb.setSuperbeingLastName("Huss");
        sb.setSuperbeingAlias("Bubby");
        sb.setSuperbeingDescription("a binch");
        dao.addSuperbeing(sb);
        Superbeing fromDao = dao.getSuperbeingByID(sb.getSuperbeingID());
        assertEquals(fromDao, sb);
        dao.deleteSuperbeing(sb.getSuperbeingID());

        assertNull(dao.getSuperbeingByID(sb.getSuperbeingID()));
    }

    /**
     * Test of updateSuperbeing method, of class SuperbeingDao.
     */
    @Test
    public void testUpdateSuperbeing() {
    }

    /**
     * Test of getSuperbeingByID method, of class SuperbeingDao.
     */
    @Test
    public void testGetSuperbeingByID() {
    }

    @Test
    public void getSuperbeingsByLocation() {
        //TODO search superbeings by locations still erroring
        List<Superbeing> sbList = dao.getSuperbeingsByLocation(1);
        assertEquals(1, sbList.size());
    }

    /**
     * Test of getAllSuperbeings method, of class SuperbeingDao.
     */
    @Test
    public void testGetAllSuperbeings() {
    }

    /**
     * Test of getSuperbeingsByOrganizationID method, of class
     * SuperbeingDao.
     */
    @Test
    public void testGetSuperbeingsByOrganizationID() {
    }

    public class SuperbeingDaoImpl implements SuperbeingDao {

        public void addSuperbeing(Superbeing superbeing) {
        }

        public void deleteSuperbeing(int superbeingID) {
        }

        public void updateSuperbeing(Superbeing superbeing) {
        }

        public Superbeing getSuperbeingByID(int superbeingID) {
            return null;
        }

        public List<Superbeing> getAllSuperbeings() {
            return null;
        }

        public List<Superbeing> getSuperbeingsByOrganizationID(int organizationID) {
            return null;
        }

        @Override
        public List<Superbeing> getSuperbeingsByLocation(int locationID) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

}
