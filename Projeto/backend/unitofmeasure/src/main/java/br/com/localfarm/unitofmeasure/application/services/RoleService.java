package br.com.localfarm.unitofmeasure.application.services;

import br.com.localfarm.unitofmeasure.domain.models.Role;
import br.com.localfarm.unitofmeasure.domain.models.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }
}