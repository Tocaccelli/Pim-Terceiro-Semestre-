package br.com.localfarm.app.application.services;

import br.com.localfarm.app.domain.models.UnitOfMeasure;
import br.com.localfarm.app.infrastructure.persistence.UnitOfMeasureRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitOfMeasureService {

    @Autowired
    private UnitOfMeasureRepositoryImpl unitOfMeasureRepository;

    @PreAuthorize("hasRole('ROLE_USER')")
    public UnitOfMeasure createUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        return unitOfMeasureRepository.save(unitOfMeasure);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public UnitOfMeasure updateUnitOfMeasure(Long id, UnitOfMeasure unitOfMeasure) {
        Optional<UnitOfMeasure> existingUnitOfMeasure = unitOfMeasureRepository.findById(id);
        if (existingUnitOfMeasure.isPresent()) {
            unitOfMeasure.setId(id);
            return unitOfMeasureRepository.save(unitOfMeasure);
        }
        return null;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteUnitOfMeasure(Long id) {
        unitOfMeasureRepository.deleteById(id);
    }

    public List<UnitOfMeasure> getAllUnitsOfMeasure() {
        return unitOfMeasureRepository.findAll();
    }

    public Optional<UnitOfMeasure> getUnitOfMeasureById(Long id) {
        return unitOfMeasureRepository.findById(id);
    }
}