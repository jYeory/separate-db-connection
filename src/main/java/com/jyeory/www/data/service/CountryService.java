package com.jyeory.www.data.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyeory.www.data.entity.Country;
import com.jyeory.www.data.repository.CountryRepository;

@Service
public class CountryService {
	
	@Autowired
	private CountryRepository countryRepository;
	
	public Optional<Country> getCountryByName(String code){
		return countryRepository.findById(code);
	}

}
