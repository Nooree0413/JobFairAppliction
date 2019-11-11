package com.elca.jobfairmanagementsystem.repository;

import com.elca.jobfairmanagementsystem.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Long, Job> {
}
