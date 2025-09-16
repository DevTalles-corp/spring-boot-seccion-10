package com.portfolio.my_portfolio_backend.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {
    private Long id; // Clave primaria


    private String firstName;

    private String lastName;

    private String title; // Ej: "Full Stack Developer"

    private String profileDescription; // El texto largo del "Who am I?"

    private String profileImageUrl; // URL o ruta a la imagen de perfil

    private Integer yearsOfExperience;

    private String email;

    private String phone;

    private String linkedinUrl;

    private String githubUrl;
}
