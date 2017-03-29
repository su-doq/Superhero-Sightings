/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author husst
 */
public class AddressDaoImpl implements AddressDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_ADDRESS
            = "insert into address "
            + "(street, city, zip, state, country, latitude, longitude, planet, galaxy, multiverse) "
            + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_ADDRESS
            = "delete from address where addressID = ?";

    private static final String SQL_UPDATE_ADDRESS
            = "update address set street = ?, city = ?, zip = ?, state = ?, "
            + "country = ?, latitude = ?, longitude = ?, "
            + "planet = ?, galaxy = ?, multiverse = ? "
            + "where addressID = ?";
    private static final String SQL_SELECT_ADDRESS_BY_ID
            = "select * from address where addressID = ?";

    private static final String SQL_SELECT_ALL_ADDRESSES
            = "select * from address";

    private static final String SQL_SELECT_LOCATIONS_BY_ADDRESS_ID
            = "select loc.locationID, loc.locationName, loc.locationDescription, loc.addressID "
            + "from location loc "
            + "join address a on "
            + "(a.addressID = loc.addressID) where "
            + "a.addressID = ?";

    private static final String SQL_SELECT_ADDRESS_BY_LOCATION_ID
            = "select * from address a "
            + "join location loc on "
            + "(loc.locationID = a.locationID) "
            + "where loc.locationID = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addAddress(Address address) {
        jdbcTemplate.update(SQL_INSERT_ADDRESS,
                address.getStreet(),
                address.getCity(),
                address.getZip(),
                address.getState(),
                address.getCountry(),
                address.getLatitude(),
                address.getLongitude(),
                address.getPlanet(),
                address.getGalaxy(),
                address.getMultiverse()
        );
        int addressID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        address.setAddressID(addressID);

    }

    @Override
    public void deleteAddress(int addressID) {
        jdbcTemplate.update(SQL_DELETE_ADDRESS, addressID);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateAddress(Address address) {
        jdbcTemplate.update(SQL_UPDATE_ADDRESS,
                address.getStreet(),
                address.getCity(),
                address.getZip(),
                address.getState(),
                address.getCountry(),
                address.getLatitude(),
                address.getLongitude(),
                address.getPlanet(),
                address.getGalaxy(),
                address.getMultiverse(),
                //
                address.getAddressID()
        );
    }

    @Override
    public Address getAddress(int addressID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS_BY_ID, new AddressMapper(), addressID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Address> getAllAddresses() {
        try {
            return jdbcTemplate.query(SQL_SELECT_ALL_ADDRESSES, new AddressMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

//////////////////////////////////
//helpers
//////////////////////////////////
    @Override
    public List<Location> findLocationsForAddress(Address address) {
        return jdbcTemplate.query(SQL_SELECT_LOCATIONS_BY_ADDRESS_ID, new LocationMapper(),
                address.getAddressID());
    }

    public Address findAddressForLocation(Location location) {
        return jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS_BY_LOCATION_ID, new AddressMapper(),
                location.getLocationID());
    }

    @Override
    public List<Location> associateLocationWithAddress(List<Location> locationList) {
        for (Location location : locationList) {
            Address a = findAddressForLocation(location);
            int addressID = a.getAddressID();
            location.setAddressID(addressID);
        }
        return locationList;
    }

//////////////////////////////////
//mappers
//////////////////////////////////
    private static class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Location L = new Location();
            L.setLocationID(rs.getInt("locationID"));
            L.setLocationName(rs.getString("locationName"));
            L.setLocationDescription(rs.getString("locationDescription"));
            L.setAddressID(rs.getInt("addressID"));
            return L;
        }

    }

    private static class AddressMapper implements RowMapper<Address> {

        @Override
        public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
            Address a = new Address();
            a.setAddressID(rs.getInt("addressID"));
            a.setStreet(rs.getString("street"));
            a.setCity(rs.getString("city"));
            a.setZip(rs.getString("zip"));
            a.setState(rs.getString("state"));
            a.setCountry(rs.getString("country"));
            a.setLatitude(rs.getDouble("latitude"));
            a.setLongitude(rs.getDouble("longitude"));
            a.setPlanet(rs.getString("planet"));
            a.setGalaxy(rs.getString("galaxy"));
            a.setMultiverse(rs.getString("multiverse"));
            return a;
        }
    }

}
