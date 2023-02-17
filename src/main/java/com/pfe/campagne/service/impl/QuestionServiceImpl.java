package com.pfe.campagne.service.impl;


import com.pfe.campagne.model.Examen;
import com.pfe.campagne.model.Question;
import com.pfe.campagne.model.Quiz;
import com.pfe.campagne.repository.QuestionRepository;
import com.pfe.campagne.repository.ReponseRepository;
import com.pfe.campagne.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ReponseRepository reponseRepository;
    @Override
    public Question addQuestion(Question question) {

        return this.questionRepository.save(question);
    }

    @Override
    public List<Question> addAllQuestion(List<Question> question) {
        return this.questionRepository.saveAll(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Set<Question> getQuestions() {
        return new HashSet<>(this.questionRepository.findAll());
    }

    @Override
    public Question getQuestion(Long questionId) {
        return this.questionRepository.findById(questionId).get();
    }



    @Override
    public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
        return this.questionRepository.findByQuiz(quiz);
    }

    @Override
    public Set<Question> getQuestionsofEntrepriseByQuiz(Long entrprise, Long qid) {
        return this.questionRepository.findEntreQuiz(entrprise,qid);
    }

    @Override
    public Set<Question> getquestadmofquiz(Long qid) {
        return this.questionRepository.findquesofbibadmbyquiz(qid);
    }

    @Override
    public List<Question> getquestionofexam(Long idExam) {
        return this.questionRepository.findAllByexam(idExam);
    }

    //  @Override
    //public Set<Question> getQuestionOfExamen(Examen examen) {
       // return this.questionRepository.findByExamen(examen);
    //}

    //@Override
  /*  public List<Question> getQuestionOfExamen(Candidatexam candidatexam) {
        return (List<Question>) this.questionRepository.findByCandidatexam(candidatexam);
    }*/

    @Override
    public void deleteQuestion(Long quesId) {
        Question question = new Question();
        question.setQuesId(quesId);
        this.questionRepository.delete(question);
    }

}
