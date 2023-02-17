package com.pfe.campagne.repository;

import com.pfe.campagne.model.Question;
import com.pfe.campagne.model.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReponseRepository extends JpaRepository<Reponse,Integer> {

    public Reponse findByIdreponse(Integer idreponse);

    public Reponse findByEstcorrecteAndAndQuestion(Boolean b,Question question);

    public List<Reponse>findByQuestionAndAndEstcorrecte(Question question,boolean b);

    public List<Reponse> findByQuestion(Question question);



}
