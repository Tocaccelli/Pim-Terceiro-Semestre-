package br.com.localfarm.app.domain.repositories;

import br.com.localfarm.app.domain.models.UnitOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Long> {
}