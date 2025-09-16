package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.dto.ProjectDto;
import com.portfolio.my_portfolio_backend.model.Project;

import java.util.List;

public interface IProjectService {
    List<Project> findAll();
    Project save(Project project);
}
