package com.elca.jobfairmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elca.jobfairmanagementsystem.entity.Job;

/**
 *
 * @author ghr
 */
@Repository
public interface JobRepository extends JpaRepository<Job , Long> {
}
