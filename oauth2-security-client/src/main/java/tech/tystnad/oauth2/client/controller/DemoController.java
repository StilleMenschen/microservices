package tech.tystnad.oauth2.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("pub")
    public byte[] publicEndpoint() {
        return "public".getBytes(StandardCharsets.UTF_8);
    }

    @GetMapping("pri")
    public byte[] privateEndpoint() {
        return "private".getBytes(StandardCharsets.UTF_8);
    }
}
