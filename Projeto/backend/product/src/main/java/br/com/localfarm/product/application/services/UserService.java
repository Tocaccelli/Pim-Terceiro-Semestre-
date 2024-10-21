package br.com.localfarm.product.application.services;

import br.com.localfarm.product.domain.models.Role;
import br.com.localfarm.product.domain.models.User;
import br.com.localfarm.product.domain.models.UserRole;
import br.com.localfarm.product.domain.models.enums.RoleEnum;
import br.com.localfarm.product.domain.repositories.RoleRepository;
import br.com.localfarm.product.domain.repositories.UserRepository;
import br.com.localfarm.product.domain.repositories.UserRolesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRolesRepository userRolesRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserRolesRepository userRolesRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRolesRepository = userRolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User save(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            Role role = user.getRoles().iterator().next();
            RoleEnum roleEnum = RoleEnum.valueOf(role.getName().name());

            Role foundRole = roleRepository.findByName(roleEnum)
                    .orElseThrow(() -> new IllegalArgumentException("Role not found: " + role.getName()));

            userRolesRepository.save(new UserRole(savedUser.getId(), foundRole.getId()));
        } else {
            throw new IllegalArgumentException("At least one role must be provided");
        }

        return savedUser;
    }


    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
    }
}