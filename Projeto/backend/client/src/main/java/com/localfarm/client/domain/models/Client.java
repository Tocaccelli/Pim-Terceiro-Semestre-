package com.localfarm.client.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Address is mandatory")
    private String address;

    @NotNull(message = "Contact is mandatory")
    private String contact;

    @NotNull(message = "CNPJ/CPF is mandatory")
    private String cnpjCpf;

    @NotNull(message = "Consent is mandatory")
    private Boolean consent;
}