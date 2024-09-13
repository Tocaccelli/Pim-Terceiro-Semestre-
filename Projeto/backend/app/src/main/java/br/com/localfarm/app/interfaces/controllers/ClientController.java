package br.com.localfarm.app.interfaces.controllers;

import br.com.localfarm.app.domain.models.Client;
import br.com.localfarm.app.application.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client) {
        return clientService.updateClient(id, client);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    @GetMapping
    public Page<Client> getAllClients(Pageable pageable) {
        return clientService.getAllClients(pageable);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    @GetMapping("/{id}")
    public Optional<Client> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }
}