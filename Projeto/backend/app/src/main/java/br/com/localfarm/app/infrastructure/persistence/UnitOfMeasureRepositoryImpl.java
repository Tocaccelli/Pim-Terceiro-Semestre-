package br.com.localfarm.app.infrastructure.persistence;

import br.com.localfarm.app.domain.models.UnitOfMeasure;
import br.com.localfarm.app.domain.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UnitOfMeasureRepositoryImpl {

    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasure save(UnitOfMeasure unitOfMeasure) {
        return unitOfMeasureRepository.save(unitOfMeasure);
    }

    public Optional<UnitOfMeasure> findById(Long id) {
        return unitOfMeasureRepository.findById(id);
    }

    public List<UnitOfMeasure> findAll() {
        return unitOfMeasureRepository.findAll();
    }

    public void deleteById(Long id) {
        unitOfMeasureRepository.deleteById(id);
    }
}