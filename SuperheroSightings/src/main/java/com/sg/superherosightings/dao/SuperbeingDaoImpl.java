/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Superbeing;
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
public class SuperbeingDaoImpl implements SuperbeingDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_SUPERBEING = "insert into superbeing "
            + "(superbeingAlias, superbeingFirstName, superbeingLastName, superbeingDescription) "
            + "values (?, ?, ?, ?)";
    private static final String SQL_DELETE_SUPERBEING = "delete from superbeing where superbeingID = ?";
    private static final String SQL_UPDATE_SUPERBEING = "update superbeing set superbeingAlias = ?, "
            + "superbeingFirstName = ?,"
            + " superbeingLastName = ?, superbeingDescription = ?"
            + " where superbeingID = ?";

    private static final String SQL_SELECT_SUPERBEING_BY_ID = "select * from superbeing where superbeingID = ?";

    private static final String SQL_SELECT_SUPERBEING_BY_LOCATION = "select superbeing.superbeingID, superbeing.SuperbeingAlias, "
            + "superbeing.superbeingFirstName, superbeing.superbeingLastName, superbeing.SuperbeingDescription "
            + "from superbeing "
            + "inner join sighting ON"
            + "(superbeing.superbeingID = sighting.superbeingID) "
            + "inner join location ON "
            + "(sighting.locationID = location.locationID) "
            + "where locationID = ?";

    private static final String SQL_SELECT_SUPERBEING_BY_LOCATION_ID = "select * from superbeing "
            + "inner join sighting ON"
            + "(superbeing.superbeingID = sighting.superbeingID) "
            + "inner join location ON "
            + "(sighting.locationID = location.locationID) "
            + "where location.locationID = ?";

//    private static final String SQL_SELECT_SUPERBEING_BY_LOCATION = "select location.LocationName, sighting.sightingDate, superbeing.SuperbeingAlias, "
//            + "concat (superbeing.superbeingFirstName, ' ', superbeing.superbeingLastName) as Name, superbeing.SuperbeingDescription "
//            + "from superbeing "
//            + "inner join sighting ON"
//            + "(superbeing.superbeingID = sighting.superbeingID) "
//            + "inner join location ON "
//            + "(sighting.locationID = location.locationID) "
//            + "where locationID = ?";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSuperbeing(Superbeing superbeing) {
        jdbcTemplate.update(SQL_INSERT_SUPERBEING,
                superbeing.getSuperbeingAlias(),
                superbeing.getSuperbeingFirstName(),
                superbeing.getSuperbeingLastName(),
                superbeing.getSuperbeingDescription()
        );

        int superbeingID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        superbeing.setSuperbeingID(superbeingID);
    }

    @Override
    public void deleteSuperbeing(int superbeingID) {
        jdbcTemplate.update(SQL_DELETE_SUPERBEING, superbeingID);
    }

    @Override
    public void updateSuperbeing(Superbeing superbeing) {
        jdbcTemplate.update(SQL_UPDATE_SUPERBEING,
                superbeing.getSuperbeingAlias(),
                superbeing.getSuperbeingFirstName(),
                superbeing.getSuperbeingLastName(),
                superbeing.getSuperbeingDescription(),
                superbeing.getSuperbeingID()
        );

    }

    @Override
    public List<Superbeing> getSuperbeingsByLocation(int locationID) {
        try {
            return jdbcTemplate.query(SQL_SELECT_SUPERBEING_BY_LOCATION_ID, new SuperbeingMapper(), locationID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    public Superbeing getSuperbeingByID(int superbeingID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SUPERBEING_BY_ID, new SuperbeingMapper(), superbeingID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private List<Superbeing> associateSuperbeingsWithLocation(List<Superbeing> superbeingList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public List<Superbeing> getAllSuperbeings() {
        return null;
    }

    @Override
    public List<Superbeing> getSuperbeingsByOrganizationID(int organizationID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
