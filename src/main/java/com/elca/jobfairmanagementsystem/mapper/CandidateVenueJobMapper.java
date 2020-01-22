package com.elca.jobfairmanagementsystem.mapper;

import com.elca.jobfairmanagementsystem.dto.CandidateFileDto;
import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobDto;
import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobSaveDto;
import com.elca.jobfairmanagementsystem.entity.CandidateFile;
import com.elca.jobfairmanagementsystem.entity.CandidateVenueJob;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {VenueMapper.class})
public interface CandidateVenueJobMapper {
    @Mapping(target = "venueJob.venueJobId", source = "candidateVenueJobDto.venueJob.venueJobId")
    @Mapping(target = "candidate.candidateId", source = "candidateVenueJobDto.candidate.candidateId")
    CandidateVenueJob candidateVenueJobDtoToEntity (CandidateVenueJobDto candidateVenueJobDto);

    @Mapping(target = "venueJob.venueJobId", source = "venueJob.venueJobId")
    @Mapping(target = "candidate.candidateId", source = "candidate.candidateId")
    @Mapping(target = "candidate.experienceDtos", source = "candidate.experiences")
    @Mapping(target = "candidate.qualificationDtos", source = "candidate.qualifications")
    @Mapping(target = "candidate.candidateSkillDtos", source = "candidate.candidateSkills")
    @Mapping(target = "candidate.candidateScreeningDtos", source = "candidate.candidateScreenings")
    @Mapping(target = "candidate.candidateFileDtos", source = "candidate.candidateFiles")
    CandidateVenueJobDto candidateVenueJobEntityToDto (CandidateVenueJob candidateVenueJob);

    @Mapping(target = "data", ignore = true)
    CandidateFileDto candidateFileEntityToDto(CandidateFile candidateFile);

    @Mapping(target = "candidate.candidateId", source = "candidateVenueJobSaveDto.candidateId")
    @Mapping(target = "venueJob.venueJobId", source = "venueJobId")
    CandidateVenueJob candidateVenueJobSaveDtoToEntity (CandidateVenueJobSaveDto candidateVenueJobSaveDto);

    @Mapping(source = "candidate.candidateId", target = "candidateId")
    @Mapping(source = "venueJob.venueJobId", target = "venueJobId")
    CandidateVenueJobSaveDto entityToCandidateVenueJobSaveDto (CandidateVenueJob candidateVenueJob);

}
