package tech.tystnad.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/consumer")
public class ConsumerController {
    // 远程调用服务
    private final ProducerLoadService producerLoadService;
    // 配类
    private final ConsumerAppConfig consumerAppConfig;

    @Value("${app.value:nothing}")
    private String value;

    @Autowired
    public ConsumerController(ProducerLoadService producerLoadService, ConsumerAppConfig consumerAppConfig) {
        this.producerLoadService = producerLoadService;
        this.consumerAppConfig = consumerAppConfig;
    }

    @GetMapping
    public ProducerResponse hello() {
        log.info("The nacos config message {} {}", consumerAppConfig.getMessage(), value);
        return ProducerResponse.builder().code(200)
                .message("It's Consumer Service " + consumerAppConfig.getMessage())
                .build();
    }

    @GetMapping("/load")
    public List<ProducerLoadService.User> load() {
        return producerLoadService.load();
    }

    @GetMapping("/fail")
    public ProducerResponse fail() {
        return producerLoadService.fail();
    }
}
