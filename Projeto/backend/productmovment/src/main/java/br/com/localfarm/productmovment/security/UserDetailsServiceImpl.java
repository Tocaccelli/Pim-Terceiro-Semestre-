package br.com.localfarm.productmovment.security;

import br.com.localfarm.productmovment.application.services.UserServiceInterface;
import br.com.localfarm.productmovment.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserServiceInterface userService;

    @Autowired
    public UserDetailsServiceImpl(@Lazy UserServiceInterface userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.findByUsername(username);
        user.get().getRoles().forEach(role -> System.out.println("Role: " + role.getName()));
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.get().getUsername(),
                user.get().getPassword(),
                userService.mapRolesToAuthorities(user.get().getRoles())
        );}
}