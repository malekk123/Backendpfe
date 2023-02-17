package com.pfe.campagne.controller;

import com.pfe.campagne.helper.EntrepriseException;
import com.pfe.campagne.helper.UserFoundException;
import com.pfe.campagne.model.Entreprise;
import com.pfe.campagne.model.User;
import com.pfe.campagne.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/entreprise")
@CrossOrigin("*")
public class EntrepriseController {
    @Autowired
    private EntrepriseService entrepriseService;
    //add entreprise
    @PostMapping("/")
    public ResponseEntity<Entreprise> addEntreprise(@RequestBody Entreprise entreprise) throws Exception {
        return ResponseEntity.ok(this.entrepriseService.addEntreprise(entreprise));
    }

    //update entreprise
    @PutMapping("/")
    public ResponseEntity<Entreprise> update (@RequestBody Entreprise entreprise){
        return ResponseEntity.ok(this.entrepriseService.updateEntreprise(entreprise));
    }

    //get entreprise
    @GetMapping("/{entrepriseid}")
    public ResponseEntity<Optional<Entreprise>> getsingleentreprise(@PathVariable("entrepriseid") Long entrepriseid){
        return ResponseEntity.ok(this.entrepriseService.getEntreprise(entrepriseid));
    }

    //get entreprise
    @GetMapping("/")
    public ResponseEntity<?> entreprises(){
        return ResponseEntity.ok(this.entrepriseService.getEntreprises());
    }

    @GetMapping("/user/{userid}")
    public ResponseEntity<Entreprise> getentrepriseofuser(@PathVariable("userid") Integer userid){
        return ResponseEntity.ok(this.entrepriseService.getEntrepriseofuser(userid));
    }

    @GetMapping("/user/")
    public ResponseEntity<Entreprise> getEntrepriseUser(@RequestBody User user){
        return ResponseEntity.ok(this.entrepriseService.getEntreprisebUser(user));
    }

    @DeleteMapping("/{entreid}")
    public void deleteEntreprise(@PathVariable("entreid") Long entrepriseid){
        this.entrepriseService.delelteEntreprise(entrepriseid);
    }


    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(EntrepriseException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

}

