package com.pfe.campagne.service.impl;

import com.pfe.campagne.model.Candidat;
import com.pfe.campagne.model.Candidatexam;
import com.pfe.campagne.model.Question;
import com.pfe.campagne.model.RepCandidat;
import com.pfe.campagne.repository.QuestionRepository;
import com.pfe.campagne.repository.RepCandidatRepository;
import com.pfe.campagne.service.RepCandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepCandidatServiceImpl implements RepCandidatService {

    @Autowired
    private RepCandidatRepository repCandidatRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public RepCandidat addReponse(RepCandidat repCandidat) {
        return this.repCandidatRepository.save(repCandidat);
    }

    @Override
    public List<RepCandidat> addAllreponse(List<RepCandidat> repCandidats, Question question, Candidatexam candidatexam) {
        for(RepCandidat rc:repCandidats){
            if(rc!=null) {
                rc.setQuestion(question);
                rc.setCandidatexam(candidatexam);
            }
            repCandidatRepository.save(rc);
        }
      /*  for(RepCandidat rc:repCandidats){
            System.out.println("ajouter reponse r="+ rc +",q="+rc.getQuestion().getQuesId());
            Optional<Question> q =questionRepository.findById(rc.getQuestion().getQuesId());
            if(q !=null && q.isPresent()){
                rc.setQuestion(q.get());
                repCandidatRepository.save(rc);
            }

        }*/
        return repCandidats;
    }

    @Override
    public RepCandidat updateReponse(RepCandidat repCandidat) {
        return this.repCandidatRepository.save(repCandidat);
    }

    @Override
    public List<RepCandidat> getReponse() {
        return this.repCandidatRepository.findAll();
    }

    @Override
    public Optional<RepCandidat> getReponse(Integer idreponse) {
        return this.repCandidatRepository.findById(idreponse);
    }

    @Override
    public List<RepCandidat> getReponseQuestion(Question question) {
        return this.repCandidatRepository.findByQuestion(question);
    }


    @Override
    public void deleteReponse(Integer idreponse) {
        this.repCandidatRepository.deleteById(idreponse);
    }
}
