package com.localfarm.client.domain.repositories;

import com.localfarm.client.domain.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}