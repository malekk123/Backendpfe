package com.pfe.campagne.service.impl;

import com.pfe.campagne.helper.EntrepriseException;
import com.pfe.campagne.helper.UserFoundException;
import com.pfe.campagne.model.Entreprise;
import com.pfe.campagne.model.User;
import com.pfe.campagne.model.UserRole;
import com.pfe.campagne.repository.EntrepriseRepository;
import com.pfe.campagne.repository.UserRepository;
import com.pfe.campagne.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
@Service
public class EntrepriseServiceImpl implements EntrepriseService {
    @Autowired
    private EntrepriseRepository entrepriseRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public Entreprise addEntreprise(Entreprise entreprise) throws Exception {

        Entreprise entreprise1 = this.entrepriseRepository.findByNoment(entreprise.getNoment());
        if (entreprise1 != null) {
            System.out.println("Nom entreprise existant");
            throw new EntrepriseException();
        } else {

            return this.entrepriseRepository.save(entreprise);
        }
    }

    @Override
    public Entreprise updateEntreprise(Entreprise entreprise) {
        return this.entrepriseRepository.save(entreprise);
    }

    @Override
    public Set<Entreprise> getEntreprises() {
        return new LinkedHashSet<>(this.entrepriseRepository.findAll());
    }

    @Override
    public Optional<Entreprise> getEntreprise(Long entrid) {
        return this.entrepriseRepository.findById(entrid);
    }

    @Override
    public void delelteEntreprise(Long entrid) {
     //   Entreprise entreprise = new Entreprise();
      //  entreprise.setEntrpriseid(entrid);
       // this.entrepriseRepository.delete(entreprise);

      this.entrepriseRepository.deleteById(entrid);

    }

    @Override
    public Entreprise getEntrepriseofuser(Integer userid) {
        User user = userRepository.findById(userid).get();
        user.getEntreprise();
        return this.entrepriseRepository.findByUsers(user);
    }

    @Override
    public Entreprise getEntreprisebUser(User user) {
        //user.getEntreprise();
        return this.entrepriseRepository.findByUsers(user);
    }


    //public Entreprise getEntrepriseofuser(Long userid,Long entrepriseid) {
      //   return this.entrepriseRepository.findEntrepriseByUsers(userid);
    //}


    /*@Override
    public List<User> getUserofEntreprise(Entreprise entreprise) {
        return null ;//this.entrepriseRepository.findByentreprise(entreprise);
    }*/
}
