package br.com.localfarm.product.domain.repositories;

import br.com.localfarm.product.domain.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRole, Long> {}
