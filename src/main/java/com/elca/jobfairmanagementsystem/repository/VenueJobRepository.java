package com.elca.jobfairmanagementsystem.repository;

import com.elca.jobfairmanagementsystem.entity.VenueJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VenueJobRepository extends JpaRepository<VenueJob,Long>{
    @Query("SELECT a FROM VenueJob a WHERE a.venue.venueId =:venueId")
    Page<VenueJob> findByVenue (long venueId, Pageable pageable);

    @Query("SELECT a FROM VenueJob a WHERE a.venue.venueId =:venueId AND a.job.category =:category")
    List<VenueJob> findByVenueIdAndCategory (long venueId,String category);

    @Query("SELECT a FROM VenueJob a WHERE a.job.level =:level AND a.venue.venueId=:venueId")
    List<VenueJob> findByLevel (long venueId,String level);

    @Query("SELECT a FROM VenueJob a WHERE a.job.title LIKE CONCAT('%',:title,'%') AND a.venue.venueId=:venueId")
    List<VenueJob> findByTitle (long venueId,String title);

    @Query("SELECT a FROM VenueJob a WHERE a.job.title LIKE CONCAT('%',:title,'%') AND a.venue.venueId=:venueId AND a.job.category = :category")
    List<VenueJob> findByTitleAndCategory (long venueId,String title,String category);

    @Query("SELECT a FROM VenueJob a WHERE a.venue.venueId =:venueId AND a.job.jobId =:jobId")
    VenueJob findByVenueIdAndJobId (long venueId,long jobId);

    @Transactional
    @Modifying
    @Query("DELETE FROM VenueJob a WHERE a.venue.venueId =:venueId AND a.job.jobId =:jobId")
    void deleteVenueJobByVenueIdAndJobId(long venueId,long jobId);
}
