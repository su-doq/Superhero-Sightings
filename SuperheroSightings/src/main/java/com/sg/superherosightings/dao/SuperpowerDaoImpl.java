/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Superpower;
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
public class SuperpowerDaoImpl implements SuperpowerDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_SUPERPOWER = "insert into superpower "
            + "(superpowerName, superpowerDescription) "
            + "values (? , ?)";

    private static final String SQL_DELETE_SUPERPOWER = "delete from superpower where superpowerID = ?";

    private static final String SQL_UPDATE_SUPERPOWER = "update superpower set superpowerName= ?,"
            + "superpowerDescription = ?"
            + " where superpowerID = ?";

    private static final String SQL_SELECT_SUPERPOWER_BY_ID = "select * from superpower where superpowerID = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSuperpower(Superpower superpower) {
        jdbcTemplate.update(SQL_INSERT_SUPERPOWER,
                superpower.getSuperpowerName(),
                superpower.getSuperpowerDescription()
        );
        int superpowerID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        superpower.setSuperpowerID(superpowerID);
    }

    @Override
    public void deleteSuperpower(int superpowerID) {
        jdbcTemplate.update(SQL_DELETE_SUPERPOWER, superpowerID);
    }

    @Override
    public void updateSuperpower(Superpower superpower) {
        jdbcTemplate.update(SQL_UPDATE_SUPERPOWER,
                superpower.getSuperpowerName(),
                superpower.getSuperpowerDescription(),
                superpower.getSuperpowerID()
        );
    }

    @Override
    public Superpower getSuperpowerByID(int superpowerID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SUPERPOWER_BY_ID, new SuperpowerMapper(), superpowerID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static class SuperpowerMapper implements RowMapper<Superpower> {

        @Override
        public Superpower mapRow(ResultSet rs, int rowNum) throws SQLException {
            Superpower sp = new Superpower();
            sp.setSuperpowerID(rs.getInt("superpowerID"));
            sp.setSuperpowerName(rs.getString("superpowerName"));
            sp.setSuperpowerDescription(rs.getString("superpowerDescription"));
            return sp;
        }
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Superpower> getAllSuperpowersBySuperbeingID(int superbeingID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
