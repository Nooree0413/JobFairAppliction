package com.elca.jobfairmanagementsystem.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author bfk
 */

@Getter
@Setter
@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private int skillId;

    @Column(name = "skill_description")
    private String  skillDescription;

    @Column(name = "skill_name")
    private  String skillName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "candidate_skill",
            joinColumns = { @JoinColumn(name = "skill_id") },
            inverseJoinColumns = { @JoinColumn(name = "candidate_id") })
    private Set<Candidate> candidates = new HashSet<>();

}
