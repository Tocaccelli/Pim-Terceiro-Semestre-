CREATE TABLE IF NOT EXISTS client (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL COMMENT 'Client Name',
    address VARCHAR(255) NOT NULL COMMENT 'Client Address',
    contact VARCHAR(50) NOT NULL COMMENT 'Client Contact Information',
    cnpj_cpf VARCHAR(20) NOT NULL UNIQUE COMMENT 'Client CNPJ or CPF',
    consent BOOLEAN NOT NULL DEFAULT FALSE COMMENT 'Consent status'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;