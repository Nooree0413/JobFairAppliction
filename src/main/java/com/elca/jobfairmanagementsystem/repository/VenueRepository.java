package com.elca.jobfairmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elca.jobfairmanagementsystem.entity.Venue;

/**
 *
 * @author ghr
 */
@Repository
public interface VenueRepository extends JpaRepository<Venue,Long> {
}
