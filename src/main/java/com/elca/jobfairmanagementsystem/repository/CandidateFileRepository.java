package com.elca.jobfairmanagementsystem.repository;

import com.elca.jobfairmanagementsystem.entity.CandidateFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateFileRepository extends JpaRepository<CandidateFile, Long> {
    CandidateFile findByFileName(String fileName);
}
