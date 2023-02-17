package com.pfe.campagne.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Entreprise implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long entrpriseid;

    private  String noment;

    private String email;

    private Integer numtele;

    private  String  siteweb;

    private Integer fax;

    private String adresse;

    private  String logo;

    private String SignatureMail;

    @Column(length = 5000)
    private String desccontenumail;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "entreprise")//,fetch = FetchType.EAGER
    @JsonIgnore
    private Set <User> users = new LinkedHashSet<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "entreprise")//,fetch = FetchType.EAGER
    @JsonIgnore
    private Set <Question> questions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "entreprise",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private Set <Campagne> campagnes = new LinkedHashSet<>();

}
