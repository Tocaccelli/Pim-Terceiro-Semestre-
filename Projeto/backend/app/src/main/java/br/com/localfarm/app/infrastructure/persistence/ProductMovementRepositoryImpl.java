package br.com.localfarm.app.infrastructure.persistence;

import br.com.localfarm.app.domain.models.ProductMovement;
import br.com.localfarm.app.domain.repositories.ProductMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductMovementRepositoryImpl {

    @Autowired
    private ProductMovementRepository productMovementRepository;

    public ProductMovement save(ProductMovement productMovement) {
        return productMovementRepository.save(productMovement);
    }

    public Optional<ProductMovement> findById(Long id) {
        return productMovementRepository.findById(id);
    }

    public List<ProductMovement> findAll() {
        return productMovementRepository.findAll();
    }

    public void deleteById(Long id) {
        productMovementRepository.deleteById(id);
    }
}