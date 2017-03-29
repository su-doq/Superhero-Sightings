/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Superpower;
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
public class SuperpowerDaoTest {

    SuperpowerDao dao;

    public SuperpowerDaoTest() {
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

        dao = ctx.getBean("superpowerDao", SuperpowerDao.class);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setJdbcTemplate method, of class SuperpowerDaoImpl.
     */
    @Test
    public void testSetJdbcTemplate() {
    }

    /**
     * Test of addSuperpower method, of class SuperpowerDaoImpl.
     */
    @Test
    public void testAddSuperpower() {
        Superpower sp = new Superpower();
        sp.setSuperpowerName("Acid p*ss");
        sp.setSuperpowerDescription("It hurts");
        dao.addSuperpower(sp);
        Superpower fromDao = dao.getSuperpowerByID(sp.getSuperpowerID());
        assertEquals(fromDao, sp);
    }

    /**
     * Test of deleteSuperpower method, of class SuperpowerDaoImpl.
     */
    @Test
    public void testDeleteSuperpower() {
        Superpower sp = new Superpower();
        sp.setSuperpowerName("Acid p*ss");
        sp.setSuperpowerDescription("It hurts");
        dao.addSuperpower(sp);
        Superpower fromDao = dao.getSuperpowerByID(sp.getSuperpowerID());
        dao.deleteSuperpower(sp.getSuperpowerID());
        assertNull(dao.getSuperpowerByID(sp.getSuperpowerID()));
    }

    /**
     * Test of updateSuperpower method, of class SuperpowerDaoImpl.
     */
    @Test
    public void testUpdateSuperpower() {
    }

    /**
     * Test of getSuperpowerByID method, of class SuperpowerDaoImpl.
     */
    @Test
    public void testGetSuperpowerByID() {
    }

    /**
     * Test of getAllSuperpowers method, of class SuperpowerDaoImpl.
     */
    @Test
    public void testGetAllSuperpowers() {
    }

    /**
     * Test of getAllSuperpowersBySuperbeingID method, of class
     * SuperpowerDaoImpl.
     */
    @Test
    public void testGetAllSuperpowersBySuperbeingID() {
    }

}
