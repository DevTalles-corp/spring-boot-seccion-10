package com.portfolio.my_portfolio_backend.controller;

import com.portfolio.my_portfolio_backend.dto.ProjectDto;
import com.portfolio.my_portfolio_backend.mapper.ProjectMapper;
import com.portfolio.my_portfolio_backend.model.Project;
import com.portfolio.my_portfolio_backend.service.FileStorageService;
import com.portfolio.my_portfolio_backend.service.IProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final IProjectService projectService;
    private final FileStorageService fileStorageService;

    @GetMapping
    public String getAll(Model model){
        List<ProjectDto> projects = projectService.findAll().stream()
                        .map(ProjectMapper::toDto)
                        .toList();
        model.addAttribute("projects", projects);
        return "projects/list";
    }


    @GetMapping("/new-project")
    public String showForm(Model model) {
        model.addAttribute("projectDto", new ProjectDto());
        return "projects/form-project"; // o el nombre que hayas elegido
    }


    @PostMapping("/save")
    public String saveProject(@Valid @ModelAttribute("projectDto") ProjectDto projectDto, BindingResult result,
                                @RequestParam("file") MultipartFile file
    ){
        if(file.isEmpty()){
            result.rejectValue("imageUrl", "file.required", "La imagen del proyecto es obligatoria.");
        }

        if(result.hasErrors()){
            return "projects/form-project";
        }

        try {
            String imageUrl = fileStorageService.storeFile(file);

            projectDto.setImageUrl(imageUrl);

            Project project = ProjectMapper.toEntity(projectDto);

            projectService.save(project);

            return "redirect:/projects";
        } catch (IOException e) {
            return "error-page";
        }
    }














}
