package tech.tystnad.oauth2.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Oauth2ClientController {
    private final String apiBaseUri;
    private final WebClient webClient;

    @Autowired
    public Oauth2ClientController(WebClient webClient,
                                  @Value("${api.base-uri}") String apiBaseUri) {
        this.webClient = webClient;
        this.apiBaseUri = apiBaseUri;
    }

    @GetMapping(value = "/api/books")
    public String[] getBooks(
            @RegisteredOAuth2AuthorizedClient("api-client-authorization-code") OAuth2AuthorizedClient authorizedClient
    ) {
        return this.webClient
                .get()
                .uri(apiBaseUri)
                .attributes(ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(String[].class)
                .block();
    }

    @GetMapping
    public Map<String, Object> index() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("message", "Index");
        map.put("timestamp", System.currentTimeMillis());
        return map;
    }

    @GetMapping("/api/endpoint")
    public Map<String, Object> endpoint() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("message", "OK");
        map.put("timestamp", System.currentTimeMillis());
        return map;
    }
}
