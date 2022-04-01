package com.jyeory.www.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name="country")
@Getter
@Setter
public class Country {

    /** Primary key. */
    protected static final String PK = "code";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="code", unique=true, nullable=false, length=3)
    private String code;
    @Column(name="name", nullable=false, length=52)
    private String name;
    @Column(name="continent", nullable=false, length=52)
    private String continent;
    @Column(name="region", nullable=false, length=26)
    private String region;
    @Column(name="surface_area", nullable=false, precision = 10)
    private Double surfaceArea;
    @Column(name="indep_year", nullable=true)
    private int indepYear;
    @Column(name="population", nullable=false)
    private int population;
    @Column(name="life_expectancy", nullable=true, precision = 3)
    private Double lifeExpectancy;
    @Column(name="gnp", nullable=true, precision = 3)
    private Double gnp;
    @Column(name="gnp_old", nullable=true, precision = 3)
    private Double gnpOld;
    @Column(name="local_name", nullable=false, length=45)
    private String localName;
    @Column(name="government_form", nullable=false, length=45)
    private String governmentForm;
    @Column(name="head_of_state", nullable=true, length=60)
    private String headOfState;
    @Column(name="capital", nullable=true)
    private int capital;
    @Column(name="code2", nullable=false, length=2)
    private String code2;

}
