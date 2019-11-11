package com.elca.jobfairmanagementsystem.repository;

import com.elca.jobfairmanagementsystem.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience,Long> {
}
