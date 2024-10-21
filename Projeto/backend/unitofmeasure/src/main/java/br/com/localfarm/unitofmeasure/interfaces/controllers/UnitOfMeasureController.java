package br.com.localfarm.unitofmeasure.interfaces.controllers;

import br.com.localfarm.unitofmeasure.application.services.UnitOfMeasureService;
import br.com.localfarm.unitofmeasure.domain.models.UnitOfMeasure;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/units-of-measure")
@ CrossOrigin(origins = "*")
public class UnitOfMeasureController {

    @Autowired
    private UnitOfMeasureService unitOfMeasureService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<UnitOfMeasure> createUnitOfMeasure(@Valid @RequestBody UnitOfMeasure unitOfMeasure) {
        UnitOfMeasure createdUnit = unitOfMeasureService.createUnitOfMeasure(unitOfMeasure);
        return new ResponseEntity<>(createdUnit, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<UnitOfMeasure> updateUnitOfMeasure(@PathVariable Long id, @Valid @RequestBody UnitOfMeasure unitOfMeasure) {
        try {
            UnitOfMeasure updatedUnit = unitOfMeasureService.updateUnitOfMeasure(id, unitOfMeasure);
            return new ResponseEntity<>(updatedUnit, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnitOfMeasure(@PathVariable Long id) {
        try {
            unitOfMeasureService.deleteUnitOfMeasure(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Page<UnitOfMeasure>> getAllUnitsOfMeasure(Pageable pageable) {
        Page<UnitOfMeasure> units = unitOfMeasureService.getAllUnitsOfMeasure(pageable);
        return new ResponseEntity<>(units, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnitOfMeasure> getUnitOfMeasureById(@PathVariable Long id) {
        Optional<UnitOfMeasure> unit = unitOfMeasureService.getUnitOfMeasureById(id);
        return unit.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}