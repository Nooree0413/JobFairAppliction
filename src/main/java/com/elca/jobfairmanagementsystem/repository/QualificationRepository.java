package com.elca.jobfairmanagementsystem.repository;

import com.elca.jobfairmanagementsystem.entity.Experience;
import com.elca.jobfairmanagementsystem.entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification,Long> {
//    @Query("SELECT a FROM Qualification a WHERE a.candidate.candidateId =:candidateId")
//    List<Qualification> findByCandidateId(long candidateId);
}
