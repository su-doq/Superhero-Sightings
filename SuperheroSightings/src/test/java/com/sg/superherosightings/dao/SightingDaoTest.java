/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Superbeing;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class SightingDaoTest {

    SightingDao sightingDao;
    SuperbeingDao superbeingDao;
    LocationDao locationDao;
    AddressDao addressDao;

    public SightingDaoTest() {
        this.sightingDao = sightingDao;
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
        sightingDao = ctx.getBean("sightingDao", SightingDao.class);

        superbeingDao = ctx.getBean("superbeingDao", SuperbeingDao.class);
        locationDao = ctx.getBean("locationDao", LocationDao.class);
        addressDao = ctx.getBean("addressDao", AddressDao.class);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addSighting method, of class SightingDao.
     */
    @Test
    public void testAddGetSighting() {
        Address a = new Address();
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
        addressDao.addAddress(a);
        a.setAddressID(a.getAddressID());

        Location L = new Location();
        L.setLocationName("Tims house");
        L.setLocationDescription("just his house");
        L.setAddressID(a.getAddressID());
        locationDao.addLocation(L);
        L.setLocationID(L.getLocationID());

        Superbeing sb = new Superbeing();
        sb.setSuperbeingFirstName("Timembersighting");
        sb.setSuperbeingLastName("Huss");
        sb.setSuperbeingAlias("Bubby");
        sb.setSuperbeingDescription("a binch");
        superbeingDao.addSuperbeing(sb);
        sb.setSuperbeingID(sb.getSuperbeingID());

        Sighting s = new Sighting();
        s.setSightingDate(LocalDate.parse("2019-10-10", DateTimeFormatter.ISO_DATE));
        s.setSightingDescription("hoooly crud it wqs the real life Bubby");
        s.setSuperbeingID(sb.getSuperbeingID());
        s.setLocationID(L.getLocationID());
        sightingDao.addSighting(s);
        s.setSightingID(s.getSightingID());

//        Sighting fromDao = sightingDao.getSightingByDate(s.getSightingDate());
        Sighting fromDaoID = sightingDao.getSightingByID(s.getSightingID());

        assertEquals(fromDaoID, s);

    }

    /**
     * Test of deleteSighting method, of class SightingDao.
     */
    @Test
    public void testDeleteSighting() {
        Address a = new Address();
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
        addressDao.addAddress(a);
        a.setAddressID(a.getAddressID());

        Location L = new Location();
        L.setLocationName("Tims house");
        L.setLocationDescription("just his house");
        L.setAddressID(a.getAddressID());
        locationDao.addLocation(L);
        L.setLocationID(L.getLocationID());

        Superbeing sb = new Superbeing();
        sb.setSuperbeingFirstName("Timembersighting");
        sb.setSuperbeingLastName("Huss");
        sb.setSuperbeingAlias("Bubby");
        sb.setSuperbeingDescription("a binch");
        superbeingDao.addSuperbeing(sb);
        sb.setSuperbeingID(sb.getSuperbeingID());

        Sighting s = new Sighting();
        s.setSightingDate(LocalDate.parse("2019-10-10", DateTimeFormatter.ISO_DATE));
        s.setSightingDescription("hoooly crud it wqs the real life Bubby");
        s.setSuperbeingID(sb.getSuperbeingID());
        s.setLocationID(L.getLocationID());
        sightingDao.addSighting(s);
        s.setSightingID(s.getSightingID());
        sightingDao.deleteSighting(s.getSightingID());
//        Sighting fromDao = sightingDao.getSightingByDate(s.getSightingDate());
        Sighting fromDaoID = sightingDao.getSightingByID(s.getSightingID());
        assertNull(fromDaoID);
        assertNotEquals(fromDaoID, s);
    }

    /**
     * Test of updateSighting method, of class SightingDao.
     */
    @Test
    public void testUpdateSighting() {
        Address a = new Address();
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
        addressDao.addAddress(a);
        a.setAddressID(a.getAddressID());

        Location L = new Location();
        L.setLocationName("Tims house");
        L.setLocationDescription("just his house");
        L.setAddressID(a.getAddressID());
        locationDao.addLocation(L);
        L.setLocationID(L.getLocationID());

        Superbeing sb = new Superbeing();
        sb.setSuperbeingFirstName("Timembersighting");
        sb.setSuperbeingLastName("Huss");
        sb.setSuperbeingAlias("Bubby");
        sb.setSuperbeingDescription("a binch");
        superbeingDao.addSuperbeing(sb);
        sb.setSuperbeingID(sb.getSuperbeingID());

        Sighting s = new Sighting();
        s.setSightingDate(LocalDate.parse("2019-10-10", DateTimeFormatter.ISO_DATE));
        s.setSightingDescription("hoooly crud it wqs the real life Bubby");
        s.setSuperbeingID(sb.getSuperbeingID());
        s.setLocationID(L.getLocationID());
        sightingDao.addSighting(s);
        s.setSightingID(s.getSightingID());
//        Sighting fromDao = sightingDao.getSightingByDate(s.getSightingDate());
        Sighting fromDao = sightingDao.getSightingByID(s.getSightingID());
        s.setSightingDescription("nevermind it was not bubby at all");
        sightingDao.updateSighting(s);
//        Sighting updatedSighting = sightingDao.getSightingByDate(s.getSightingDate());
        Sighting updatedSightingByID = sightingDao.getSightingByID(s.getSightingID());
        assertEquals(updatedSightingByID, s);
    }

    /**
     * Test of getSightingByDate method, of class SightingDao.
     */
    @Test
    public void testGetSightingByDate() {
    }

    /**
     * Test of getAllSightings method, of class SightingDao.
     */
    @Test
    public void testGetAllSightings() {
    }

    public class SightingDaoImpl implements SightingDao {

        public void addSighting(Sighting sighting) {
        }

        public void deleteSighting(int sightingID) {
        }

        public void updateSighting(Sighting sighting) {
        }

        public Sighting getSightingByDate(LocalDate sightingDate) {
            return null;
        }

        public List<Sighting> getAllSightings() {
            return null;
        }

        @Override
        public Sighting getSightingByID(int sightingID) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

}
