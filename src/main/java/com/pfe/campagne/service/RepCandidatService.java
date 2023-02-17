package com.pfe.campagne.service;

import com.pfe.campagne.model.Candidatexam;
import com.pfe.campagne.model.Question;
import com.pfe.campagne.model.RepCandidat;

import java.util.List;
import java.util.Optional;

public interface RepCandidatService {

    public RepCandidat addReponse(RepCandidat repCandidat);

    public List<RepCandidat> addAllreponse(List<RepCandidat> repCandidats, Question question, Candidatexam candidatexam);

    public RepCandidat updateReponse(RepCandidat repCandidat);

    public List<RepCandidat> getReponse();

    public Optional<RepCandidat> getReponse(Integer idreponse);

    public List<RepCandidat> getReponseQuestion(Question question);



    public void deleteReponse(Integer idreponse);
}
