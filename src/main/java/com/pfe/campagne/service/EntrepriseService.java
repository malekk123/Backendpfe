package com.pfe.campagne.service;

import com.pfe.campagne.model.Entreprise;
import com.pfe.campagne.model.User;

import java.util.Optional;
import java.util.Set;

public interface EntrepriseService {

    public Entreprise addEntreprise(Entreprise entreprise)throws Exception;

    public Entreprise updateEntreprise(Entreprise entreprise);

    public Set<Entreprise> getEntreprises();

    public Optional<Entreprise> getEntreprise(Long entrid);

    public void delelteEntreprise(Long entrid);

    public Entreprise getEntrepriseofuser(Integer userid);
    //public List <User> getUserofEntreprise(Entreprise entreprise);


    public Entreprise getEntreprisebUser (User user);
}
