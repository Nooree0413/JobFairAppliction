package com.elca.jobfairmanagementsystem.mapper;

import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobDto;
import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobSaveDto;
import com.elca.jobfairmanagementsystem.entity.CandidateVenueJob;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {VenueMapper.class})
public interface CandidateVenueJobMapper {
    @Mapping(target = "venueJob.venueJobId", source = "candidateVenueJobDto.venueJob.venueJobId")
    @Mapping(target = "candidate.candidateId", source = "candidateVenueJobDto.candidate.candidateId")
    CandidateVenueJob candidateVenueJobDtoToEntity (CandidateVenueJobDto candidateVenueJobDto);

    @Mapping(target = "venueJob.venueJobId", source = "candidateVenueJob.venueJob.venueJobId")
    @Mapping(target = "candidate.candidateId", source = "candidateVenueJob.candidate.candidateId")
    @Mapping(target = "candidate.experienceDtos", source = "candidateVenueJob.candidate.experiences")
    @Mapping(target = "candidate.qualificationDtos", source = "candidateVenueJob.candidate.qualifications")
    @Mapping(target = "candidate.candidateSkillDtos", source = "candidateVenueJob.candidate.candidateSkills")
    @Mapping(target = "candidate.candidateScreeningDtos", source = "candidateVenueJob.candidate.candidateScreenings")
    CandidateVenueJobDto candidateVenueJobEntityToDto (CandidateVenueJob candidateVenueJob);

    @Mapping(target = "candidate.candidateId", source = "candidateVenueJobSaveDto.candidateId")
    @Mapping(target = "venueJob.venueJobId", source = "venueJobId")
    @Mapping(target = "venueJob.venue.venueId", source = "venueId.venueId")
    CandidateVenueJob candidateVenueJobSaveDtoToEntity (CandidateVenueJobSaveDto candidateVenueJobSaveDto);

    @Mapping(source = "candidate.candidateId", target = "candidateId")
    @Mapping(source = "venueJob.venueJobId", target = "venueJobId")
    @Mapping(source = "venueJob.venue", target = "venueId")
    CandidateVenueJobSaveDto entityToCandidateVenueJobSaveDto (CandidateVenueJob candidateVenueJob);

}
