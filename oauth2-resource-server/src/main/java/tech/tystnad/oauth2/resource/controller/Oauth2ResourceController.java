package tech.tystnad.oauth2.resource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Oauth2ResourceController {
    @GetMapping("/api/books")
    public String[] getBooks() {
        return new String[] { "book1", "book2", "book3" };
    }
}
