/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Superbeing;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class SightingDaoImpl implements SightingDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_SIGHTING = "insert into sighting "
            + "(sightingDate, sightingDescription, superbeingID, locationID) "
            + "values (?, ?, ?, ?)";

    private static final String SQL_DELETE_SIGHTING = "delete from sighting where sightingID = ?";

    private static final String SQL_UPDATE_SIGHTING = "update sighting set sightingDate = ?, "
            + "sightingDescription = ?, "
            + "superbeingID = ?, locationID = ? "
            + "where sightingID = ?";

    private static final String SQL_SELECT_SIGHTING_BY_DATE = "select * from sighting where sightingDate = ?";
    private static final String SQL_INSERT_SUPERBEING_INTO_SIGHTING = "insert into ";
    private static final String SQL_SELECT_SUPERBEING_BY_SIGHTING_ID
            = "select sb.superbeingID, sb.superbeingAlias, sb.superbeingFirstName, sb.superbeingLastName, sb.superbeingDescription"
            + " from superbeing sb "
            + "join sighting s on sb.superbeingID = s.superbeingID "
            + "where sb.superbeingID = ?";

    private static final String SQL_SELECT_LOCATION_BY_SIGHTING_ID
            = "select l.locationID, l.locationName, l.locationDescription, l.addressID "
            + "from location l "
            + "join sighting s on l.locationID = s.locationID "
            + "where l.locationID = ?";
//main query
//    private static final String SQL_SELECT_SIGHTINGS_BY_DATE
//            = " select sighting.sightingDate, superbeing.superbeingAlias, concat(superbeing.superbeingFirstName, ' ', superbeing.superbeingLastName), location.locationName "
//            + "from sighting "
//            + "inner join superbeing ON "
//            + "(sighting.superbeingID = superbeing.superbeingID) "
//            + "inner join location ON "
//            + "(sighting.locationID = location.locationID) "
//            + "where sighting.sightingDate = ?";

    private static final String SQL_SELECT_SIGHTINGS_BY_DATE
            = " select * "
            + "from sighting "
            + "inner join superbeing ON "
            + "(sighting.superbeingID = superbeing.superbeingID) "
            + "inner join location ON "
            + "(sighting.locationID = location.locationID) "
            + "where sighting.sightingDate = ?";

    private static final String SQL_SELECT_SIGHTING_BY_ID
            = "select * from sighting where SightingID = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getSightingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                sighting.getSightingDescription(),
                sighting.getSuperbeingID(),
                sighting.getLocationID()
        );

        int sightingID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        sighting.setSightingID(sightingID);
    }

    @Override
    public Sighting getSightingByDate(LocalDate sightingDate) {
        try {
            String date = sightingDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Sighting sighting = jdbcTemplate.queryForObject(SQL_SELECT_SIGHTINGS_BY_DATE, new SightingMapper(), date);
            // sighting.setSuperbeingID(findSuperbeingForSightingDate(sighting));
            //sighting.setLocationID(findLocationForSightingDate(sighting));
            return sighting;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private int findSuperbeingForSightingDate(Sighting sighting) {
        Superbeing sb = jdbcTemplate.queryForObject(SQL_SELECT_SUPERBEING_BY_SIGHTING_ID, new SuperbeingMapper(), sighting.getSightingID());
        return sb.getSuperbeingID();
    }

    private static final class SuperbeingMapper implements RowMapper<Superbeing> {

        @Override
        public Superbeing mapRow(ResultSet rs, int rowNum) throws SQLException {
            Superbeing sb = new Superbeing();
            sb.setSuperbeingID(rs.getInt("superbeingID"));
            sb.setSuperbeingAlias(rs.getString("superbeingAlias"));
            sb.setSuperbeingFirstName(rs.getString("superbeingFirstName"));
            sb.setSuperbeingLastName(rs.getString("superbeingLastName"));
            sb.setSuperbeingDescription(rs.getString("superbeingDescription"));
            return sb;
        }
    }

    private int findLocationForSightingDate(Sighting sighting) {
        Location l = jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING_ID, new LocationMapper(), sighting.getLocationID());
        return l.getLocationID();
    }

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Location l = new Location();
            l.setLocationID(rs.getInt("locationID"));
            l.setLocationName(rs.getString("locationName"));
            l.setLocationDescription(rs.getString("locationDescription"));
            l.setAddressID(rs.getInt("addressID"));
            return l;
        }

    }

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sighting s = new Sighting();
            s.setSightingID(rs.getInt("sightingID"));
            s.setSightingDate(rs.getTimestamp("sightingDate").
                    toLocalDateTime().toLocalDate());
            s.setSightingDescription(rs.getString("sightingDescription"));
            s.setSuperbeingID(rs.getInt("superbeingID"));
            s.setLocationID(rs.getInt("locationID"));
            return s;
        }

    }

    @Override
    public void deleteSighting(int sightingID) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingID);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sighting.getSightingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                sighting.getSightingDescription(),
                sighting.getSuperbeingID(),
                sighting.getLocationID(),
                //
                sighting.getSightingID()
        );
    }

    public Sighting getSightingByID(int sightingID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING_BY_ID, new SightingMapper(), sightingID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
