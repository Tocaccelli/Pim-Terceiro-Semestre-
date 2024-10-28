package com.localfarm.client.interfaces.controllers;

import com.localfarm.client.application.services.JwtService;
import com.localfarm.client.application.services.RoleService;
import com.localfarm.client.application.services.UserService;
import com.localfarm.client.domain.models.Role;
import com.localfarm.client.domain.models.User;
import com.localfarm.client.domain.models.dto.LoginDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final JwtService jwtService;

    @Autowired
    public UserController(UserService userService, RoleService roleService, JwtService jwtService) {
        this.userService = userService;
        this.roleService = roleService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        try {
            System.out.println("Received user: " + user.getUsername());

            // Buscar roles do banco de dados
            Set<Role> rolesToAdd = new HashSet<>();
            for (Role role : user.getRoles()) {
                Role existingRole = roleService.findById(role.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Role not found"));
                rolesToAdd.add(existingRole);
            }
            user.setRoles(rolesToAdd);

            User createdUser = userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            // Buscar o usuário pelo nome de usuário
            User user = userService.findByUsername(loginDTO.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            // Debug: Imprimir o usuário encontrado
            System.out.println("Found user: " + user.getUsername());

            // Verificar se a senha corresponde
            if (userService.passwordMatches(loginDTO.getPassword(), user.getPassword())) {
                // Gerar token JWT
                String token = jwtService.generateToken(user);
                return ResponseEntity.ok(token);
            } else {
                System.out.println("Invalid password attempt for user: " + loginDTO.getUsername());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}