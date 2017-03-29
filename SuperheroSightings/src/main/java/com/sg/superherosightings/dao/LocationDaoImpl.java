/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author husst
 */
public class LocationDaoImpl implements LocationDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final String SQL_INSERT_LOCATION
            = "insert into location "
            + "(locationName, locationDescription, addressID) "
            + "values (? ,?, ?)";

    private static final String SQL_DELETE_LOCATION
            = "delete from location where locationID = ?";

    private static final String SQL_UPDATE_LOCATION
            = "update location set locationName = ?, location Description = ? "
            + "where locationID = ?";

    private static final String SQL_SELECT_LOCATIONS_BY_SUPERBEING_ID
            = "select location.locationName, sighting.sightingDate, superbeing.superbeingAlias, concat(superbeing.superbeingFirstName, ' ', superbeingLastName) "
            + "from sighting "
            + "inner join superbeing ON "
            + "(sighting.superbeingID = sighting.superbeingID) "
            + "inner join location ON "
            + "(sighting.locationID = location.locationID) "
            + "where superbeing.superbeingID = ?";

    private static final String SQL_SELECT_LOCATION_BY_ID
            = "select * from location where locationID = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getAddressID()
        );
        int locationID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        location.setLocationID(locationID);
    }

    @Override
    public void deleteLocation(int locationID) {
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationID);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getAddressID(),
                //
                location.getLocationID()
        );
    }

    @Override
    public Location getLocationByID(int locationID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_ID, new LocationMapper(), locationID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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

}
