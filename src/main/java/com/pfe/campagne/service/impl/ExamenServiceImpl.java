package com.pfe.campagne.service.impl;

import com.pfe.campagne.model.*;
import com.pfe.campagne.repository.ExamenRepository;
import com.pfe.campagne.repository.QuestionRepository;
import com.pfe.campagne.service.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExamenServiceImpl implements ExamenService {

    @Autowired
    private  ExamenRepository examenRepository;

    @Autowired
    private QuestionRepository questionRepository;


    @Override
    public Examen addExamen(Examen examen) {
        return this.examenRepository.save(examen);
    }

    @Override
    public Examen updateExamen(Examen examen) {
        return this.examenRepository.save(examen);
    }

    @Override
    public Set<Examen> getExamens() {
        return new HashSet<>(this.examenRepository.findAll());
    }

    @Override
    public Examen getExamen(Long examenId) {
        return this.examenRepository.findById(examenId).get();
    }


    @Override
    public void deleteExamen(Long examenId) {
        Examen examen = examenRepository.findById(examenId).get();
        Question question = new Question();
        question.setExamen(examen);
        if (question.getExamen().getIdExam() == examen.getIdExam()) {
            question.setExamen(null);

        }
        this.examenRepository.deleteById(examenId);

    }
    @Override
    public List<Examen> findAllById(Campagne campagne) {
        return this.examenRepository.findAllByCampagne(campagne);
    }


    @Override
    public Examen assignquestiontoexam(Long examId, Long quesId) {
        Examen examen = examenRepository.findById(examId).get();
        Question question =questionRepository.findById(quesId).get();
        question.setExamen(examen);
        return examenRepository.save(examen);
    }

   /* @Override
    public Examen assignquestiontoexam(Long examId, Long quesId) {
        Set<Question> questionSet = null;
        Examen examen =examenRepository.findById(examId).get();
        Question question = questionRepository.findById(quesId).get();
        questionSet = examen.getQuestion();
        questionSet.add(question);
        examen.setQuestion(questionSet);
        return examenRepository.save(examen);

    }
*/
   /* @Override
    public List<Examen> getExamensofCampagne(Campagne campagne) {
        return this.examenRepository.findByCampagne(campagne);
    }*/


}
