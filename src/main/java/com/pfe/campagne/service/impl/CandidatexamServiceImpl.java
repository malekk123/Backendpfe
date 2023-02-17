package com.pfe.campagne.service.impl;

import com.pfe.campagne.model.Candidatexam;
import com.pfe.campagne.model.Question;
import com.pfe.campagne.model.RepCandidat;
import com.pfe.campagne.model.Reponse;
import com.pfe.campagne.repository.CandidatexamRepository;
import com.pfe.campagne.repository.QuestionRepository;
import com.pfe.campagne.repository.RepCandidatRepository;
import com.pfe.campagne.repository.ReponseRepository;
import com.pfe.campagne.service.CandidatexamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CandidatexamServiceImpl implements CandidatexamService {

    @Autowired
    private CandidatexamRepository candidatexamRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ReponseRepository reponseRepository;

    @Autowired
    private RepCandidatRepository repCandidatRepository;


    @Override
    public Candidatexam addexamCandidat(Candidatexam candidatexam) {
        return this.candidatexamRepository.save(candidatexam);
    }

    @Override
    public Candidatexam updateCandidat(Candidatexam candidatexam) {
        return this.candidatexamRepository.save(candidatexam);
    }

    @Override
    public Candidatexam getCandidatexam(Long idcandidat) {
        return this.candidatexamRepository.findById(idcandidat).get();
    }

    @Override
    public List<Candidatexam> getcandidatexamByexam(Long idExam) {
        return  this.candidatexamRepository.getcandidatExam(idExam);
    }

    @Override
    public List<Candidatexam> evaluercandidats(Long idExam) {
        Integer numCorrectAns=0;
        Integer numAttemptAns=0;
        Integer notetotal=0;
        List<Candidatexam> candidatexamList =this.candidatexamRepository.getcandidatExam(idExam);
        for (Candidatexam cand:candidatexamList){
         List<Question>questions=this.questionRepository.findAllByexam(idExam);
          for (Question q:questions){
              Set<Reponse> reponses= q.getReponses();
              Set<RepCandidat>repCandidats =q.getRepCandidats();
              if(repCandidats!=null){
                  if (repCandidats.size() == 1) {
                      for (Reponse rc : reponses) {
                              if(repCandidats.contains(rc.getReponse())){
                                  numAttemptAns++;
                                  numCorrectAns++;
                                  notetotal+=q.getPoint();

                              }
                      }
                  }else {
                      for (RepCandidat repc : repCandidats) {

                          if (repCandidats.size() == reponses.size()) {
                              if (reponses.contains(repc.getRepcand())) {
                                  notetotal += (q.getPoint() / reponses.size());
                              }

                          }
                      }
                  }
              }
          }
          cand.setNumcorrectanswer(numCorrectAns);
          cand.setNumquestionattempted(numAttemptAns);
          cand.setNotetotal(notetotal);
          this.candidatexamRepository.save(cand);
        }

        return candidatexamList;
    }

    @Override
    public Candidatexam updatetimer(Candidatexam candidatexam,float timer) {
        candidatexam.setTimer(timer);
        return this.candidatexamRepository.save(candidatexam);
    }

    @Override
    public Candidatexam getCandidatexamByusernameetcandidat(Long idExam, Integer idCandidat) {
        return this.candidatexamRepository.getListofcandidatexambyexanduser(idExam,idCandidat);
    }

    @Override
    public Candidatexam getcandidatbytoken(String token) {
        return this.candidatexamRepository.getcandidatbytoken(token);
    }

    @Override
    public void deleteCandidatexam(Long idcandidat) {
        this.candidatexamRepository.deleteById(idcandidat);
    }

     /*@Override
    public List<Question> getquestionofcandidatexam(Long idExam,Long id){
         return  this.candidatexamRepository.getListofcandidatexambyexanduser(idExam,id);
     }*/


}
