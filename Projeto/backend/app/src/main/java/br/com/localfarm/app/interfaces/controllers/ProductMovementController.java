package br.com.localfarm.app.interfaces.controllers;

import br.com.localfarm.app.domain.models.ProductMovement;
import br.com.localfarm.app.application.services.ProductMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product-movements")
public class ProductMovementController {

    @Autowired
    private ProductMovementService productMovementService;

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @PostMapping
    public ProductMovement createProductMovement(@RequestBody ProductMovement productMovement) {
        return productMovementService.createProductMovement(productMovement);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @PutMapping("/{id}")
    public ProductMovement updateProductMovement(@PathVariable Long id, @RequestBody ProductMovement productMovement) {
        return productMovementService.updateProductMovement(id, productMovement);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @DeleteMapping("/{id}")
    public void deleteProductMovement(@PathVariable Long id) {
        productMovementService.deleteProductMovement(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'USER')")
    @GetMapping
    public Page<ProductMovement> getAllProductMovements(Pageable pageable) {
        return productMovementService.getAllProductMovements(pageable);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'USER')")
    @GetMapping("/{id}")
    public Optional<ProductMovement> getProductMovementById(@PathVariable Long id) {
        return productMovementService.getProductMovementById(id);
    }
}