package tech.tystnad.producer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ProducerService {
    public String ok() {
        return "ThreadPool: " +
                Thread.currentThread().getName();
    }

    @HystrixCommand(
            fallbackMethod = "timeoutHandler",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
            }
    )
    public String fail() {
        final int TIME_OUT_COUNT = 6;
        try {
            TimeUnit.SECONDS.sleep(TIME_OUT_COUNT);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        return "ThreadPool: " +
                Thread.currentThread().getName() +
                ", Time " + TIME_OUT_COUNT;
    }

    public String timeoutHandler() {
        return "ThreadPool: " +
                Thread.currentThread().getName() +
                ", System is busy!!!";
    }
}
