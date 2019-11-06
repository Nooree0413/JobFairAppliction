package com.elca.jobfairmanagementsystem.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

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
    private Long candidateId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Size(max = 50)
    @Email
    private String email;

    @Column
    private int mobileNo;

    @Column
    private int phoneNo;
    @Column
    private String address;
    @Column
    private String Nationality;

}
