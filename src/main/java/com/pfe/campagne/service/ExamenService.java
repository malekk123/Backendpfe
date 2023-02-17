package com.pfe.campagne.service;

import com.pfe.campagne.model.Campagne;

import com.pfe.campagne.model.Category;
import com.pfe.campagne.model.Examen;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ExamenService {

    public Examen addExamen(Examen examen);

    public Examen updateExamen(Examen examen);

    public Set<Examen> getExamens();

    public Examen getExamen(Long examenId);

    public void deleteExamen(Long examenId);

    public List<Examen> findAllById(Campagne campagne);

    public Examen assignquestiontoexam(Long examId,Long quesId);

    //public List <Examen> getExamensofCampagne(Campagne campagne);
}
