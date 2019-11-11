package com.elca.jobfairmanagementsystem.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ghr
 */

@Getter
@Setter
@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private Long candidateId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_no")
    private int mobileNo;


    @Column(name = "phone_no")
    private int phoneNo;

    @Column(name = "address")
    private String address;

    @Column(name = "nationality")
    private String Nationality;

    @ManyToMany(mappedBy = "candidates")
    private Set<Skill> skills = new HashSet<>();

}
