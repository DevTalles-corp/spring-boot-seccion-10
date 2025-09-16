package com.portfolio.my_portfolio_backend.controller;

import com.portfolio.my_portfolio_backend.model.PersonalInfo;
import com.portfolio.my_portfolio_backend.service.IPersonalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PortfolioController {
    private final IPersonalInfoService personalInfoService;

    @GetMapping("/form")
    public String showForm(Model model){
        model.addAttribute("personalInfo", new PersonalInfo());
        return "form";
    }

    @PostMapping("/personal-info-save")
    public String savePersonalInfo(@ModelAttribute("personalInfo") PersonalInfo personalInfo){
        personalInfoService.save(personalInfo);
        return "redirect:/";
    }
}
