package com.portfolio.my_portfolio_backend.dto;

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
public class PersonalInfoDto {
    private Long id; // Clave primaria

    @NotBlank(message = "El nombre no puede estar vacío")
    private String firstName;
    @NotBlank(message = "El apellido no puede estar vacío")
    private String lastName;
    @NotBlank(message = "El título no puede estar vacío")
    private String title; // Ej: "Full Stack Developer"
    @NotBlank(message = "La descripción del perfil no puede estar vacía")
    private String profileDescription; // El texto largo del "Who am I?"
    @NotBlank(message = "La imagen de perfil no puede estar vacía")
    private String profileImageUrl; // URL o ruta a la imagen de perfil
    @Min(value = 0, message = "Los años de experiencia no pueden ser negativos")
    private Integer yearsOfExperience;
    @Email(message = "El email no es válido")
    @NotBlank(message = "El email no puede estar vacío")
    private String email;
    @NotBlank(message = "El teléfono no puede estar vacío")
    private String phone;
    @URL(message = "La URL de LinkedIn no es válida")
    @NotBlank(message = "LinkedIn es una red obligatoria")
    private String linkedinUrl;
    @URL(message = "La URL de GitHub no es válida")
    @NotBlank(message = "GitHub es una red obligatoria")
    private String githubUrl;
}
