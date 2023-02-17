package com.pfe.campagne.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "question")
public class Question implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long quesId;
    @Column(length = 5000)
    private String content;

    private String image;

    private Integer point;

    private String type;


  //  private String option1;
  //  private String option2;
   // private String option3;
   // private String option4;

    //private String answer;

//    private Integer nbdereponse;


  //  private Integer nbdereponsecorrecte;

    private Boolean bibad=true;

    private Boolean bibus =false;

    @OneToMany(mappedBy = "question",fetch=FetchType.EAGER,cascade = CascadeType.ALL)

    private Set<Reponse> reponses =new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)//,cascade = CascadeType.ALL)
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.EAGER)
    private Entreprise entreprise;

    /*@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "question")
    @JsonIgnore
    private Set<Candidatexam> candidatexams = new HashSet<>();
*/
    @ManyToOne(fetch = FetchType.EAGER)
    private Examen examen;

    @OneToMany(mappedBy = "question",fetch = FetchType.EAGER)
    private Set<RepCandidat> repCandidats =new HashSet<>();

  /*  @JsonIgnore
    @ManyToMany//(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    //@JoinTable(name = "candidatexam")//,joinColumns = @JoinColumn(name = "examen_idexam"), inverseJoinColumns = @JoinColumn(name = "questions_questid"))
    private Set <Examen> examen = new HashSet<>();
*/
    public Question() {
    }


    public void setType(String type) {
        this.type = type;
    }

    public String  getType() {
        return type;
    }

  /*  public void setNbdereponsecorrecte(Integer nbdereponsecorrecte) {
        this.nbdereponsecorrecte = nbdereponsecorrecte;
    }

    public Integer getNbdereponsecorrecte() {
        return nbdereponsecorrecte;
    }


    public void setNbdereponse(Integer nbdereponse) {
        this.nbdereponse = nbdereponse;
    }

    public Integer getNbdereponse() {
        return nbdereponse;
    }
*/
    public void setReponses(Set<Reponse> reponses) {
        this.reponses = reponses;
    }

    public Set<Reponse> getReponses() {
        return reponses;
    }


    public Boolean getBibad(){
        return bibad;
    }

    public void setBibad(Boolean bibad) {
        this.bibad = bibad;
    }

    public Boolean getBibus(){
        return bibus;
    }

    public void setBibus(Boolean bibus) {
        this.bibus = bibus;
    }

    public Integer getPoint()
    {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Long getQuesId() {
        return quesId;
    }

    public void setQuesId(Long quesId) {
        this.quesId = quesId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public Set<RepCandidat> getRepCandidats() {
        return repCandidats;
    }

    public void setRepCandidats(Set<RepCandidat> repCandidats) {
        this.repCandidats = repCandidats;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    /*public Set<Candidatexam> getCandidatexams() {
        return candidatexams;
    }*/

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setExamen(Examen examen){
        this.examen= examen;
    }
   public Examen getExamen() {
        return examen;
    }
}
