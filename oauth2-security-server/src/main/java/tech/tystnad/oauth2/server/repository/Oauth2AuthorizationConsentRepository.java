package tech.tystnad.oauth2.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.tystnad.oauth2.server.domain.Oauth2AuthorizationConsent;

import java.util.Optional;

@Repository
public interface Oauth2AuthorizationConsentRepository extends JpaRepository<Oauth2AuthorizationConsent, Oauth2AuthorizationConsent.AuthorizationConsentId> {
    Optional<Oauth2AuthorizationConsent> findByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);

    void deleteByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);
}

