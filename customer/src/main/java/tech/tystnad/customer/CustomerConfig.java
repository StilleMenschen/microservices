package tech.tystnad.customer;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class CustomerConfig {

    @Bean
    // 在客户端使用 RestTemplate 请求服务端时, 开启负载均衡 (Ribbon)
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofMinutes(30))
                .setReadTimeout(Duration.ofMinutes(30)).build();
    }
}
