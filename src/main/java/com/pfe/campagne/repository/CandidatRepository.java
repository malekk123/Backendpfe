package com.pfe.campagne.repository;

import com.pfe.campagne.model.Candidat;
import com.pfe.campagne.model.Candidatexam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatRepository extends JpaRepository<Candidat,Integer> {

    Candidat findByToken(String token);

    Candidat findByEmail (String email);

    Candidat findByIdCandidat(Integer idCandidat);

    Candidat findByCandidatexam(Candidatexam candidatexam);

}
