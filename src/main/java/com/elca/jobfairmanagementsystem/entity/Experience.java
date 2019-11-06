package com.elca.jobfairmanagementsystem.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

/**
 *
 * @author koo
 */
@Getter
@Setter
@Entity
@Table(name = "experience")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="experience_id")
    private Long experienceId;

    @Column(name ="company_name")
    private String companyName;

    @Column
    private String position;

    @Column
    private String duration;

    @Column(name = "leaving_reason")
    private String leavingReason;

}
