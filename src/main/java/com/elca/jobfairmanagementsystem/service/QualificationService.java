package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.QualificationDto;
import com.elca.jobfairmanagementsystem.entity.Qualification;
import java.util.List;

public interface QualificationService {

    List<QualificationDto> searchAllQualifications();

    QualificationDto findByQualificationId(Long qualificationId);

    void saveQualification(QualificationDto qualificationDto);

    void deleteQualification(Long qualificationId);

    void updateQualification(QualificationDto qualificationDto);

}
