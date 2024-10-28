package br.com.localfarm.product.domain.repositories;

import br.com.localfarm.product.domain.models.Role;
import br.com.localfarm.product.domain.models.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}
