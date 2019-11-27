package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.QualificationDto;
import com.elca.jobfairmanagementsystem.entity.Qualification;
import com.elca.jobfairmanagementsystem.exception.QualificationNotFoundException;

import java.util.List;

public interface QualificationService {

    List<QualificationDto> findAllQualifications() throws QualificationNotFoundException;

    QualificationDto findByQualificationId(Long qualificationId) throws QualificationNotFoundException;

    void saveQualification(QualificationDto qualificationDto);

    void deleteQualification(Long qualificationId) throws QualificationNotFoundException;

    void updateQualification(QualificationDto qualificationDto) throws QualificationNotFoundException;

}
