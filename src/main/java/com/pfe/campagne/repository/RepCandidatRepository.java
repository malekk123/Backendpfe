package com.pfe.campagne.repository;

import com.pfe.campagne.model.Question;
import com.pfe.campagne.model.RepCandidat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepCandidatRepository extends JpaRepository<RepCandidat,Integer> {

    List<RepCandidat> findByQuestion(Question question);


}
