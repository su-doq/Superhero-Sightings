/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
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
public class OrganizationDaoTest {

    OrganizationDao dao;
    LocationDao locationDao;
    AddressDao addressDao;

    public OrganizationDaoTest() {
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
        dao = ctx.getBean("organizationDao", OrganizationDao.class);
        locationDao = ctx.getBean("locationDao", LocationDao.class);
        addressDao = ctx.getBean("addressDao", AddressDao.class);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addOrganization method, of class OrganizationDao.
     */
    @Test
    public void testAddGetOrganization() {
        Organization o = new Organization();
        o.setOrganizationName("TIM LEAGUE");
        o.setOrganizationDescription("TIMS EVERYWHERE!!");

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

        o.setLocationID(L.getLocationID());
        dao.addOrganization(o);
        o.setOrganizationID(o.getOrganizationID());
        Organization fromDao = dao.getOrganizationByID(o.getOrganizationID());
        assertEquals(fromDao, o);
    }

    /**
     * Test of deleteOrganization method, of class OrganizationDao.
     */
    @Test
    public void testDeleteOrganization() {
        Organization o = new Organization();
        o.setOrganizationName("TIM LEAGUE");
        o.setOrganizationDescription("TIMS EVERYWHERE!!");

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

        o.setLocationID(L.getLocationID());
        dao.addOrganization(o);
        o.setOrganizationID(o.getOrganizationID());
        Organization fromDao = dao.getOrganizationByID(o.getOrganizationID());
        dao.deleteOrganization(o.getOrganizationID());
        Organization nullOrganization = dao.getOrganizationByID(o.getOrganizationID());
        assertNull(nullOrganization);
        assertNotEquals(fromDao, nullOrganization);
    }

    /**
     * Test of updateOrganization method, of class OrganizationDao.
     */
    @Test
    public void testUpdateOrganization() {
        Organization o = new Organization();
        o.setOrganizationName("TIM LEAGUE");
        o.setOrganizationDescription("TIMS EVERYWHERE!!");

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

        o.setLocationID(L.getLocationID());
        dao.addOrganization(o);
        o.setOrganizationID(o.getOrganizationID());
        Organization fromDao = dao.getOrganizationByID(o.getOrganizationID());
        o.setOrganizationName("TIM ARMY");
        dao.updateOrganization(o);

        Organization updatedO = dao.getOrganizationByID(o.getOrganizationID());
        assertNotEquals(fromDao, updatedO);
        assertEquals(o, updatedO);
    }

    /**
     * Test of getOrganizationByID method, of class OrganizationDao.
     */
    @Test
    public void testGetOrganizationByID() {
    }

    /**
     * Test of getAllOrganizations method, of class OrganizationDao.
     */
    @Test
    public void testGetAllOrganizations() {
    }

    public class OrganizationDaoImpl implements OrganizationDao {

        public void addOrganization(Organization organization) {
        }

        public void deleteOrganization(int organizationID) {
        }

        public void updateOrganization(Organization organization) {
        }

        public Organization getOrganizationByID(int organizationID) {
            return null;
        }

        public List<Organization> getAllOrganizations() {
            return null;
        }
    }

}
