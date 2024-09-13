package br.com.localfarm.app.domain.repositories;

import br.com.localfarm.app.domain.models.UnitOfMeasure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Long> {
    Page<UnitOfMeasure> findAll(Pageable pageable);
}