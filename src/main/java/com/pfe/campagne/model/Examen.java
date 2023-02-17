package com.pfe.campagne.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "examen")
public class Examen implements Serializable {

    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long idExam;
    private String nomexam;
    private Integer duree;

    @ManyToOne //(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    private Campagne campagne;

    /*@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "examen")//, orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
  //  private Set<Candidatexam> candidatexams = new HashSet<>();
*/

    @OneToMany(mappedBy = "examen",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private  Set<Question> questions =new LinkedHashSet<>();

/*    @ManyToMany//(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
  //  @JoinTable(name = "candidatexam")//,joinColumns = @JoinColumn(name = "examen_idexam"),
    //inverseJoinColumns = @JoinColumn(name = "questions_questid"))
    private Set<Question> question = new HashSet<>();
*/

    @OneToMany(mappedBy = "examen",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Candidatexam> candidatexams = new LinkedHashSet<>();
}
