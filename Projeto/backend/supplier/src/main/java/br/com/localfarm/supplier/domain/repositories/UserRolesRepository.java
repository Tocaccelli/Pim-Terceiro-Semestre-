package br.com.localfarm.supplier.domain.repositories;

import br.com.localfarm.supplier.domain.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRole, Long> {}
