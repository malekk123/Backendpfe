package com.pfe.campagne.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity(name = "repcandidat")
public class RepCandidat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer repc;

    private String repcand;

    private Integer scoreparq;

    private Integer indice;


    @ManyToOne
    @JsonIgnore
    private Candidatexam candidatexam;


    @ManyToOne
    @JsonIgnore
    private Question question;


}
