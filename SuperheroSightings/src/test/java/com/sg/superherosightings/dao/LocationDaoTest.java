/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Location;
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
public class LocationDaoTest {

    LocationDao dao;
    AddressDao addressDao;

    public LocationDaoTest() {
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
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("locationDao", LocationDao.class);
        addressDao = ctx.getBean("addressDao", AddressDao.class);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addLocation method, of class LocationDao.
     */
    @Test
    public void testAddGetLocation() {
        Address a = new Address();
        a.setCity("eeeeeeeee");
        a.setState("OH");
        a.setZip("44011");
        a.setCountry("USA");
        a.setGalaxy("Hella");
        a.setMultiverse("hours");
        a.setPlanet("plut0pia");
        a.setLatitude(1.0000009);
        a.setLongitude(1.00007);
        a.setStreet("aaerrrraa");
        addressDao.addAddress(a);
        Location l = new Location();
        l.setAddressID(a.getAddressID());
        l.setLocationName("E!");
        l.setLocationDescription("e.");
        dao.addLocation(l);
        Location fromDao = dao.getLocationByID(l.getLocationID());
        assertEquals(fromDao, l);
    }

    /**
     * Test of deleteLocation method, of class LocationDao.
     */
    @Test
    public void testDeleteLocation() {
        Address a = new Address();
        a.setCity("test");
        a.setState("OH");
        a.setZip("44011");
        a.setCountry("USA");
        a.setGalaxy("Hella");
        a.setMultiverse("hours");
        a.setPlanet("plut0pia");
        a.setLatitude(1.0000009);
        a.setLongitude(1.00007);
        a.setStreet("aaerrrraa");
        addressDao.addAddress(a);
        Location l = new Location();
        l.setAddressID(a.getAddressID());
        l.setLocationName("E!");
        l.setLocationDescription("e.");
        dao.addLocation(l);

        Location L2 = dao.getLocationByID(l.getLocationID());
        assertEquals(L2, l);
        dao.deleteLocation(l.getLocationID());
        assertNull(dao.getLocationByID(l.getLocationID()));

    }

    /**
     * Test of updateLocation method, of class LocationDao.
     */
    @Test
    public void testUpdateLocation() {
        Address a = new Address();
        a.setCity("fuggintest");
        a.setState("OH");
        a.setZip("44011");
        a.setCountry("USA");
        a.setGalaxy("Hella");
        a.setMultiverse("hours");
        a.setPlanet("plut0pia");
        a.setLatitude(1.0000009);
        a.setLongitude(1.00007);
        a.setStreet("aaerrrraa");
        addressDao.addAddress(a);
        Address oldAddress = addressDao.getAddress(a.getAddressID());
        a.setCity("newie");
        addressDao.updateAddress(a);
        Address newAddress = addressDao.getAddress(a.getAddressID());
        assertEquals(newAddress, a);
    }

    /**
     * Test of getLocationByID method, of class LocationDao.
     */
    @Test
    public void testGetLocationByID() {
    }

    /**
     * Test of getAllLocations method, of class LocationDao.
     */
    @Test
    public void testGetAllLocations() {
    }

    public class LocationDaoImpl implements LocationDao {

        public void addLocation(Location location) {
        }

        public void deleteLocation(int locationID) {
        }

        public void updateLocation(Location location) {
        }

        public Location getLocationByID(int locationID) {
            return null;
        }

        public List<Location> getAllLocations() {
            return null;
        }
    }

}
