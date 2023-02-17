package com.pfe.campagne.repository;

import com.pfe.campagne.model.Entreprise;
import com.pfe.campagne.model.ImageData;
import com.pfe.campagne.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.Optional;
import java.util.function.Function;


public interface EntrepriseRepository extends JpaRepository<Entreprise,Long> {
  //  List<User> findByentreprise(Entreprise entreprise);

//    public Entreprise findEntrepriseByUsers(Long entrpriseid);

    public Entreprise findByUsers(User user);

    public Entreprise findByNoment(String noment);

    public Optional<Entreprise> findById(Long entrepriseid);

    public Optional<ImageData> findByLogo(String logo);

}
