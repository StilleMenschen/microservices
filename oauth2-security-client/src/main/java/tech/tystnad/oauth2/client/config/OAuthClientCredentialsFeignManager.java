package tech.tystnad.oauth2.client.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.registration.ClientRegistration;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class OAuthClientCredentialsFeignManager {
    public static final String CLIENT_REGISTRATION_ID = "api-client-credentials";

    private final OAuth2AuthorizedClientManager authorizedClientManager;
    private final ClientRegistration clientRegistration;

    // Using anonymous user principal as its S2S authentication
    public static final Authentication ANONYMOUS_USER_AUTHENTICATION =
            new AnonymousAuthenticationToken(
                    "key", "anonymous", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));

    public String getAccessToken() {
        try {
            OAuth2AuthorizeRequest oAuth2AuthorizeRequest = OAuth2AuthorizeRequest
                    .withClientRegistrationId(clientRegistration.getRegistrationId())
                    .principal(ANONYMOUS_USER_AUTHENTICATION)
                    .build();
            OAuth2AuthorizedClient client = authorizedClientManager.authorize(oAuth2AuthorizeRequest);
            if (Objects.isNull(client)) {
                throw new IllegalStateException("client credentials flow on " + clientRegistration.getRegistrationId() + " failed, client is null");
            }
            return client.getAccessToken().getTokenValue();
        } catch (Exception exp) {
            log.error("client credentials error " + exp.getMessage());
        }
        return null;
    }
}
