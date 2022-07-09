package tech.tystnad.fraud;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Slf4j
@Component
@AllArgsConstructor
public class FraudTask {

    private final RabbitTemplate template;

    @Scheduled(cron = "30 0/2 * * * ?")
    public void rabbitMessageTask() {
        log.info("sending message to rabbit...");
        IntStream.rangeClosed(1, 2).forEach(i ->
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
