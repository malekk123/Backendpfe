package com.pfe.campagne.service;

import com.pfe.campagne.model.Campagne;
import com.pfe.campagne.model.Candidatexam;

import java.util.List;
import java.util.Set;

public interface CandidatexamService {

    public Candidatexam addexamCandidat(Candidatexam candidatexam);

    public  Candidatexam updateCandidat(Candidatexam candidatexam);

    public Candidatexam getCandidatexam(Long idcandidat);

    public List<Candidatexam> getcandidatexamByexam(Long idExam);

    public List<Candidatexam> evaluercandidats(Long idExam);
    public Candidatexam updatetimer(Candidatexam candidatexam,float timer);
    public Candidatexam getCandidatexamByusernameetcandidat(Long idExam,Integer idCandidat);

    public Candidatexam getcandidatbytoken(String token);
    public void deleteCandidatexam(Long idcandidat);
}
