package com.pfe.campagne.controller;


import com.pfe.campagne.model.Category;
import com.pfe.campagne.model.Question;
import com.pfe.campagne.model.Reponse;
import com.pfe.campagne.service.QuestionService;
import com.pfe.campagne.service.ReponseService;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/reponse")
@CrossOrigin("*")
public class ReponseController {
    @Autowired
    private ReponseService reponseService;

    @Autowired
    private QuestionService serviceQ;

    //add reponse
    @PostMapping("/")
    public ResponseEntity addReponse(@RequestBody Reponse reponse) {

        System.out.println("add reponse r="+reponse+", q="+reponse.getQuestion().getQuesId());
        Question question = serviceQ.getQuestion(reponse.getQuestion().getQuesId());
        if (question == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Question introuvable!");
        reponse.setQuestion(question);
        Reponse reponse1 = this.reponseService.addReponse(reponse);


        return ResponseEntity.ok(reponse);
    }
    @PostMapping("/all")
    public List<Reponse> addreponseall(@RequestBody List<Reponse>reponses){
        List<Reponse> reponseList= new ArrayList<>();
        reponseList = this.reponseService.addAllreponse(reponses);
        return reponseList;
    }

    //all with question
    @PostMapping("/allquestion/{quesId}")
    public List<Reponse> addreponseallques(@RequestBody List<Reponse>reponses,@PathVariable("quesId") Long quesId){
        List<Reponse> reponseList= new ArrayList<>();
        Question question=this.serviceQ.getQuestion(quesId);
        reponseList = this.reponseService.addAllreponseid(reponses,question);
        return reponseList;
    }
    //get reponse
    @GetMapping("/{idreponse}")
    public Reponse getReponse(@PathVariable("idreponse") Integer idreponse) {
        return this.reponseService.getReponse(idreponse);
    }

    //get all reponses
    @GetMapping("/")
    public ResponseEntity<?> getReponses() {
        return ResponseEntity.ok(this.reponseService.getReponse());
    }

    //update reponses
    @PutMapping("/")
    public Reponse updateReponse(@RequestBody Reponse reponse) {
        return this.reponseService.updateReponse(reponse);
    }

    //get reponse
    @GetMapping("/question/{questid}")
    public List<Reponse> getReponseQuestion(@PathVariable("questid") Long questid){
        Question question = new Question();
        question.setQuesId(questid);
        return this.reponseService.getReponseQuestion(question);
    }
    //delete reponse
    @DeleteMapping("/{idreponse}")
    public void deleteReponse(@PathVariable("idreponse") Integer idreponse ) {
        this.reponseService.deleteReponse(idreponse);
    }




}
