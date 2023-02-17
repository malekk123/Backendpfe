package com.pfe.campagne.repository;

import com.pfe.campagne.model.TokenConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenConfirmationRepository extends JpaRepository<TokenConfirmation,Long> {

    TokenConfirmation findByConfirmationToken(String confirmationToken);

}
