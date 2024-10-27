package br.com.localfarm.product.interfaces.controllers.feign;

import br.com.localfarm.product.domain.models.dto.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client-service", url = "http://localhost:8081/clients")
public interface ClientFeignClient {

    @GetMapping("/{id}")
    Client getClientById(@PathVariable("id") Long id);
}