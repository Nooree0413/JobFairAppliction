package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

@Data
public class RoleDto {
    private Long roleId;
    private String name;
    private String description;
    private Boolean candidateAdd;
    private Boolean candidateDetail;
    private Boolean candidateList;
    private Boolean home;
    private Boolean jobList;
    private Boolean venuePage;
    private Boolean dashboard;
    private Boolean jobBo;
    private Boolean skillBo;
    private Boolean venueBo;
    private Boolean VenueJobBo;
}
