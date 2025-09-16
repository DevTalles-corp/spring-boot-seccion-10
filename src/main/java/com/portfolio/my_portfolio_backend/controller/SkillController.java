package com.portfolio.my_portfolio_backend.controller;

import com.portfolio.my_portfolio_backend.dto.SkillDto;
import com.portfolio.my_portfolio_backend.mapper.SkillMapper;
import com.portfolio.my_portfolio_backend.model.Skill;
import com.portfolio.my_portfolio_backend.service.ISkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/skills")
@RequiredArgsConstructor
public class SkillController {

    private final ISkillService skillService;

    @GetMapping
    public String listSkills(Model model){

        List<Skill> skills = skillService.findAll();

        List<SkillDto> skillDtos = skills.stream()
                        .map(SkillMapper::toDto)
                                .toList();

        model.addAttribute("skills", skillDtos);
        return "skills/list-skills";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model){
        model.addAttribute("skillDto", new SkillDto());
        return "skills/form-skill";
    }

    @PostMapping("/save")
    public String saveSkill(@Valid @ModelAttribute("skillDto") SkillDto skillDto, BindingResult result){
        if (result.hasErrors()){
            return "skills/form-skill";
        }

        try {
            Skill skill = SkillMapper.toEntity(skillDto);
            skillService.save(skill);
            return "redirect:/skills";
        }catch (Exception e){
            return "error-page";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        Optional<Skill> skillOptional = skillService.findById(id);

        if (skillOptional.isPresent()){
            SkillDto skillDto = SkillMapper.toDto(skillOptional.get());
            model.addAttribute("skillDto", skillDto);
            return "skills/form-skill";
        }else {
            model.addAttribute("errorMessage", "Habilidad no encontrada con ID: " + id);
            return "redirect:skills";
        }
    }

    @GetMapping("/personal/{personalInfoId}")
    public String listSkillsByPersonalInfoId(@PathVariable Long personalInfoId, Model model){
        List<Skill> skills = skillService.findSkillsByPersonalInfoId(personalInfoId);

        List<SkillDto> skillDtos = skills.stream().map(SkillMapper::toDto).toList();
        model.addAttribute("skills", skillDtos);
        return "skills/list-skills";
    }

    @PostMapping("/delete/{id}")
    public String deleteSkill(@PathVariable Long id, RedirectAttributes redirectAttributes){
        try{
            skillService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Habilidad eliminada con éxito!");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la habilidad: " + e.getMessage());
        }

        return "redirect:/skills";
    }
















}
