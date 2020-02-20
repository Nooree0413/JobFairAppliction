package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.QualificationDto;
import com.elca.jobfairmanagementsystem.entity.Qualification;
import com.elca.jobfairmanagementsystem.exception.ErrorMessages;
import com.elca.jobfairmanagementsystem.exception.QualificationNotFoundException;
import com.elca.jobfairmanagementsystem.mapper.QualificationMapper;
import com.elca.jobfairmanagementsystem.repository.QualificationRepository;
import com.elca.jobfairmanagementsystem.service.QualificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class QualificationServiceImpl implements QualificationService {

    private final QualificationRepository qualificationRepo;
    private final QualificationMapper qualificationMapper;

    public QualificationServiceImpl(QualificationRepository qualificationRepo, QualificationMapper qualificationMapper) {
        this.qualificationRepo = qualificationRepo;
        this.qualificationMapper = qualificationMapper;
    }

    @Override
    public List<QualificationDto> findAllQualifications() throws QualificationNotFoundException {
        List<Qualification> qualifications = qualificationRepo.findAll();
        if (qualifications.size() != 0) {
            return qualifications.stream()
                    .map(qualificationMapper::qualificationEntityToDto)
                    .collect(Collectors.toList());
        } else {
            throw new QualificationNotFoundException(ErrorMessages.NO_QUALIFICATION_AVAILABLE.toString());
        }
    }

    @Override
    public void saveQualification(QualificationDto qualificationDto) {
        Qualification qualification = qualificationMapper.qualificationDtoToEntity(qualificationDto);
        qualificationRepo.save(qualification);
    }

    @Override
    public void deleteQualification(Long qualificationId) throws QualificationNotFoundException {
        var qualification = findByQualificationId(qualificationId);
        if (qualification != null) {
            qualificationRepo.deleteById(qualificationId);
        } else {
            throw new QualificationNotFoundException(ErrorMessages.QUALIFICATION_NOT_FOUND.toString());
        }
    }

    @Override
    public void updateQualification(QualificationDto qualificationDto) throws QualificationNotFoundException {
        var updateQualification = findByQualificationId(qualificationDto.getQualificationId());
        if (updateQualification != null) {
            updateQualification.setCandidateId(qualificationDto.getCandidateId());
            updateQualification.setTitle(qualificationDto.getTitle());
            updateQualification.setInstitution(qualificationDto.getInstitution());
            qualificationRepo.save(qualificationMapper.qualificationDtoToEntity(updateQualification));
        } else {
            throw new QualificationNotFoundException(ErrorMessages.NO_QUALIFICATION_AVAILABLE.toString());
        }
    }

    @Override
    public QualificationDto findByQualificationId(Long qualificationId) throws QualificationNotFoundException {
        Optional<Qualification> optionalQualification = qualificationRepo.findById(qualificationId);
        var qualification = optionalQualification.orElseThrow(() -> new QualificationNotFoundException(ErrorMessages.QUALIFICATION_NOT_FOUND.toString()));
        return qualificationMapper.qualificationEntityToDto(qualification);
    }
}
