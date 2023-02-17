package com.pfe.campagne.service;


import com.pfe.campagne.model.Examen;
import com.pfe.campagne.model.Question;
import com.pfe.campagne.model.Quiz;

import java.util.List;
import java.util.Set;

public interface QuestionService{
    public Question addQuestion(Question question);

    public List<Question> addAllQuestion(List<Question> question);

    public Question updateQuestion(Question question);

    public Set<Question> getQuestions();

    public Question getQuestion(Long questionId);


    public Set<Question> getQuestionsOfQuiz(Quiz quiz);

    public Set<Question> getQuestionsofEntrepriseByQuiz(Long entrprise,Long qid);

    //public Set<Question> getQuestionOfExamen(Examen examen);

    public Set<Question> getquestadmofquiz(Long qid);

    public List<Question>getquestionofexam (Long idExam);

    public void deleteQuestion(Long quesId);

}
