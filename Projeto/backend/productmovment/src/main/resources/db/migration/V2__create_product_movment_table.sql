CREATE TABLE IF NOT EXISTS product_movement (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date DATETIME NOT NULL COMMENT 'Movement date and time',
    type VARCHAR(20) NOT NULL COMMENT 'Type of movement (Entry or Exit)',
    quantity INT NOT NULL COMMENT 'Quantity moved',
    purpose VARCHAR(50) NOT NULL COMMENT 'Purpose of movement (Purchase, Sale, or Production)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;