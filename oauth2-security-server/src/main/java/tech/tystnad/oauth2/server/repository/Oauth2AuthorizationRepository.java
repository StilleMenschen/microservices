package tech.tystnad.oauth2.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.tystnad.oauth2.server.domain.Oauth2Authorization;

import java.util.Optional;

@Repository
public interface Oauth2AuthorizationRepository extends JpaRepository<Oauth2Authorization, String> {
    Optional<Oauth2Authorization> findByState(String state);

    Optional<Oauth2Authorization> findByAuthorizationCodeValue(String authorizationCode);

    Optional<Oauth2Authorization> findByAccessTokenValue(String accessToken);

    Optional<Oauth2Authorization> findByRefreshTokenValue(String refreshToken);

    @Query("select a from Oauth2Authorization a where a.state = :token" +
            " or a.authorizationCodeValue = :token" +
            " or a.accessTokenValue = :token" +
            " or a.refreshTokenValue = :token"
    )
    Optional<Oauth2Authorization> findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValue(@Param("token") String token);
}

