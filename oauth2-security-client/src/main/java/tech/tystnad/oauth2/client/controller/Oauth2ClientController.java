package tech.tystnad.oauth2.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@RestController
@RequiredArgsConstructor
public class Oauth2ClientController {
    private final WebClient webClient;

    @GetMapping(value = "/api/books")
    public String[] getBooks(
            @RegisteredOAuth2AuthorizedClient("api-client-authorization-code") OAuth2AuthorizedClient authorizedClient
    ) {
        return this.webClient
                .get()
                .uri("http://127.0.0.1:8087/api/books")
                .attributes(oauth2AuthorizedClient(authorizedClient))
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
