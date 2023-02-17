package com.pfe.campagne.service;

import com.pfe.campagne.model.Candidat;
import com.pfe.campagne.model.Candidatexam;

import java.util.List;

public interface CandidatService {

    public Candidat CreateNewCandidat (Candidat candidat)throws Exception;

    public Candidat findBytoken(String token);

    public void envoiMail(Candidat candidat)throws Exception;

    public Boolean confirmToken(String token);

    public void delete(Candidat candidat);

    public Candidat findCandidatByid(Integer idCandidat);

    public Candidat findCandidatByEmail(String email);

    public Candidat supprimeToken(String token);

    public Candidat findCandidatBycandexam(Long idcandidat);

    public List<Candidat> findAllCandidatbycadex(List<Candidatexam> candidatexamList);

    public Candidat generateToken(Candidat candidat);
}
