package com.jyeory.www.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jyeory.www.data.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String>{
	
	@Query(value = "SELECT * FROM country WHERE Name = :countryNm", nativeQuery = true)
	public Optional<Country> findByCountryName(@Param("countryNm") String countryNm);
	
}
