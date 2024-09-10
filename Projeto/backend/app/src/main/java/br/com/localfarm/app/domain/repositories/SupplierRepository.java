package br.com.localfarm.app.domain.repositories;

import br.com.localfarm.app.domain.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}