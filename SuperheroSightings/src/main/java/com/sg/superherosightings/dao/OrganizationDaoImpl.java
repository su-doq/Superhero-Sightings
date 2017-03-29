/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Organization;
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
public class OrganizationDaoImpl implements OrganizationDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
            Organization o = new Organization();
            o.setOrganizationID(rs.getInt("organizationID"));
            o.setOrganizationName(rs.getString("organizationName"));
            o.setOrganizationDescription(rs.getString("organizationDescription"));
            o.setLocationID(rs.getInt("locationID"));
            return o;
        }

    }
    //
    //TODO i use helpers for FKs right?
    private static final String SQL_INSERT_ORGANIZATION
            = "insert into organization (organizationName, organizationDescription, locationID) "
            + "values (?, ?, ?)";

    private static final String SQL_DELETE_ORGANIZATION
            = "delete from organization where organizationID = ?";

    private static final String SQL_DELETE_ORGANIZATION_MEMBERS
            = "delete from member where organizationID = ?";
    private static final String SQL_UPDATE_ORGANIZATION
            = "update organization set organizationName = ?, organizationDescription = ?, locationID = ? "
            + "where organizationID = ?";
    private static final String SQL_UPDATE_ORGANIZATION_LOCATION
            = "update organization set locationID = ? where organizationID = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganization(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getLocationID()
        );
        int organizationID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        organization.setOrganizationID(organizationID);
    }

    @Override
    public void deleteOrganization(int organizationID) {
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, organizationID);
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION_MEMBERS, organizationID);
    }
    private static final String SQL_SELECT_ORGANIZATION_BY_ID
            = "select * from organization where organizationID = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateOrganization(Organization organization) {
        //TODO all of these kinds of updates will need to delete relationships between them
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getLocationID(),
                //
                organization.getOrganizationID()
        );
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION_MEMBERS,
                organization.getOrganizationID());
    }

    @Override
    public Organization getOrganizationByID(int organizationID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION_BY_ID, new OrganizationMapper(), organizationID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
