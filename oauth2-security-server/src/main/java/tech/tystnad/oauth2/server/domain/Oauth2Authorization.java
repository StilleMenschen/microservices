package tech.tystnad.oauth2.server.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "`oauth2_authorization`")
@Data
public class Oauth2Authorization {
    @Id
    @Column
    private String id;
    private String registeredClientId;
    private String principalName;
    private String authorizationGrantType;
    @Lob
    @Column(columnDefinition="TEXT")
    private String attributes;
    @Lob
    @Column(columnDefinition="TEXT")
    private String state;

    @Lob
    @Column(columnDefinition="TEXT")
    private String authorizationCodeValue;
    private Instant authorizationCodeIssuedAt;
    private Instant authorizationCodeExpiresAt;
    private String authorizationCodeMetadata;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String accessTokenValue;
    private Instant accessTokenIssuedAt;
    private Instant accessTokenExpiresAt;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String accessTokenMetadata;
    private String accessTokenType;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String accessTokenScopes;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String refreshTokenValue;
    private Instant refreshTokenIssuedAt;
    private Instant refreshTokenExpiresAt;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String refreshTokenMetadata;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String oidcIdTokenValue;
    private Instant oidcIdTokenIssuedAt;
    private Instant oidcIdTokenExpiresAt;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String oidcIdTokenMetadata;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String oidcIdTokenClaims;

}

