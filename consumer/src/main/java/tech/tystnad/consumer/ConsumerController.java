package tech.tystnad.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/consumer")
@AllArgsConstructor
public class ConsumerController {
    // 远程调用服务
    private final ProducerLoadService producerLoadService;

    @GetMapping("/load")
    public List<ProducerLoadService.User> load() {
        return producerLoadService.load();
    }

    @GetMapping("/fail")
    public String fail() {
        return producerLoadService.fail();
    }
}
