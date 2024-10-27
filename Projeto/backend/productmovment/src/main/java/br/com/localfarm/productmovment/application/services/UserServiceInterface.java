package br.com.localfarm.productmovment.application.services;

import br.com.localfarm.productmovment.domain.models.Role;
import br.com.localfarm.productmovment.domain.models.User;
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