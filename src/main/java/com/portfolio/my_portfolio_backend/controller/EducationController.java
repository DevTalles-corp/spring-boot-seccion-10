package com.portfolio.my_portfolio_backend.controller;

import com.portfolio.my_portfolio_backend.dto.EducationDto;
import com.portfolio.my_portfolio_backend.mapper.EducationMapper;
import com.portfolio.my_portfolio_backend.model.Education;
import com.portfolio.my_portfolio_backend.service.IEducationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/education")
@RequiredArgsConstructor
public class EducationController {

    private final IEducationService educationService;

    @GetMapping
    public String listEducation(Model model) {
        List<Education> educationList = educationService.findAll();
        List<EducationDto> educationDtos = educationList.stream()
                .map(EducationMapper::toDto)
                .collect(Collectors.toList());
        model.addAttribute("educationList", educationDtos);
        return "education/list-education";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        EducationDto newEducationDto = new EducationDto();
        newEducationDto.setStartDate(LocalDate.now()); // <-- Añadir esta línea
        model.addAttribute("educationDto", newEducationDto);
        return "education/form-education";
    }

    @PostMapping("/save")
    public String saveEducation(@Valid @ModelAttribute("educationDto") EducationDto educationDto,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "education/form-education";
        }

        try {
            Education education = EducationMapper.toEntity(educationDto);
            educationService.save(education);
            redirectAttributes.addFlashAttribute("message", "Educación guardada con éxito en Mar del Plata!");
            return "redirect:/education";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar la educación: " + e.getMessage());
            return "redirect:/education";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Education> educationOptional = educationService.findById(id);
        if (educationOptional.isPresent()) {
            EducationDto educationDto = EducationMapper.toDto(educationOptional.get());
            model.addAttribute("educationDto", educationDto);
            return "education/form-education";
        } else {
            model.addAttribute("errorMessage", "Educación no encontrada con ID: " + id);
            return "redirect:/education";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteEducation(@PathVariable Long id,
                                  RedirectAttributes redirectAttributes) {
        try {
            educationService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Educación eliminada con éxito de tu portfolio en Mar del Plata!");
            return "redirect:/education";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la educación: " + e.getMessage());
            return "redirect:/education";
        }
    }

    @GetMapping("/personal/{personalInfoId}")
    public String listEducationByPersonalInfo(@PathVariable Long personalInfoId, Model model) {
        List<Education> educationList = educationService.findEducationByPersonalInfoId(personalInfoId);
        List<EducationDto> educationDtos = educationList.stream()
                .map(EducationMapper::toDto)
                .collect(Collectors.toList());
        model.addAttribute("educationList", educationDtos);
        return "education/list-education";
    }
}