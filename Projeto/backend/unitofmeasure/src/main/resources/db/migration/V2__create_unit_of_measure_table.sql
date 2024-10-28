CREATE TABLE IF NOT EXISTS unit_of_measure (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL COMMENT 'Name of the unit of measure',
    symbol VARCHAR(10) NOT NULL UNIQUE COMMENT 'Symbol of the unit (e.g., kg, m, L)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;