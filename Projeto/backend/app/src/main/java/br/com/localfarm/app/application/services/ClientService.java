package br.com.localfarm.app.application.services;

import br.com.localfarm.app.domain.models.Client;
import br.com.localfarm.app.infrastructure.persistence.ClientRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepositoryImpl clientRepository;

    @PreAuthorize("hasRole('ROLE_USER')")
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public Client updateClient(Long id, Client client) {
        Optional<Client> existingClient = clientRepository.findById(id);
        if (existingClient.isPresent()) {
            client.setId(id);
            return clientRepository.save(client);
        }
        return null;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public Page<Client> getAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }
}