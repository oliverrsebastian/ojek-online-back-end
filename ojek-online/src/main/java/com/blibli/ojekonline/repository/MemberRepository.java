package com.blibli.ojekonline.repository;

import com.blibli.ojekonline.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
