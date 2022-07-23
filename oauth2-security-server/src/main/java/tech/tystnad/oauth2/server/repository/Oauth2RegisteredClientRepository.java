package tech.tystnad.oauth2.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.tystnad.oauth2.server.domain.Oauth2RegisteredClient;

import java.util.Optional;

@Repository
public interface Oauth2RegisteredClientRepository extends JpaRepository<Oauth2RegisteredClient, String> {
    Optional<Oauth2RegisteredClient> findByClientId(String clientId);
}
