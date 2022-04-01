package com.jyeory.www.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jyeory.www.data.entity.Country;
import com.jyeory.www.data.service.CountryService;
import com.jyeory.www.user.entity.Member;
import com.jyeory.www.user.service.MemberService;

@RestController
public class CustomController {
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/country")
    public ResponseEntity<Country> getCountry(@RequestParam String countryCd){
        Optional<Country> country = countryService.getCountryByName(countryCd);
        return ResponseEntity.status(HttpStatus.OK).body(country.isEmpty() ? null : country.get());
    }
	
	@GetMapping("/member")
    public ResponseEntity<Member> getMember(@RequestParam int idx){
        Optional<Member> member = memberService.getMemberByIdx(idx);
        return ResponseEntity.status(HttpStatus.OK).body(member.isEmpty() ? null : member.get());
    }
    
}
