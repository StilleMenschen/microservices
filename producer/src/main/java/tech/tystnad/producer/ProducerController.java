package tech.tystnad.producer;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/producer")
@AllArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @GetMapping("/ok")
    public String ok() {
        return producerService.ok();
    }

    @GetMapping("/fail")
    public String fail() {
        return producerService.fail();
    }
}
