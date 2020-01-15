package com.elca.jobfairmanagementsystem.repository;

import com.elca.jobfairmanagementsystem.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {
    @Query("SELECT a FROM Venue a WHERE a.active = :active")
    List<Venue> findVenueByActive(boolean active);
}
