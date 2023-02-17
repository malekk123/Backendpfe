package com.pfe.campagne.controller;

import com.pfe.campagne.model.Candidatexam;
import com.pfe.campagne.model.Question;
import com.pfe.campagne.model.RepCandidat;
import com.pfe.campagne.repository.QuestionRepository;
import com.pfe.campagne.service.CandidatService;
import com.pfe.campagne.service.CandidatexamService;
import com.pfe.campagne.service.QuestionService;
import com.pfe.campagne.service.RepCandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/repcandidat")
@CrossOrigin("*")
public class RepCandidatController {

    @Autowired
    private RepCandidatService repCandidatService;

    @Autowired
    private QuestionService service;

    @Autowired
    private CandidatexamService candexam;


    //ajouter une réponse
    @PostMapping("/")
    public ResponseEntity ajouterReponse(@RequestBody RepCandidat repCandidat){
        return ResponseEntity.ok(this.repCandidatService.addReponse(repCandidat));
    }
    //ajouter plusieurs réponses
    @PostMapping("/allrep/question/{quesId}/candidatexam/{idcandidat}")
    public List<RepCandidat> addrepcandidatall(@RequestBody List<RepCandidat> repCandidats,@PathVariable("quesId")Long quesId,@PathVariable("idcandidat")Long idcandidat){
        Question question =this.service.getQuestion(quesId);
        Candidatexam candidatexam = this.candexam.getCandidatexam(idcandidat);
        List<RepCandidat> repCandidatList =new ArrayList<>();
        repCandidatList = this.repCandidatService.addAllreponse(repCandidats,question,candidatexam);
        return repCandidatList;
    }
    //afficher une réponse
    @GetMapping("/{repc}")
    public ResponseEntity<?> getReponseCandidat(){
        return ResponseEntity.ok(this.repCandidatService.getReponse());
    }
    //mise à jour d'une réponse
    @PutMapping("/")
    public RepCandidat updateReponse(@RequestBody RepCandidat repCandidat){
        return this.repCandidatService.updateReponse(repCandidat);
    }
    //supprimer une réponse
    @DeleteMapping("/{repc}")
    public void deleterepcandidat(@PathVariable("repc") Integer repc){
        this.repCandidatService.deleteReponse(repc);
    }





}
