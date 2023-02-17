package com.pfe.campagne.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TestDTO {

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;
}
