package com.jyeory.www.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jyeory.www.user.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{
	
}
