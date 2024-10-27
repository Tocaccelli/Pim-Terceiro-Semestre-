package br.com.localfarm.product.domain.repositories;

import br.com.localfarm.product.domain.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
