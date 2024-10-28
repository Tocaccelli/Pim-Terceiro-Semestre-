package com.localfarm.client.security.config;

public class EndpointsUtil {

    // Endpoints acessíveis para todos
    public static final String[] ENDPOINTS_ANYONE_CAN_ACCESS = {
            "/api/users/register",
            "/api/users/login"
    };

    // Endpoints acessíveis apenas para Administrador Geral
    public static final String[] ENDPOINTS_WITH_ADMINISTRADOR_CAN_ACCESS = {
            "/c",                   // Listar todos os clientes
            "/c/criar",              // Criar cliente
            "/clients/excluir/{id}",       // Excluir cliente
            "/c/atualizar/{id}",     // Atualizar cliente
            "/c/buscar/{id}"         // Buscar cliente por ID
    };

    // Endpoints acessíveis para Administrador Geral e Gerencial Fazenda (sem exclusão)
    public static final String[] ENDPOINTS_WITH_GERENCIAL_CAN_ACCESS = {
            "/c",                   // Listar todos os clientes
            "/c/criar",              // Criar cliente
            "/c/atualizar/{id}",     // Atualizar cliente
            "/c/buscar/{id}"         // Buscar cliente por ID
    };

    // Endpoints acessíveis para Administrador Geral, Gerencial Fazenda e Operacional Fazenda (sem exclusão)
    public static final String[] ENDPOINTS_WITH_OPERACIONAL_CAN_ACCESS = {
            "/c",                   // Listar todos os clientes
            "/c/criar",              // Criar cliente
            "/c/atualizar/{id}",     // Atualizar cliente
            "/c/buscar/{id}"         // Buscar cliente por ID
    };
}
