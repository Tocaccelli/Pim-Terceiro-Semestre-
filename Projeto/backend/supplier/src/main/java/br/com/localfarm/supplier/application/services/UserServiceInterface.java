package br.com.localfarm.supplier.application.services;

import br.com.localfarm.supplier.domain.models.Role;
import br.com.localfarm.supplier.domain.models.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface UserServiceInterface {
    User save(User user);
    void deleteById(Long id);
    Optional<User> findByUsername(String username);
    Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles);
}