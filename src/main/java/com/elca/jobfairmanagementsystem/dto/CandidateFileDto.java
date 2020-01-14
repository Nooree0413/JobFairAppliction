package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

@Data
public class CandidateFileDto {
    private Long candidateFileId;
    private String fileName;
    private String fileType;
    private byte[] data;
    private Long candidateId;
}
