package com.elca.jobfairmanagementsystem.repository;

import com.elca.jobfairmanagementsystem.entity.VenueJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VenueJobRepository extends JpaRepository<VenueJob,Long>{
    @Query("SELECT a FROM VenueJob a WHERE a.venue.venueId =:venueId")
    List<VenueJob> findByVenue (long venueId);

    @Query("SELECT a FROM VenueJob a WHERE a.venue.venueId =:venueId AND a.job.category =:category")
    List<VenueJob> findByVenueIdAndCategory (long venueId,String category);

    @Query("SELECT a FROM VenueJob a WHERE a.job.level =:level")
    List<VenueJob> findByLevel (String level);

    @Query("SELECT a FROM VenueJob a WHERE a.job.title LIKE CONCAT('%',:title,'%')")
    List<VenueJob> findByTitle (String title);

    @Query("SELECT a FROM VenueJob a WHERE a.venue.venueId =:venueId AND a.job.jobId =:jobId")
    VenueJob findByVenueIdAndJobId (long venueId,long jobId);
}
