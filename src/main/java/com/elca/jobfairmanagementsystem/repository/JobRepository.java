package com.elca.jobfairmanagementsystem.repository;

import com.elca.jobfairmanagementsystem.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByCategory (String category);
}
