package com.portfolio.my_portfolio_backend.rest;

import com.portfolio.my_portfolio_backend.model.Experience;
import com.portfolio.my_portfolio_backend.service.IExperienceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/experience")
public class ExperienceRestController {

    private final IExperienceService experienceService;

    public ExperienceRestController(IExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping
    public List<Experience> findAll() {
        return experienceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Experience> findById(@PathVariable Long id) {
        Optional<Experience> experience = experienceService.findById(id);
        if(experience.isPresent()){
            return new ResponseEntity<>(experience.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Experience save(@RequestBody Experience experience) {
        return experienceService.save(experience);
    }

    @PutMapping("/{id}")
    public Experience update(@PathVariable Long id, @RequestBody Experience experience) {
        experience.setId(id);
        return experienceService.save(experience);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        experienceService.deleteById(id);
    }
}
