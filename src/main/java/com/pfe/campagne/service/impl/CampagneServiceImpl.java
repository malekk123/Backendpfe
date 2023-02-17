package com.pfe.campagne.service.impl;

import com.pfe.campagne.model.Campagne;
import com.pfe.campagne.model.Examen;
import com.pfe.campagne.model.Question;
import com.pfe.campagne.repository.CampagneRepository;
import com.pfe.campagne.repository.EntrepriseRepository;
import com.pfe.campagne.service.CampagneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;

import java.util.Set;
@Service
@Transactional
public class CampagneServiceImpl implements CampagneService {


    @Autowired
    private CampagneRepository campagneRepository;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Override
    public Campagne addCampagne(Campagne campagne) {
        return this.campagneRepository.save(campagne);
    }

    @Override
    public Campagne updateCampagne(Campagne campagne) {
        return this.campagneRepository.save(campagne);
    }

    @Override
    public Set<Campagne> getCampagnes() {
        return new HashSet<>(this.campagneRepository.findAll());
    }

    @Override
    public Campagne getCampagne(Long campagneId) {
        return this.campagneRepository.findById(campagneId).get();
    }

    @Override
    public List<Campagne> getActiveCampagne() {
        return this.campagneRepository.findByActive(true);
    }

    @Override
    public List<Campagne> getArchivedCampagne() {
        return this.campagneRepository.findByArchived(true);
    }

    @Override
    public List<Campagne> getArchivedCampagneofentreprise(Long entrepriseid) {

        return this.campagneRepository.findCmArchivedEntr(entrepriseid);
    }

    @Override
    public List<Campagne> getActiveCampagneofentreprise(Long entrepriseid) {
       // Optional<Entreprise> entreprise = entrepriseRepository.findById(entrepriseid);
        return this.campagneRepository.findCmActiveEntr(entrepriseid);
    }


    @Override
    public void deleteCampagne(Long campagneId) {
         Campagne campagne = campagneRepository.findById(campagneId).get();

         Examen examen =new Examen();
         examen.setCampagne(campagne);
         if (examen.getCampagne().getCampid() == campagne.getCampid()){
             examen.setCampagne(null);
         }

        this.campagneRepository.deleteById(campagneId);
    }
}

