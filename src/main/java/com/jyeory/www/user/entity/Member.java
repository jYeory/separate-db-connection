package com.jyeory.www.user.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name="member")
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idx", unique=true, nullable=false, precision=10)
    private int idx;
    @Column(name="member_id", nullable=false, length=12)
    private String memberId;
    @Column(name="name", nullable=false, length=40)
    private String name;
    @Column(name="email", nullable=false, length=100)
    private String email;
    @Column(name="reg_dtm")
    private Timestamp regDtm;

}
