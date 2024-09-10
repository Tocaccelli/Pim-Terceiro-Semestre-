package br.com.localfarm.app.application.services;

import br.com.localfarm.app.domain.models.Supplier;
import br.com.localfarm.app.infrastructure.persistence.SupplierRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepositoryImpl supplierRepository;

    @PreAuthorize("hasRole('ROLE_USER')")
    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public Supplier updateSupplier(Long id, Supplier supplier) {
        Optional<Supplier> existingSupplier = supplierRepository.findById(id);
        if (existingSupplier.isPresent()) {
            supplier.setId(id);
            return supplierRepository.save(supplier);
        }
        return null;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getSupplierById(Long id) {
        return supplierRepository.findById(id);
    }
}