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
public class MemberDaoTest {

    MemberDao dao;
    SuperbeingDao superbeingDao;
    OrganizationDao organizationDao;
    LocationDao locationDao;
    AddressDao addressDao;

    public MemberDaoTest() {
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
        dao = ctx.getBean("memberDao", MemberDao.class);
        superbeingDao = ctx.getBean("superbeingDao", SuperbeingDao.class);
        organizationDao = ctx.getBean("organizationDao", OrganizationDao.class);
        addressDao = ctx.getBean("addressDao", AddressDao.class);
        locationDao = ctx.getBean("locationDao", LocationDao.class);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addMember method, of class MemberDao.
     */
    @Test
    public void testAddGetMember() {
        Superbeing sb = new Superbeing();
        sb.setSuperbeingFirstName("Timember");
        sb.setSuperbeingLastName("Huss");
        sb.setSuperbeingAlias("Bubby");
        sb.setSuperbeingDescription("a binch");
        superbeingDao.addSuperbeing(sb);
        sb.setSuperbeingID(sb.getSuperbeingID());

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
        a.setAddressID(a.getAddressID());
        //
        Location l = new Location();
        l.setAddressID(a.getAddressID());
        l.setLocationName("E!");
        l.setLocationDescription("e.");
        locationDao.addLocation(l);
        l.setLocationID(l.getLocationID());
        //
        Organization o = new Organization();
        o.setOrganizationName("Tim League");
        o.setOrganizationDescription("bunch of tims");
        o.setLocationID(l.getLocationID());
        organizationDao.addOrganization(o);
        o.setOrganizationID(o.getOrganizationID());

        LocalDate ldStart = LocalDate.parse("2011-09-01", DateTimeFormatter.ISO_DATE);
        LocalDate ldEnd = LocalDate.parse("2011-09-02", DateTimeFormatter.ISO_DATE);

        Member m = new Member();
        m.setOrganizationID(o.getOrganizationID());
        m.setSuperbeingID(sb.getSuperbeingID());
        m.setStartDate(ldStart);
        m.setEndDate(ldEnd);
        dao.addMember(m);
        Member m2 = dao.getMemberById(m.getMemberID());

        assertEquals(m, m2);

    }

    /**
     * Test of deleteMember method, of class MemberDao.
     */
    @Test
    public void testDeleteMember() {
        Superbeing sb = new Superbeing();
        sb.setSuperbeingFirstName("Timember");
        sb.setSuperbeingLastName("Huss");
        sb.setSuperbeingAlias("Bubby");
        sb.setSuperbeingDescription("a binch");
        superbeingDao.addSuperbeing(sb);
        sb.setSuperbeingID(sb.getSuperbeingID());

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
        a.setAddressID(a.getAddressID());
        //
        Location l = new Location();
        l.setAddressID(a.getAddressID());
        l.setLocationName("Eeeeeeeeee!");
        l.setLocationDescription("e.");
        locationDao.addLocation(l);
        l.setLocationID(l.getLocationID());
//
        Organization o = new Organization();
        o.setOrganizationName("Tim League");
        o.setOrganizationDescription("bunch of tims");
        o.setLocationID(l.getLocationID());
        organizationDao.addOrganization(o);
        o.setOrganizationID(o.getOrganizationID());

        LocalDate ldStart = LocalDate.parse("2011-09-01", DateTimeFormatter.ISO_DATE);
        LocalDate ldEnd = LocalDate.parse("2011-09-02", DateTimeFormatter.ISO_DATE);

        Member m = new Member();
        m.setOrganizationID(o.getOrganizationID());
        m.setSuperbeingID(sb.getSuperbeingID());
        m.setStartDate(ldStart);
        m.setEndDate(ldEnd);
        dao.addMember(m);
        m.setMemberID(m.getMemberID());
        Member m2 = dao.getMemberById(m.getMemberID());
        assertEquals(m, m2);
        dao.deleteMember(m2.getMemberID());
        Member fromDao = dao.getMemberById(m2.getMemberID());

        assertNull(fromDao);
        assertNotEquals(m, fromDao);

    }

    /**
     * Test of updateMember method, of class MemberDao.
     */
    @Test
    public void testUpdateMember() {
        Superbeing sb = new Superbeing();
        sb.setSuperbeingFirstName("Timember");
        sb.setSuperbeingLastName("Huss");
        sb.setSuperbeingAlias("Bubby");
        sb.setSuperbeingDescription("a binch");
        superbeingDao.addSuperbeing(sb);
        sb.setSuperbeingID(sb.getSuperbeingID());

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
        l.setLocationName("Eeeeeeeeee!");
        l.setLocationDescription("e.");
        locationDao.addLocation(l);
        l.setLocationID(l.getLocationID());
        //
        Organization o = new Organization();
        o.setOrganizationName("Tim League");
        o.setOrganizationDescription("bunch of tims");
        o.setLocationID(l.getLocationID());
        organizationDao.addOrganization(o);
        o.setOrganizationID(o.getOrganizationID());

        LocalDate ldStart = LocalDate.parse("2011-09-01", DateTimeFormatter.ISO_DATE);
        LocalDate ldEnd = LocalDate.parse("2011-09-02", DateTimeFormatter.ISO_DATE);

        Member m = new Member();
        m.setOrganizationID(o.getOrganizationID());
        m.setSuperbeingID(sb.getSuperbeingID());
        m.setStartDate(ldStart);
        m.setEndDate(ldEnd);
        dao.addMember(m);
        m.setMemberID(m.getMemberID());

        Member fromDao = dao.getMemberById(m.getMemberID());
        m.setStartDate(LocalDate.parse("2015-09-01", DateTimeFormatter.ISO_DATE));
        dao.updateMember(m);
        Member m2 = dao.getMemberById(m.getMemberID());
        assertNotEquals(m2, fromDao);
    }

    /**
     * Test of getMemberById method, of class MemberDao.
     */
    @Test
    public void testGetMemberById() {
    }

    /**
     * Test of getAllMembersOfOrganization method, of class MemberDao.
     */
    @Test
    public void testGetAllMembersOfOrganization() {
    }

    public class MemberDaoImpl implements MemberDao {

        public void addMember(Member member) {
        }

        public void deleteMember(int memberID) {
        }

        public void updateMember(Member member) {
        }

        public Member getMemberById(int memberID) {
            return null;
        }

        public List<Member> getAllMembersOfOrganization() {
            return null;
        }
    }

}
