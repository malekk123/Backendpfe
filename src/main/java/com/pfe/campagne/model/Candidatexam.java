package com.pfe.campagne.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "candidatexam")
public class Candidatexam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idcandidat;

    private  Integer notetotal;

    private String  tag ;

    private Integer numcorrectanswer;

    private Integer numquestionattempted;

    private  Float timer;
    @ManyToOne
    private Examen examen;

    @OneToOne
    @JsonIgnore
    private Candidat candidat;

    @OneToMany(mappedBy = "candidatexam",cascade = CascadeType.ALL)//,fetch = FetchType.EAGER
    private Set<RepCandidat> repCandidats = new HashSet<>();



}
