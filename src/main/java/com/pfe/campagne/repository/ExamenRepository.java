package com.pfe.campagne.repository;

import com.pfe.campagne.model.Campagne;
import com.pfe.campagne.model.Examen;
import com.pfe.campagne.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ExamenRepository extends JpaRepository<Examen,Long> {

    public Examen findByIdExam(Long idexam);

    public List<Examen> findAllByCampagne(Campagne campagne);

    public List<Examen> findByCampagne(Campagne campagne);

    public List<Question> getByIdExam(Long Idexam);
   // public  List<Examen>

}
