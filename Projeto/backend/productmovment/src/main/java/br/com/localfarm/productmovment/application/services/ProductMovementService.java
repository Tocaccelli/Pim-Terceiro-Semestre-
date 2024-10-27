package br.com.localfarm.productmovment.application.services;

import br.com.localfarm.productmovment.domain.models.ProductMovement;
import br.com.localfarm.productmovment.domain.repositories.ProductMovementRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductMovementService {

    @Autowired
    private ProductMovementRepository productMovementRepository;

    @PreAuthorize("hasRole('ROLE_USER')")
    public ProductMovement createProductMovement(@Valid ProductMovement productMovement) {
        return productMovementRepository.save(productMovement);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public ProductMovement updateProductMovement(Long id, @Valid ProductMovement productMovement) {
        Optional<ProductMovement> existingProductMovement = productMovementRepository.findById(id);
        if (existingProductMovement.isPresent()) {
            productMovement.setId(id);
            return productMovementRepository.save(productMovement);
        }
        throw new IllegalArgumentException("ProductMovement not found with id: " + id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteProductMovement(Long id) {
        if (productMovementRepository.existsById(id)) {
            productMovementRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("ProductMovement not found with id: " + id);
        }
    }

    public Page<ProductMovement> getAllProductMovements(Pageable pageable) {
        return productMovementRepository.findAll(pageable);
    }

    public Optional<ProductMovement> getProductMovementById(Long id) {
        return productMovementRepository.findById(id);
    }
}