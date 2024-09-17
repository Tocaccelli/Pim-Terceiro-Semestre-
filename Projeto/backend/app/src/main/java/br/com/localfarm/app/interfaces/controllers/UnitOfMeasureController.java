package br.com.localfarm.app.interfaces.controllers;

import br.com.localfarm.app.application.services.UnitOfMeasureService;
import br.com.localfarm.app.domain.models.UnitOfMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/units-of-measure")
public class UnitOfMeasureController {

    @Autowired
    private UnitOfMeasureService unitOfMeasureService;

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PostMapping
    public UnitOfMeasure createUnitOfMeasure(@RequestBody UnitOfMeasure unitOfMeasure) {
        return unitOfMeasureService.createUnitOfMeasure(unitOfMeasure);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PutMapping("/{id}")
    public UnitOfMeasure updateUnitOfMeasure(@PathVariable Long id, @RequestBody UnitOfMeasure unitOfMeasure) {
        return unitOfMeasureService.updateUnitOfMeasure(id, unitOfMeasure);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteUnitOfMeasure(@PathVariable Long id) {
        unitOfMeasureService.deleteUnitOfMeasure(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    @GetMapping
    public Page<UnitOfMeasure> getAllUnitsOfMeasure(Pageable pageable) {
        return unitOfMeasureService.getAllUnitsOfMeasure(pageable);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    @GetMapping("/{id}")
    public Optional<UnitOfMeasure> getUnitOfMeasureById(@PathVariable Long id) {
        return unitOfMeasureService.getUnitOfMeasureById(id);
    }
}