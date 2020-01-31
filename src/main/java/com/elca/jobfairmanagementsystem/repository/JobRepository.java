package com.elca.jobfairmanagementsystem.repository;

import com.elca.jobfairmanagementsystem.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("SELECT a FROM Job a WHERE a.title LIKE CONCAT('%',:title,'%')")
    List<Job> findByTitle (String title);

    @Query("SELECT j FROM Job j WHERE j.level =:level")
    List<Job> findByLevel (String level);

    List<Job> findByCategory (String category);

    @Query("SELECT count(a) FROM Job a")
    Integer findCountOfAllJobs();
}
