CREATE TABLE IF NOT EXISTS supplier (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL COMMENT 'Supplier name',
    address VARCHAR(255) NOT NULL COMMENT 'Supplier address',
    contact VARCHAR(50) NOT NULL COMMENT 'Supplier contact information',
    cnpj_cpf VARCHAR(20) NOT NULL UNIQUE COMMENT 'Supplier CNPJ or CPF',
    consent BOOLEAN NOT NULL DEFAULT FALSE COMMENT 'Consent status'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;