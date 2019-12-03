package com.elca.jobfairmanagementsystem.repository;

import com.elca.jobfairmanagementsystem.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience,Long> {
    @Query("SELECT a FROM Experience a WHERE a.candidate.candidateId =:candidateId")
    List<Experience> findByCandidateId(long candidateId);
}
