package tech.tystnad.consumer;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ProducerLoadServiceFallBack implements ProducerLoadService {
    @Override
    public ProducerResponse fail() {
        return ProducerResponse.builder()
                .code(500).message("The system is busy!!!")
                .build();
    }

    @Override
    public List<User> load() {
        return Collections.emptyList();
    }
}
