package com.pfe.campagne.controller;



import com.pfe.campagne.model.Candidatexam;
import com.pfe.campagne.service.CandidatexamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/camdidatexam")
public class CandidatexamController {

    @Autowired
    private CandidatexamService candidatexamService;

    @PostMapping("/")
    //add camdidatexam
    public ResponseEntity<Candidatexam> add (@RequestBody Candidatexam candidatexam){
        return ResponseEntity.ok(this.candidatexamService.addexamCandidat(candidatexam));
    }


    @PutMapping("/")
    //update campagne
    public ResponseEntity <Candidatexam> update (@RequestBody Candidatexam candidatexam){
        return ResponseEntity.ok(this.candidatexamService.updateCandidat(candidatexam));
    }

    //get single campagne
    @GetMapping("/{idcandidat}")
    public Candidatexam singleexam(@PathVariable("idcandidat")Long idcandidat){
        return this.candidatexamService.getCandidatexam(idcandidat);
    }
    //get candidatexam by exam
    @GetMapping("/examen/{idExam}")
    public List<Candidatexam> candidatexamByexam(@PathVariable("idExam")Long idExam)
    {
        return  this.candidatexamService.getcandidatexamByexam(idExam);
    }

    @GetMapping("/examen/{idExam}/candidat/{idCandidat}")
    public Candidatexam candidatexamUserandExam(@PathVariable("idExam")Long idExam,@PathVariable("idCandidat")Integer idCandidat){
        return this.candidatexamService.getCandidatexamByusernameetcandidat(idExam,idCandidat);
    }

    @GetMapping("/candidatexamen/{token}")
    public Candidatexam getcandidatbytoken(@PathVariable("token")String token){
        return this.candidatexamService.getcandidatbytoken(token);
    }

    // update time in candidatexam
    @PutMapping("/candidat/{idcandidat}/time/{timer}")
    public Candidatexam getcandidatBytimer(@PathVariable("idcandidat")Long idcandidat,@PathVariable("timer")float timer){
        Candidatexam candidatexam=this.candidatexamService.getCandidatexam(idcandidat);
        return this.candidatexamService.updatetimer(candidatexam,timer);
    }
    @DeleteMapping("/{idcandidat}")
    public void delete (@PathVariable("idcandidat")Long idcandidat){
        this.candidatexamService.deleteCandidatexam(idcandidat);
    }
}
