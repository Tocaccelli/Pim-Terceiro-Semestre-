package br.com.localfarm.productmovment.domain.repositories;

import br.com.localfarm.productmovment.domain.models.ProductMovement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductMovementRepository extends JpaRepository<ProductMovement, Long> {
    Page<ProductMovement> findAll(Pageable pageable);
    List<ProductMovement> findByProductId(Long productId);
}
