package com.elca.jobfairmanagementsystem.repository;

import com.elca.jobfairmanagementsystem.entity.VenueJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VenueJobRepository extends JpaRepository<VenueJob,Long>{
    @Query("SELECT a FROM VenueJob a WHERE a.venue.venueId =:venueId")
    List<VenueJob> findByVenue (long venueId);
}
