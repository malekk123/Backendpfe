package com.pfe.campagne.service;

import com.pfe.campagne.model.Campagne;


import java.util.List;
import java.util.Set;

public interface CampagneService {

    public Campagne addCampagne(Campagne campagne);

    public Campagne updateCampagne(Campagne campagne);

    public Set<Campagne> getCampagnes();

    public Campagne getCampagne(Long campagneId);

    public List<Campagne> getActiveCampagne();

    public List<Campagne> getArchivedCampagne();

    public List<Campagne> getArchivedCampagneofentreprise(Long entrepriseid);

    public List<Campagne> getActiveCampagneofentreprise(Long entrepriseid);

    public void deleteCampagne(Long campagneId);
}
