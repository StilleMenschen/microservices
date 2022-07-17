package tech.tystnad.oauth2.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import tech.tystnad.oauth2.client.config.OAuthFeignConfig;

@Component
// 声明服务提供者的名称, 对应注册中心上的服务名, 指定一个调用失败的替代处理实现类
@FeignClient(name = "OAUTH2-RESOURCE-SERVER",
        fallback = Oauth2ResourceServiceFallBack.class,
        configuration = OAuthFeignConfig.class)
public interface Oauth2ResourceService {

    @GetMapping(value = "/api/books")
    String[] books();
}
