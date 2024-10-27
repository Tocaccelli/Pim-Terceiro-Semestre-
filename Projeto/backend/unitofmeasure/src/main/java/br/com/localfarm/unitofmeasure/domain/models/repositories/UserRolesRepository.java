package br.com.localfarm.unitofmeasure.domain.models.repositories;

import br.com.localfarm.unitofmeasure.domain.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRole, Long> {}
