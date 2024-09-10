package br.com.localfarm.app.domain.repositories;

import br.com.localfarm.app.domain.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}