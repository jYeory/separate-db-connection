package com.jyeory.www.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyeory.www.user.entity.Member;
import com.jyeory.www.user.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	public Optional<Member> getMemberByIdx(int idx){
		return memberRepository.findById(idx);
	}
}
