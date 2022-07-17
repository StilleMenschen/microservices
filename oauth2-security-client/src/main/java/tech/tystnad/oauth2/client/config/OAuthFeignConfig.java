package tech.tystnad.oauth2.client.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

//@Configuration
@RequiredArgsConstructor
public class OAuthFeignConfig {

    public static final String CLIENT_REGISTRATION_ID = "api-client-credentials";

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2AuthorizedClientManager authorizedClientManager;

    @Bean
    public RequestInterceptor requestInterceptor() {
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(CLIENT_REGISTRATION_ID);
        OAuthClientCredentialsFeignManager clientCredentialsFeignManager =
                new OAuthClientCredentialsFeignManager(authorizedClientManager, clientRegistration);
        return requestTemplate ->
                requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer " + clientCredentialsFeignManager.getAccessToken());
    }
}
