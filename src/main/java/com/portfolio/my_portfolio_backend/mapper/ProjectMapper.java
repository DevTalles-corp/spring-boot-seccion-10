package com.portfolio.my_portfolio_backend.mapper;

import com.portfolio.my_portfolio_backend.dto.ProjectDto;
import com.portfolio.my_portfolio_backend.model.Project;

public class ProjectMapper {

    public static ProjectDto toDto(Project project){
        if(project==null){
            return null;
        }
        ProjectDto projectDto = new ProjectDto();

        projectDto.setId(project.getId());
        projectDto.setTitle(project.getTitle());
        projectDto.setDescription(project.getDescription());
        projectDto.setImageUrl(project.getImageUrl());
        projectDto.setProjectUrl(project.getProjectUrl());
        projectDto.setPersonalInfoId(project.getPersonalInfoId());

        return projectDto;
    }

    public static Project toEntity(ProjectDto dto){
        if (dto == null) {
            return null;
        }
        Project project = new Project();

        project.setId(dto.getId());
        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setImageUrl(dto.getImageUrl());
        project.setProjectUrl(dto.getProjectUrl());
        project.setPersonalInfoId(dto.getPersonalInfoId());

        return project;
    }













}
