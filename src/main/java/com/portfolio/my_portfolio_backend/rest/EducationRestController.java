package com.portfolio.my_portfolio_backend.rest;

import com.portfolio.my_portfolio_backend.model.Education;
import com.portfolio.my_portfolio_backend.service.IEducationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/education")
public class EducationRestController {
    private final IEducationService educationService;

    public EducationRestController(IEducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping
    public List<Education> findAll() {
        return educationService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Education> findById(@PathVariable Long id) {
        return educationService.findById(id);
    }

    @PostMapping
    public Education save(@RequestBody Education education) {
        return educationService.save(education);
    }

    @PutMapping("/{id}")
    public Education update(@PathVariable Long id, @RequestBody Education education) {
        education.setId(id);
        return educationService.save(education);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        educationService.deleteById(id);
    }












}
