package br.com.localfarm.gateway_service.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
    public AuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Lógica de autenticação (ex: verificar tokens)
            return chain.filter(exchange);
        };
    }

    public static class Config {
        // Configurações adicionais se necessário
    }
}

