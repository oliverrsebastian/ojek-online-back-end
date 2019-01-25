package com.blibli.ojekonline.service;

import com.blibli.ojekonline.model.Member;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MemberService {

    Member getMemberById(int id);

    String reduceBalance(Member member, int balance);

    Member saveMember(Member member);

    String deleteMemberById(int id);

    List<Member> getMemberList();

    String recoverBalance(Member member, int balance);
}
