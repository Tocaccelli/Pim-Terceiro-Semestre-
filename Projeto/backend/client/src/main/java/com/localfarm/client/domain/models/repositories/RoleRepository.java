package com.localfarm.client.domain.models.repositories;

import com.localfarm.client.domain.models.Role;
import com.localfarm.client.domain.models.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}
