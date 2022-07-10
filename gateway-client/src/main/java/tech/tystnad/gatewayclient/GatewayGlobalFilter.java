package tech.tystnad.gatewayclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class GatewayGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();
        log.info("global filter path {}", path);
        exchange.getResponse().getHeaders().add("Access-Time",
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        // 过滤器顺序
        return 0;
    }
}
