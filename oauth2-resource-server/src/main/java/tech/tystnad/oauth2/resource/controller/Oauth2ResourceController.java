package tech.tystnad.oauth2.resource.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Oauth2ResourceController {
    @GetMapping("/messages")
    public String[] getMessages() {
        log.info("Resource messages");
        return new String[]{"Message 1", "Message 2", "Message 3"};
    }
}
