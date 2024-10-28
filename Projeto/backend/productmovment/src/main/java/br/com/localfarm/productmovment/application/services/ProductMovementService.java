package br.com.localfarm.productmovment.application.services;

import br.com.localfarm.productmovment.domain.events.ProductUpdatedEvent;
import br.com.localfarm.productmovment.domain.models.ProductMovement;
import br.com.localfarm.productmovment.domain.repositories.ProductMovementRepository;
import br.com.localfarm.productmovment.application.exceptions.ProductMovementNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.http.HttpStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.function.Consumer;

@Service
@Validated
public class ProductMovementService {

    private static final Logger logger = LoggerFactory.getLogger(ProductMovementService.class);

    private final ProductMovementRepository productMovementRepository;
    private final RestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8082/p";

    @Autowired
    public ProductMovementService(ProductMovementRepository productMovementRepository, RestTemplate restTemplate) {
        this.productMovementRepository = productMovementRepository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public ProductMovement createProductMovement(@Valid ProductMovement productMovement) {
        validateProductMovement(productMovement);

        try {
            ProductMovement createdMovement = restTemplate.postForObject(BASE_URL, productMovement, ProductMovement.class);
            return productMovementRepository.save(createdMovement);
        } catch (Exception e) {
            logger.error("Erro ao criar movimento de produto: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao criar movimento de produto");
        }
    }

    @Transactional
    public ProductMovement updateProductMovement(Long id, @Valid ProductMovement productMovement) {
        validateProductMovement(productMovement);

        ProductMovement existingProductMovement = productMovementRepository.findById(id)
                .orElseThrow(() -> new ProductMovementNotFoundException("ProductMovement not found with id: " + id));

        productMovement.setId(existingProductMovement.getId());

        try {
            restTemplate.put(BASE_URL + "/atualizar/" + id, productMovement);
            return productMovementRepository.save(productMovement);
        } catch (Exception e) {
            logger.error("Erro ao atualizar movimento de produto: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar movimento de produto");
        }
    }

    @Transactional
    public void deleteProductMovement(Long id) {
        if (!productMovementRepository.existsById(id)) {
            throw new ProductMovementNotFoundException("ProductMovement not found with id: " + id);
        }

        try {
            // Excluir movimento no serviço externo
            restTemplate.delete(BASE_URL + "/excluir/" + id);
            productMovementRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Erro ao excluir movimento de produto: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao excluir movimento de produto");
        }
    }

    public Page<ProductMovement> getAllProductMovements(Pageable pageable) {
        return productMovementRepository.findAll(pageable);
    }

    public ProductMovement getProductMovementById(Long id) {
        return productMovementRepository.findById(id)
                .orElseThrow(() -> new ProductMovementNotFoundException("ProductMovement not found with id: " + id));
    }

    @Bean
    public Consumer<ProductUpdatedEvent> productUpdated() {
        return event -> {
            logger.info("Recebido evento de atualização de produto: {}", event);
            productMovementRepository.findByProductId(event.getProductId()).forEach(movement -> {
                productMovementRepository.save(movement);
                logger.info("Atualizado movimento de produto com ID: {}", movement.getId());
            });
        };
    }

    private void validateProductMovement(ProductMovement productMovement) {
        // Validações completas aqui...
        if (productMovement.getQuantity() == null || productMovement.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (productMovement.getMovementDate() == null) {
            throw new IllegalArgumentException("Movement date must not be null");
        }
        if (productMovement.getProductId() == null) {
            throw new IllegalArgumentException("Product ID must not be null");
        }
        if (productMovement.getType() == null || productMovement.getType().trim().isEmpty()) {
            throw new IllegalArgumentException("Movement type must not be null or empty");
        }
        if (!productMovement.getType().equalsIgnoreCase("Entry") && !productMovement.getType().equalsIgnoreCase("Exit")) {
            throw new IllegalArgumentException("Movement type must be 'Entry' or 'Exit'");
        }
        if (productMovement.getPurpose() == null || productMovement.getPurpose().trim().isEmpty()) {
            throw new IllegalArgumentException("Purpose must not be null or empty");
        }
        if (!productMovement.getPurpose().equalsIgnoreCase("Purchase") && !productMovement.getPurpose().equalsIgnoreCase("Sale") && !productMovement.getPurpose().equalsIgnoreCase("Production")) {
            throw new IllegalArgumentException("Purpose must be 'Purchase', 'Sale', or 'Production'");
        }
    }
}