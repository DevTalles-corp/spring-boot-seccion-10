package com.portfolio.my_portfolio_backend.controller;

import com.portfolio.my_portfolio_backend.dto.PersonalInfoDto;
import com.portfolio.my_portfolio_backend.mapper.PersonalInfoMapper;
import com.portfolio.my_portfolio_backend.model.PersonalInfo;
import com.portfolio.my_portfolio_backend.service.IPersonalInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/personal-info")
@RequiredArgsConstructor
public class PersonalInfoController {

    private final IPersonalInfoService personalInfoService;
    private static final Long DEFAULT_PERSONAL_INFO_ID=1L;
    @GetMapping
    public String viewPersonalInfo(Model model){
        return "redirect:/personal-info/edit/" + DEFAULT_PERSONAL_INFO_ID;
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<PersonalInfo> personalInfoOptional = personalInfoService.findById(id);
        if (personalInfoOptional.isPresent()) {
            PersonalInfoDto personalInfoDto = PersonalInfoMapper.toDto(personalInfoOptional.get());
            model.addAttribute("personalInfoDto", personalInfoDto);
            return "personalinfo/form-personal-info";
        } else {
            model.addAttribute("personalInfoDto", new PersonalInfoDto());
            redirectAttributes.addFlashAttribute("error", "No se encontró información personal. Por favor, crea una nueva.");
            return "personalinfo/form-personal-info";
        }
    }


    @PostMapping("/save")
    public String savePersonalInfo(@Valid @ModelAttribute("personalInfoDto") PersonalInfoDto personalInfoDto,
                                   BindingResult result,
                                   RedirectAttributes redirectAttributes) {
        if(result.hasErrors()){
            return "personalinfo/form-personal-info";
        }

        try{
            PersonalInfo personalInfo = PersonalInfoMapper.toEntity(personalInfoDto);
            personalInfoService.save(personalInfo);
            redirectAttributes.addFlashAttribute("message", "Información personal guardada con éxito!");
            return "redirect:/personal-info/edit/" + personalInfo.getId();
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Error al guardar la información personal: " + e.getMessage());
            return "personalinfo/form-personal-info";
        }

    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("personalInfoDto", new PersonalInfoDto());
        return "personalinfo/form-personal-info";
    }








}
