package com.pfe.campagne.controller;

import com.pfe.campagne.model.Candidat;
import com.pfe.campagne.model.Candidatexam;
import com.pfe.campagne.service.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/candidat")
public class CandidatController {

    @Autowired
    private CandidatService candidatService;

    @PostMapping("/")
    //add candidat
    public ResponseEntity<Candidat> add (@RequestBody Candidat candidat)throws Exception{
        return ResponseEntity.ok(this.candidatService.CreateNewCandidat(candidat));
    }

    @GetMapping("/{token}")
    public Candidat findCandidatBytoken(@PathVariable("token")String token){
        return this.candidatService.findBytoken(token);
    }

    @DeleteMapping("/{idCandidat}")
    public void delete (@PathVariable("idCandidat")Integer idCandidat){
        Candidat candidat = this.candidatService.findCandidatByid(idCandidat);
         this.candidatService.delete(candidat);
    }

    @RequestMapping("/envoiemail/{idCandidat}")
    public Boolean envoyerInvitationpourCandidat(@PathVariable("idCandidat") Integer idCandidat)throws Exception{
        Candidat candidat=this.candidatService.findCandidatByid(idCandidat);
        this.candidatService.envoiMail(candidat);
        return true;
    }

    @RequestMapping("/mail/{email}")
    public Boolean envoieMail(@PathVariable("email")String email)throws Exception{
        Candidat candidat=this.candidatService.findCandidatByEmail(email);
        this.candidatService.envoiMail(candidat);
        return true;
    }

    @GetMapping("/candidatexam/{idcandidat}")
    public Candidat findCandidatexam(@PathVariable("idcandidat")Long idcandidat){
        return this.candidatService.findCandidatBycandexam(idcandidat);
    }

    @GetMapping("/deletetoken/{token}")
    public Candidat deleteToken(@PathVariable("token") String token){
      return  this.candidatService.supprimeToken(token);

    }

    @PostMapping("/listcandidat/")
     public List<Candidat> findAllcandbycandex(@RequestBody List<Candidatexam>candidatexamList){
        return this.candidatService.findAllCandidatbycadex(candidatexamList);
    }


    @GetMapping("/generateToken/{idCandidat}")
    public Candidat generateToken(@PathVariable("idCandidat") Integer idCandidat){
        Candidat candidat = this.candidatService.findCandidatByid(idCandidat);
        return  this.candidatService.generateToken(candidat);
    }


    @GetMapping("/getcandidat/{idCandidat}")
    public  Candidat getCandidat(@PathVariable("idCandidat")Integer idCandidat){
        return this.candidatService.findCandidatByid(idCandidat);
    }

}
