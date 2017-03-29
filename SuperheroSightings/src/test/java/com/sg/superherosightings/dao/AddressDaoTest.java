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
public class AddressDaoTest {

    AddressDao dao;

    public AddressDaoTest() {
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

        dao = ctx.getBean("addressDao", AddressDao.class);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addAddress method, of class AddressDao.
     */
    @Test
    public void testAddGetAddress() {
        Address a = new Address();
//        a.setAddressID(1);
        a.setCity("Akron");
        a.setState("OH");
        a.setZip("44011");
        a.setCountry("USA");
        a.setGalaxy("Hell");
        a.setMultiverse("ours");
        a.setPlanet("plut0");
        a.setLatitude(1.0000009);
        a.setLongitude(1.00007);
        a.setStreet("aaaa");
        dao.addAddress(a);
        Address fromDao = dao.getAddress(a.getAddressID());
        assertEquals(fromDao, a);
    }

    /**
     * Test of deleteAddress method, of class AddressDao.
     */
    @Test
    public void testDeleteAddress() {
        Address a = new Address();
//        a.setAddressID(1);
        a.setCity("Aaaaaaan");
        a.setState("OH");
        a.setZip("44011");
        a.setCountry("USA");
        a.setGalaxy("Hell");
        a.setMultiverse("ours");
        a.setPlanet("plut0");
        a.setLatitude(1.0000009);
        a.setLongitude(1.00007);
        a.setStreet("aaaa");
        dao.addAddress(a);
        Address fromDao = dao.getAddress(a.getAddressID());
        assertEquals(fromDao, a);
        dao.deleteAddress(a.getAddressID());
//        Address deletedFromDao = dao.getAddress(a.getAddressID());
        assertNull(dao.getAddress(a.getAddressID()));
    }

    /**
     * Test of updateAddress method, of class AddressDao.
     */
    @Test
    public void testUpdateAddress() {
        Address a = new Address();
//        a.setAddressID(1);
        a.setCity("Aaaaaaan");
        a.setState("OH");
        a.setZip("44011");
        a.setCountry("USA");
        a.setGalaxy("Hell");
        a.setMultiverse("ours");
        a.setPlanet("plut0");
        a.setLatitude(1.0000009);
        a.setLongitude(1.00007);
        a.setStreet("aaaa");
        dao.addAddress(a);
        Address originalAddress = dao.getAddress(a.getAddressID());
        a.setCity("HELL!");
        dao.updateAddress(a);

        assertNotEquals(a, originalAddress);
    }

    /**
     * Test of getAddress method, of class AddressDao.
     */
    @Test
    public void testGetAddress() {
    }

    /**
     * Test of getAllAddresses method, of class AddressDao.
     */
    @Test
    public void testGetAllAddresses() {
    }

    public class AddressDaoImpl implements AddressDao {

        public void addAddress(Address address) {
        }

        public void deleteAddress(int addressID) {
        }

        public void updateAddress(Address address) {
        }

        public Address getAddress(int addressID) {
            return null;
        }

        public List<Address> getAllAddresses() {
            return null;
        }

        @Override
        public List<Location> findLocationsForAddress(Address address) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public void associateLocationWithAddress(Address address, Location location) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<Location> associateLocationWithAddress(List<Location> locationList) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

}
