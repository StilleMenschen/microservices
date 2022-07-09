package tech.tystnad.fraud;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/load")
public class LoadController {

    @Value("${server.port}")
    public Integer port;

    @GetMapping
    public List<User> load(){
        log.info("current server port {}", port);
        return Arrays.asList(
                new User("Jack", port),
                new User("Tim", port),
                new User("Fred", port)
        );
    }

    @Data
    @AllArgsConstructor
    static class User {
        private String name;
        private Integer age;
    }
}
