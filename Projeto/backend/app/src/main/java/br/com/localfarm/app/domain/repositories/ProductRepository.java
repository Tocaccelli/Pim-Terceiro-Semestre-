package br.com.localfarm.app.domain.repositories;

import br.com.localfarm.app.domain.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}