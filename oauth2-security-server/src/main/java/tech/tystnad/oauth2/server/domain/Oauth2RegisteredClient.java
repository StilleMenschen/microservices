package tech.tystnad.oauth2.server.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "`oauth2_registered_client`")
@Data
public class Oauth2RegisteredClient {
    @Id
    private String id;
    private String clientId;
    private Instant clientIdIssuedAt;
    private String clientSecret;
    private Instant clientSecretExpiresAt;
    private String clientName;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String clientAuthenticationMethods;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String authorizationGrantTypes;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String redirectUris;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String scopes;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String clientSettings;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String tokenSettings;
}

