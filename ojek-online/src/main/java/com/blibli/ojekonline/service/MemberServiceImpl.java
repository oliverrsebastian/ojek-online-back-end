package com.blibli.ojekonline.service;

import com.blibli.ojekonline.exceptions.PriceHigherThanBalanceException;
import com.blibli.ojekonline.model.Member;
import com.blibli.ojekonline.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member getMemberById(int id) {
        logger.warn("get member by id : " + id);
        try {
            return memberRepository.findOne(id);
        } catch (RuntimeException e) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public String reduceBalance(Member member, int balance) {
        int reducedBalance = member.getBalance() - balance;
        if (reducedBalance < 0) {
            throw new PriceHigherThanBalanceException();
        } else {
            member.setBalance(reducedBalance);
            memberRepository.save(member);
        }
        return "Success reduced balance!";
    }

    @Override
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public String deleteMemberById(int id) {
        try {
            memberRepository.delete(id);
        }catch(RuntimeException e){
            return null;
        }
        return "Delete success!";
    }

    @Override
    public List<Member> getMemberList() {
        return memberRepository.findAll();
    }

    @Override
    public String recoverBalance(Member member, int balance) {
        member.setBalance(member.getBalance() + balance);
        return "Recover balance success!";
    }
}
