package com.pfe.campagne.controller;


import com.pfe.campagne.model.Campagne;
import com.pfe.campagne.service.CampagneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/campagne")
public class CampagneController {

    @Autowired
    private CampagneService campagneService;


    @PostMapping("/")
    //add campagne
    public ResponseEntity<Campagne>add (@RequestBody Campagne campagne){
        return ResponseEntity.ok(this.campagneService.addCampagne(campagne));
    }


    @PutMapping("/")
    //update campagne
    public ResponseEntity <Campagne> update (@RequestBody Campagne campagne){
        return ResponseEntity.ok(this.campagneService.updateCampagne(campagne));
    }

    //get campagne
    @GetMapping("/")
    public ResponseEntity<?> campagnes(){
        return ResponseEntity.ok(this.campagneService.getCampagnes());
    }

    //get single campagne
    @GetMapping("/{campagneid}")
    public Campagne campagne(@PathVariable("campagneid")Long campid){
        return this.campagneService.getCampagne(campid);
    }
    //get active campagne
    @GetMapping("/active")
    public List<Campagne> getActiveCampagne(){
        return this.campagneService.getActiveCampagne();
    }

    @GetMapping("/archived")
    public List<Campagne> getArchivedCampagne(){
        return this.campagneService.getArchivedCampagne();
    }

    @GetMapping("/active/{entrpriseid}")
    public List<Campagne> getActiveentcampagne(@PathVariable("entrpriseid")Long entrpriseid)
    {return this.campagneService.getActiveCampagneofentreprise(entrpriseid);}


    @DeleteMapping("/{campagneid}")
    public void delete (@PathVariable("campagneid")Long campagneid){
        this.campagneService.deleteCampagne(campagneid);
    }

    @GetMapping("/archived/{entrpriseid}")
    public List<Campagne> getArchivedcampagne(@PathVariable("entrpriseid")Long entrpriseid){
        return this.campagneService.getArchivedCampagneofentreprise(entrpriseid);
    }
}
