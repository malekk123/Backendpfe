package com.pfe.campagne.service;



import com.pfe.campagne.model.Question;
import com.pfe.campagne.model.Reponse;

import java.util.List;
import java.util.Set;

public interface ReponseService {
    public Reponse addReponse(Reponse reponse);

    public List<Reponse> addAllreponse(List<Reponse> reponses);

    public Reponse updateReponse(Reponse reponse);

    public List<Reponse> getReponse();

    public Reponse getReponse(Integer idreponse);

    public List<Reponse> getReponseQuestion(Question question);

    public List<Reponse> addAllreponseid(List<Reponse> reponses,Question question);

    public void deleteReponse(Integer idreponse);
}
