package com.pfe.campagne.controller;


import com.pfe.campagne.model.*;
import com.pfe.campagne.repository.ReponseRepository;
import com.pfe.campagne.service.QuestionService;
import com.pfe.campagne.service.QuizService;
import com.pfe.campagne.service.RepCandidatService;
import com.pfe.campagne.service.ReponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService service;

    @Autowired
    private ReponseService reponseService;

    @Autowired
    private RepCandidatService repCandidatService;

    @Autowired
    private QuizService quizService;

    //add question
    @PostMapping("/")
    public ResponseEntity<Question> add(@RequestBody Question question) {
        return ResponseEntity.ok(this.service.addQuestion(question));
    }

    @PostMapping("/addall")
    public List<Question> addAll(@RequestBody List<Question> questions){
        return this.service.addAllQuestion(questions);
    }

    //update the question
    @PutMapping("/")
    public ResponseEntity<Question> update(@RequestBody Question question) {
        return ResponseEntity.ok(this.service.updateQuestion(question));
    }

    //get all question of any quiz
    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid) {
//        Quiz quiz = new Quiz();
//        quiz.setqId(qid);
//        Set<Question> questionsOfQuiz = this.service.getQuestionsOfQuiz(quiz);
//        return ResponseEntity.ok(questionsOfQuiz);

        Quiz quiz = this.quizService.getQuiz(qid);
        Set<Question> questions = quiz.getQuestions();
        List list = new ArrayList(questions);
        if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
            list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid) {
        Quiz quiz = new Quiz();
        quiz.setqId(qid);
        Set<Question> questionsOfQuiz = this.service.getQuestionsOfQuiz(quiz);
        return ResponseEntity.ok(questionsOfQuiz);

    }

    @GetMapping("/exam/{examid}")
    public ResponseEntity <?> getQuestionofexam(@PathVariable("examid")Long examid){
        Examen examen= new Examen();
        examen.setIdExam(examid);
        List<Question> questionList =this.service.getquestionofexam(examid);
        return ResponseEntity.ok(questionList);

    }

    @GetMapping("/examrep/{examid}")
    public ResponseEntity <?> getQuestionofexamrep(@PathVariable("examid")Long examid){
        Examen examen= new Examen();
        examen.setIdExam(examid);
        List<Question> questionList =this.service.getquestionofexam(examid);
        //for (Question q : questionList) {
            // get reponses question
            // add rep to question
            //Set<Reponse> resp = reponseService.getReponseQuestion(q);
            //q.setReponses(resp);
        //}
        return ResponseEntity.ok(questionList);
    }
    //get all question of any exam
 /*   @GetMapping("/examen/all/{examid}")
    public ResponseEntity<?> getQuestionsofExam(@PathVariable("examid")Long examid){
        Examen examen = new Examen();
        examen.setIdExam(examid);
        Set<Question> questionsofexam = this.service.getQuestionOfExamen(examen);
        return ResponseEntity.ok(questionsofexam);
    }*/
    //get all question of any quiz
    /*@GetMapping("/candidatexam/{candidatexamid}")
    public ResponseEntity<?> getQuestionOfExamen(@PathVariable("candidatexamid") Integer candidatexamid) {

        Candidatexam candidatexam = this.candidatexamService.getCandidatexam(candidatexamid);
        List<Question> questions = candidatexam.getQuestions();
        List list = new ArrayList(questions);
        if (list.size() > Integer.parseInt(candidatexam.getNbquestions())){
            list= list.subList(0,Integer.parseInt(candidatexam.getNbquestions()+1));
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/candidatexam/all/{candidatexamid}")
    public ResponseEntity<?> getQuestionsOfCandidatexam(@PathVariable("candidatexamid") Integer candidatexamid) {

        Candidatexam candidatexam =new Candidatexam();
        candidatexam.setIdcandidatexam(candidatexamid);

        List<Question> questionsOfExam = this.service.getQuestionOfExamen(candidatexam);
        return ResponseEntity.ok(questionsOfExam);

    }*/
    @GetMapping("/entreprise/{entrpriseid}/quiz/{qid}")
    public Set<Question> getquestionentrequiz(@PathVariable("entrpriseid")Long entrpriseid,@PathVariable("qid")Long qid){
        return this.service.getQuestionsofEntrepriseByQuiz(entrpriseid,qid);
    }

    //get single question
    @GetMapping("/{quesId}")
    public Question get(@PathVariable("quesId")Long quesId){
        return this.service.getQuestion(quesId);
    }

    //get les quiz de l'admin
    @GetMapping("/quizadmin/{qid}")
    public Set<Question>getquadmofquiz(@PathVariable("qid")Long qid){
        return  this.service.getquestadmofquiz(qid);
    }

    //supprimere une question
    @DeleteMapping("/{questId}")
    public void delete(@PathVariable("questId")Long quesId){
        this.service.deleteQuestion(quesId);
    }

    @PostMapping("/eval")
    public ResponseEntity<?> evalExam(@RequestBody List<Question> questions){
        System.out.println(questions);
        double lanote=0;
        int correcteAnswers=0;
        int attemped =0;
        for(Question q:questions){
            //single questions
            Question question = this.service.getQuestion(q.getQuesId());
            if(question.getType()=="unique"){

            }
        }
        return ResponseEntity.ok("les questions avec les réponses");
    }

}