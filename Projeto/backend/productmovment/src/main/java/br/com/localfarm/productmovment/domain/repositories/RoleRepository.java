package br.com.localfarm.productmovment.domain.repositories;

import br.com.localfarm.productmovment.domain.models.Role;
import br.com.localfarm.productmovment.domain.models.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}
