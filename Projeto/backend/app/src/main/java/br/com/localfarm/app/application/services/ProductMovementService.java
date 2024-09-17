package br.com.localfarm.app.application.services;

import br.com.localfarm.app.domain.models.ProductMovement;
import br.com.localfarm.app.infrastructure.persistence.ProductMovementRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductMovementService {

    @Autowired
    private ProductMovementRepositoryImpl productMovementRepository;

    @PreAuthorize("hasRole('ROLE_USER')")
    public ProductMovement createProductMovement(ProductMovement productMovement) {
        return productMovementRepository.save(productMovement);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public ProductMovement updateProductMovement(Long id, ProductMovement productMovement) {
        Optional<ProductMovement> existingProductMovement = productMovementRepository.findById(id);
        if (existingProductMovement.isPresent()) {
            productMovement.setId(id);
            return productMovementRepository.save(productMovement);
        }
        return null;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteProductMovement(Long id) {
        productMovementRepository.deleteById(id);
    }

    public Page<ProductMovement> getAllProductMovements(Pageable pageable) {
        return productMovementRepository.findAll(pageable);
    }

    public Optional<ProductMovement> getProductMovementById(Long id) {
        return productMovementRepository.findById(id);
    }
}