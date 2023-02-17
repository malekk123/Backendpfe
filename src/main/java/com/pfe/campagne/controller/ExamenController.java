package com.pfe.campagne.controller;


import com.pfe.campagne.model.Campagne;
import com.pfe.campagne.model.Examen;

import com.pfe.campagne.service.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/examen")
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    //add examen
    @PostMapping("/")
    public ResponseEntity<Examen> addExam(@RequestBody Examen examen) {
        return ResponseEntity.ok(this.examenService.addExamen(examen));
    }

    //update exam
    @PutMapping("/")
    public ResponseEntity<Examen> update (@RequestBody Examen examen){
        return ResponseEntity.ok(this.examenService.updateExamen(examen));
    }

    //get exam
    @GetMapping("/")
    public ResponseEntity<?> exams(){
        return ResponseEntity.ok(this.examenService.getExamens());
    }
    //get single exam
    @GetMapping("/{examid}")
    public Examen exam(@PathVariable("examid")Long examid){
        return this.examenService.getExamen(examid);
    }
    //delete the exam
    @DeleteMapping("{examid}")
    public void delete(@PathVariable("examid")Long examid){
        this.examenService.deleteExamen(examid);
    }
    //get campagne of exam
   /*@GetMapping("/campagne/{campid}")
    public List<Examen> getCampagneExam(@PathVariable("campagneid")Long campid){
        Campagne campagne = new Campagne();
        campagne.setCampid(campid);
        return this.examenService.getExamensofCampagne(campagne);
    }*/

    @GetMapping("/campagne/{campid}")
    public List<Examen> getExamensOfCampagne(@PathVariable("campid") Long campid) {
        Campagne campagne = new Campagne();
        campagne.setCampid(campid);
        return this.examenService.findAllById(campagne);

    }

    @PostMapping("/{examId}/question/{questId}")
    public Examen assignQuestiontoexam(
        @PathVariable Long examId,
        @PathVariable Long questId
    ){
        return examenService.assignquestiontoexam(examId,questId);
    }

}
