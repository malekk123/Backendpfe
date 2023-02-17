package com.pfe.campagne.service.impl;

import com.pfe.campagne.model.Question;
import com.pfe.campagne.model.Reponse;
import com.pfe.campagne.repository.QuestionRepository;
import com.pfe.campagne.repository.ReponseRepository;
import com.pfe.campagne.service.ReponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ReponseServiceImpl implements ReponseService {
    @Autowired
    private ReponseRepository reponseRepository;

    @Autowired
    private QuestionRepository qRepository;

    @Override
    public Reponse addReponse(Reponse reponse) {

        return this.reponseRepository.save(reponse);
    }

    @Override
    public List<Reponse> addAllreponse(List<Reponse> reponses) {
        for (Reponse r: reponses ) {
            System.out.println("add reponse r=" + r + ", q=" +  r.getQuestion().getQuesId());
          Optional<Question> q = qRepository.findById(r.getQuestion().getQuesId());
         if (q != null && q.isPresent()) {
                //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Question introuvable!");
                //break;
                r.setQuestion(q.get());
               reponseRepository.save(r);
            }
        }
        //List<Reponse> reponses1 = reponseRepository.saveAll(reponses);
        return reponses;
    }

    @Override
    public Reponse updateReponse(Reponse reponse) {
        return this.reponseRepository.save(reponse);
    }

    @Override
    public List<Reponse> getReponse() {
        return this.reponseRepository.findAll();
    }

    @Override
    public Reponse getReponse(Integer idreponse) {
        return this.reponseRepository.findByIdreponse(idreponse);
    }

    public List<Reponse> getReponseQuestion(Question question){
        return this.reponseRepository.findByQuestion(question);
    }

    @Override
    public List<Reponse> addAllreponseid(List<Reponse> reponses, Question question) {
        for (Reponse r: reponses ) {
           // System.out.println("add reponse r=" + r + ", q=" +  r.getQuestion().getQuesId());
            if (question != null ) {
                //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Question introuvable!");
                //break;

                r.setQuestion(question);
                reponseRepository.save(r);
            }
        }
        //List<Reponse> reponses1 = reponseRepository.saveAll(reponses);
        return reponses;

    }

    @Override
    public void deleteReponse(Integer idreponse) {
   this.reponseRepository.deleteById(idreponse);
    }


}
