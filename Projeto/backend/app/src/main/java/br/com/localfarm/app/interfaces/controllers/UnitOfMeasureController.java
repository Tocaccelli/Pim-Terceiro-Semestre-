package br.com.localfarm.app.interfaces.controllers;

import br.com.localfarm.app.application.services.UnitOfMeasureService;
import br.com.localfarm.app.domain.models.UnitOfMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/units-of-measure")
public class UnitOfMeasureController {

    @Autowired
    private UnitOfMeasureService unitOfMeasureService;

    @PostMapping
    public UnitOfMeasure createUnitOfMeasure(@RequestBody UnitOfMeasure unitOfMeasure) {
        return unitOfMeasureService.createUnitOfMeasure(unitOfMeasure);
    }

    @PutMapping("/{id}")
    public UnitOfMeasure updateUnitOfMeasure(@PathVariable Long id, @RequestBody UnitOfMeasure unitOfMeasure) {
        return unitOfMeasureService.updateUnitOfMeasure(id, unitOfMeasure);
    }

    @DeleteMapping("/{id}")
    public void deleteUnitOfMeasure(@PathVariable Long id) {
        unitOfMeasureService.deleteUnitOfMeasure(id);
    }

    @GetMapping
    public List<UnitOfMeasure> getAllUnitsOfMeasure() {
        return unitOfMeasureService.getAllUnitsOfMeasure();
    }

    @GetMapping("/{id}")
    public Optional<UnitOfMeasure> getUnitOfMeasureById(@PathVariable Long id) {
        return unitOfMeasureService.getUnitOfMeasureById(id);
    }
}