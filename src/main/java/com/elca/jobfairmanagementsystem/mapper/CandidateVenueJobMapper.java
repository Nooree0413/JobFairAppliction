package com.elca.jobfairmanagementsystem.mapper;

import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobDto;
import com.elca.jobfairmanagementsystem.entity.CandidateVenueJob;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CandidateVenueJobMapper {
    @Mapping(target = "venueJob.venueJobId", source = "candidateVenueJobDto.venueJob.venueJobId")
    @Mapping(target = "candidate.candidateId", source = "candidateVenueJobDto.candidate.candidateId")
    CandidateVenueJob candidateVenueJobDtoToEntity (CandidateVenueJobDto candidateVenueJobDto);

    @Mapping(target = "venueJob.venueJobId", source = "candidateVenueJob.venueJob.venueJobId")
    @Mapping(target = "candidate.candidateId", source = "candidateVenueJob.candidate.candidateId")
    CandidateVenueJobDto candidateVenueJobEntityToDto (CandidateVenueJob candidateVenueJob);
}
