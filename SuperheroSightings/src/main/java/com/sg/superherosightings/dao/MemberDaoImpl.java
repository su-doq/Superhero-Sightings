/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Member;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author husst
 */
public class MemberDaoImpl implements MemberDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_MEMBER
            = "insert into member "
            + "(organizationID, superbeingID, startDate, endDate) "
            + "values (?, ?, ?, ?)";

    private static final String SQL_DELETE_MEMBER
            = "delete from member where memberID = ?";

    private static final String SQL_UPDATE_MEMBER
            = "update member set "
            + "organizationID = ?, memberID = ?, startDate = ?, endDate = ? "
            + "where memberID = ?";

    private static final String SQL_GET_ALL_MEMBERS_OF_ORGANIZATION
            = "select organization.organizationName, superbeing.superbeingAlias, concat(superbeing.superbeingFirstName, ' ', superbeing.superbeingLastName) as Name "
            + "from organization "
            + "inner join member ON "
            + "(organization.organizationID = member.organizationID) "
            + "inner join superbeing ON "
            + "(member.superbeingID = superbeing.superbeingID) "
            + "where organizationID = ?";

    private static final String SQL_SELECT_ALL_ORGANIZATIONS_OF_SUPERBEING
            = "select organization.organizationName, superbeing.superbeingAlias, concat(superbeing.superbeingFirstName, ' ', superbeing.superbeingLastName) as Name "
            + "from superbeing "
            + "inner join member ON"
            + "(member.superbeingID = superbeing.superbeingID) "
            + "inner join organization ON "
            + "(member.organizationID = organization.organizationID) "
            + "where superbeing.superbeingID = ?";

    private static final String SQL_SELECT_MEMBER_BY_ID
            = "select * from member where memberID = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addMember(Member member) {
        jdbcTemplate.update(SQL_INSERT_MEMBER,
                member.getOrganizationID(),
                member.getSuperbeingID(),
                member.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                member.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        );
        int memberID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        member.setMemberID(memberID);
    }

    @Override
    public void deleteMember(int memberID) {
        jdbcTemplate.update(SQL_DELETE_MEMBER, memberID);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateMember(Member member) {
        jdbcTemplate.update(SQL_UPDATE_MEMBER,
                member.getOrganizationID(),
                member.getSuperbeingID(),
                member.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                member.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                //
                member.getMemberID()
        );
    }

    @Override
    public Member getMemberById(int memberID) {
        //TODO do i need to do these Gets if the spec doesnt say we need to
        // search specifically for this but another DAO may require it?
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_MEMBER_BY_ID, new MemberMapper(), memberID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Member> getAllMembersOfOrganization() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static class MemberMapper implements RowMapper<Member> {

        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member m = new Member();
            m.setMemberID(rs.getInt("memberID"));
            m.setOrganizationID(rs.getInt("organizationID"));
            m.setSuperbeingID(rs.getInt("superbeingID"));
            m.setStartDate(rs.getTimestamp("startDate").toLocalDateTime().toLocalDate());
            m.setEndDate(rs.getTimestamp("endDate").toLocalDateTime().toLocalDate());
            return m;
        }

    }

}
