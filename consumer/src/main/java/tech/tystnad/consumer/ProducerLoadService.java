package tech.tystnad.consumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Component
// 声明服务提供者的名称, 对应注册中心上的服务名, 指定一个调用失败的替代处理实现类
@FeignClient(name = "PRODUCER", fallback = ProducerLoadServiceFallBack.class)
public interface ProducerLoadService {

    @GetMapping(value = "/api/v1/producer/fail")
    ProducerResponse fail();

    // 对应服务提供者的请求地址
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/producer/load")
    List<User> load();

    @Data
    @AllArgsConstructor
    class User {
        private String name;
        private Integer age;
    }
}
