package tech.tystnad.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ProducerService {
    public ProducerResponse ok() {
        return ProducerResponse.builder()
                .code(200)
                .message("ThreadPool: " + Thread.currentThread().getName())
                .build();
    }

    public ProducerResponse fail() {
        final int TIME_OUT_COUNT = 4;
        try {
            TimeUnit.SECONDS.sleep(TIME_OUT_COUNT);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        return ProducerResponse.builder()
                .code(200)
                .message("ThreadPool: " +
                        Thread.currentThread().getName() +
                        ", Time " + TIME_OUT_COUNT)
                .build();
    }
}
