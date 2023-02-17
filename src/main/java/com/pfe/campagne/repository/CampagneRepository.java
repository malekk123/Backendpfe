package com.pfe.campagne.repository;

import com.pfe.campagne.model.Campagne;
import com.pfe.campagne.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CampagneRepository extends JpaRepository<Campagne,Long> {
    public List<Campagne> findByActive(Boolean b);

    public List<Campagne>findByArchived(Boolean b);

    @Query(value = "SELECT * FROM campagne where active=true and entreprise_entrpriseid=?1",nativeQuery = true)
    public List<Campagne> findCmActiveEntr(Long entrepriseid);

    @Query(value = "SELECT * FROM campagne where archived=true and entreprise_entrpriseid=?1",nativeQuery = true)
    public List<Campagne> findCmArchivedEntr(Long entrepriseid);

    public List<Campagne> findCampagneByEntrepriseAndActive(Long entrepriseid, Boolean b);

    public List<Campagne> findByEntrepriseAndArchived(Optional<Entreprise> entreprise, Boolean b);


}
