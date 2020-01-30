package com.elca.jobfairmanagementsystem.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DownloadDto {
    String name;
    byte[] file;
}
