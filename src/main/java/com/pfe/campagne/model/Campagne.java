package com.pfe.campagne.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "campagne")
public class Campagne implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long campid;

    private  String nomCampagne;

    private Date dateDebut;

    private  Date dateFin;

    private Boolean active;

    private Boolean archived;

    @OneToMany(mappedBy = "campagne",fetch=FetchType.EAGER,cascade = CascadeType.ALL)//(mappedBy = "examen")   //(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Examen> examens = new HashSet<>();


    @ManyToOne//(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    private Entreprise entreprise;
  /*  public void archivercampagne(Boolean active,Boolean archived){

        //LocalDate todaysDate = LocalDate.now();
        //if (dateFin.compareTo(todaysDate) )
        setActive(false);
        setArchived(true);
    }*/
}
