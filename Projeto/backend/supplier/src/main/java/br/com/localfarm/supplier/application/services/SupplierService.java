package br.com.localfarm.supplier.application.services;

import br.com.localfarm.supplier.domain.models.Supplier;
import br.com.localfarm.supplier.domain.repositories.SupplierRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @PreAuthorize("hasRole('ROLE_USER')")
    public Supplier createSupplier(@Valid @NotNull Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public Supplier updateSupplier(@NotNull Long id, @Valid @NotNull Supplier supplier) {
        Optional<Supplier> existingSupplier = supplierRepository.findById(id);
        if (existingSupplier.isPresent()) {
            supplier.setId(id);
            return supplierRepository.save(supplier);
        }
        throw new IllegalArgumentException("Supplier not found with id: " + id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteSupplier(@NotNull Long id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Supplier not found with id: " + id);
        }
    }

    public Page<Supplier> getAllSuppliers(Pageable pageable) {
        return supplierRepository.findAll(pageable);
    }

    public Optional<Supplier> getSupplierById(@NotNull Long id) {
        return supplierRepository.findById(id);
    }
}