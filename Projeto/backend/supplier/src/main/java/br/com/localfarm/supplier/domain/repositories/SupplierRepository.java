package br.com.localfarm.supplier.domain.repositories;

import br.com.localfarm.supplier.domain.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}