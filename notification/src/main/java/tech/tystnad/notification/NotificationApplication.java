package tech.tystnad.notification;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
@AllArgsConstructor
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    @Bean
    Consumer<List<Thing>> input() {
        return list -> {
            log.info("Received {}", list.size());
            list.forEach(e -> log.info(e.toString()));
        };
    }

    @Bean
    public ApplicationRunner runner(RabbitTemplate template) {
        return args -> IntStream.rangeClosed(1, 10).forEach(i ->
                template.convertAndSend("input-in-0.someGroup",
                        new Thing("value" + i).toString()));
    }

    public static class Thing {

        private String field;

        public Thing() {
        }

        public Thing(String field) {
            this.field = field;
        }

        public String getField() {
            return this.field;
        }

        public void setField(String field) {
            this.field = field;
        }

        @Override
        public String toString() {
            return "{\"field\": \"" + field + "\"}";
        }
    }
}