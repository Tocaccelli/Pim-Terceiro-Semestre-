package br.com.localfarm.unitofmeasure.application.services;

import br.com.localfarm.unitofmeasure.domain.models.UnitOfMeasure;
import br.com.localfarm.unitofmeasure.domain.models.repositories.UnitOfMeasureRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UnitOfMeasureService {

    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    public UnitOfMeasure createUnitOfMeasure(@Valid UnitOfMeasure unitOfMeasure) {
        return unitOfMeasureRepository.save(unitOfMeasure);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    public UnitOfMeasure updateUnitOfMeasure(Long id, @Valid UnitOfMeasure unitOfMeasure) {
        Optional<UnitOfMeasure> existingUnitOfMeasure = unitOfMeasureRepository.findById(id);
        if (existingUnitOfMeasure.isPresent()) {
            unitOfMeasure.setId(id);
            return unitOfMeasureRepository.save(unitOfMeasure);
        }
        throw new IllegalArgumentException("Unit of Measure not found with id: " + id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    public void deleteUnitOfMeasure(Long id) {
        if (unitOfMeasureRepository.existsById(id)) {
            unitOfMeasureRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Unit of Measure not found with id: " + id);
        }
    }

    public Page<UnitOfMeasure> getAllUnitsOfMeasure(Pageable pageable) {
        return unitOfMeasureRepository.findAll(pageable);
    }

    public Optional<UnitOfMeasure> getUnitOfMeasureById(Long id) {
        return unitOfMeasureRepository.findById(id);
    }
}