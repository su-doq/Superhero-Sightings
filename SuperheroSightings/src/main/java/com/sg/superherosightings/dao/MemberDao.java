/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Member;
import java.util.List;

/**
 *
 * @author husst
 */
public interface MemberDao {

    public void addMember(Member member);

    public void deleteMember(int memberID);

    public void updateMember(Member member);

    public Member getMemberById(int memberID);

    public List<Member> getAllMembersOfOrganization();
}
