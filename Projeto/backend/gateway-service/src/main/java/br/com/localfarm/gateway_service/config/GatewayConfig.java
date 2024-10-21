package br.com.localfarm.gateway_service.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product_service", r -> r.path("/products/**")
                        .uri("http://localhost:8081"))
                .route("client_service", r -> r.path("/clients/**")
                        .uri("http://localhost:8082"))
                .build();
    }
}