package br.com.localfarm.productmovment.interfaces.controllers;

import br.com.localfarm.productmovment.application.services.ProductMovementService;
import br.com.localfarm.productmovment.domain.events.ProductUpdatedEvent;
import br.com.localfarm.productmovment.domain.models.ProductMovement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product-movements")
@CrossOrigin(origins = "*")
public class ProductMovementController {

    @Autowired
    private ProductMovementService productMovementService;

    @PostMapping
    public ResponseEntity<ProductMovement> createProductMovement(@Valid @RequestBody ProductMovement productMovement) {
        ProductMovement createdProductMovement = productMovementService.createProductMovement(productMovement);
        return new ResponseEntity<>(createdProductMovement, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductMovement> updateProductMovement(@PathVariable Long id, @Valid @RequestBody ProductMovement productMovement) {
        try {
            ProductMovement updatedProductMovement = productMovementService.updateProductMovement(id, productMovement);
            return new ResponseEntity<>(updatedProductMovement, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deleteProductMovement(@PathVariable Long id) {
        try {
            productMovementService.deleteProductMovement(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Page<ProductMovement>> getAllProductMovements(Pageable pageable) {
        Page<ProductMovement> productMovements = productMovementService.getAllProductMovements(pageable);
        return new ResponseEntity<>(productMovements, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductMovement> getProductMovementById(@PathVariable Long id) {
        Optional<ProductMovement> productMovement = Optional.ofNullable(productMovementService.getProductMovementById(id));
        return productMovement.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/product-updated")
    public ResponseEntity<Void> handleProductUpdatedEvent(@Valid @RequestBody ProductUpdatedEvent event) {
        productMovementService.productUpdated().accept(event);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}