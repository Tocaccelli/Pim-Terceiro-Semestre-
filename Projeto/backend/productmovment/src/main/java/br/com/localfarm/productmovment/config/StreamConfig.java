package br.com.localfarm.productmovment.config;

import br.com.localfarm.productmovment.domain.events.ProductUpdatedEvent;
import br.com.localfarm.productmovment.domain.models.ProductMovement;
import br.com.localfarm.productmovment.domain.repositories.ProductMovementRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

@Configuration
public class StreamConfig {

    private static final Logger logger = LoggerFactory.getLogger(StreamConfig.class);
    private final ProductMovementRepository productMovementRepository;

    public StreamConfig(ProductMovementRepository productMovementRepository) {
        this.productMovementRepository = productMovementRepository;
    }

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    @Bean
    public Function<ProductUpdatedEvent, String> processor() {
        return event -> {
            try {
                return processProductUpdatedEvent(event);
            } catch (IllegalArgumentException e) {
                logger.error("Erro de validação: {}", e.getMessage());
                throw e;
            } catch (Exception e) {
                logger.error("Erro ao processar evento de atualização de produto: {}", e.getMessage());
                throw new RuntimeException("Erro ao processar evento. Consulte os logs.");
            }
        };
    }

    private String processProductUpdatedEvent(ProductUpdatedEvent event) {
        validateEvent(event);

        logger.info("Recebido evento de atualização de produto: {}", event);

        // Encontre os movimentos de produto associados ao ID do produto
        List<ProductMovement> movements = productMovementRepository.findByProductId(event.getProductId());

        if (movements.isEmpty()) {
            logger.warn("Nenhum movimento encontrado para o produto com ID: {}", event.getProductId());
            return "Nenhum movimento encontrado para o produto com ID: " + event.getProductId();
        }

        // Atualiza cada movimento de produto
        movements.forEach(movement -> {
            movement.setUpdatedAt(LocalDateTime.now());
            productMovementRepository.save(movement);
            logger.info("Movimento atualizado com ID: {}", movement.getId());
        });

        return "Atualizado com sucesso " + movements.size() + " movimentos para o produto com ID: " + event.getProductId();
    }

    private void validateEvent(ProductUpdatedEvent event) {
        if (event.getProductId() == null) {
            throw new IllegalArgumentException("Product ID must not be null");
        }
    }
}