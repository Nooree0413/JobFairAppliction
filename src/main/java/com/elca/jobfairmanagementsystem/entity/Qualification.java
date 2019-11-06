package com.elca.jobfairmanagementsystem.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author koo
 */

@Getter
@Setter
@Entity
@Table(name = "qualification")
public class Qualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="qualification_id")
    private Long qualificationId;

    @Column
    private String title;

    @Column
    private String result;

    @Column
    private String institution;

    @Column(name ="date_from")
    @Temporal(TemporalType.DATE)
    private Date dateFrom;

    @Column(name ="date_to")
    @Temporal(TemporalType.DATE)
    private Date dateTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidateId")
    private Candidate candidate;


}
