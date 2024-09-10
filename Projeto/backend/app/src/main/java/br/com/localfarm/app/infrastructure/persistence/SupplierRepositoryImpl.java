package br.com.localfarm.app.infrastructure.persistence;

import br.com.localfarm.app.domain.models.Supplier;
import br.com.localfarm.app.domain.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SupplierRepositoryImpl {

    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Optional<Supplier> findById(Long id) {
        return supplierRepository.findById(id);
    }

    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    public void deleteById(Long id) {
        supplierRepository.deleteById(id);
    }
}