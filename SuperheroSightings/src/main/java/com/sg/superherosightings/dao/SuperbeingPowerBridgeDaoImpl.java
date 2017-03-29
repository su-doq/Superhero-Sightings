/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Superbeing;
import com.sg.superherosightings.model.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author husst
 */
public class SuperbeingPowerBridgeDaoImpl implements SuperbeingPowerBridgeDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_BRIDGE
            = "insert into superbeingpowerbridge (superbeingID, superpowerID) "
            + "values (?, ?)";

    private static final String SQL_SELECT_SUPERBEING_ID
            = "select superbeingID where superpowerID = ?";

    private static final String SQL_SELECT_SUPERPOWERS_BY_SUPERBEING_ID
            = "select sp.superpowerID, sp.superpowerName, sp.superpowerDescription "
            + "from superpower sp "
            + "inner join superbeingPowerBridge as bridge ON "
            + "(sp.superpowerID = bridge.superpowerID) "
            + "inner join superbeing as sb ON "
            + "(sb.superbeingID = bridge.superbeingID) "
            + "where sb.superbeingID = ?";

    @Override
    public int getSuperbeingIDBySuperpowerID(int superpowerID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SUPERBEING_ID, new SuperbeingIDMapper(), superpowerID);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<Superpower> getSuperpowersBySuperbeingID(int superbeingID) {
        try {
            return jdbcTemplate.query(SQL_SELECT_SUPERPOWERS_BY_SUPERBEING_ID, new SuperpowerMapper(), superbeingID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void associateSuperpowerWithSuperbeing(int superpowerID, int superbeingID) {
        jdbcTemplate.update(SQL_INSERT_BRIDGE, superbeingID, superpowerID);
    }

    private static class SuperbeingIDMapper implements RowMapper<Integer> {

        @Override
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            int ID = rs.getInt("superbeingID");
            return ID;
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

}
