package br.com.localfarm.app.interfaces.controllers;

import br.com.localfarm.app.domain.models.ProductMovement;
import br.com.localfarm.app.application.services.ProductMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product-movements")
public class ProductMovementController {

    @Autowired
    private ProductMovementService productMovementService;

    @PostMapping
    public ProductMovement createProductMovement(@RequestBody ProductMovement productMovement) {
        return productMovementService.createProductMovement(productMovement);
    }

    @PutMapping("/{id}")
    public ProductMovement updateProductMovement(@PathVariable Long id, @RequestBody ProductMovement productMovement) {
        return productMovementService.updateProductMovement(id, productMovement);
    }

    @DeleteMapping("/{id}")
    public void deleteProductMovement(@PathVariable Long id) {
        productMovementService.deleteProductMovement(id);
    }

    @GetMapping
    public List<ProductMovement> getAllProductMovements() {
        return productMovementService.getAllProductMovements();
    }

    @GetMapping("/{id}")
    public Optional<ProductMovement> getProductMovementById(@PathVariable Long id) {
        return productMovementService.getProductMovementById(id);
    }
}