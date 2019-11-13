package com.elca.jobfairmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elca.jobfairmanagementsystem.entity.Skill;

/**
 *
 * @author ghr
 */
@Repository
public interface SkillRepository extends JpaRepository<Skill ,Long > {


}
