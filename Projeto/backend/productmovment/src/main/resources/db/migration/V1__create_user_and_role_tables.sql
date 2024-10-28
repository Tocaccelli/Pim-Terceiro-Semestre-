-- Criar tabela 'role' se não existir
CREATE TABLE IF NOT EXISTS role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Inserir valores iniciais na tabela 'role' se ainda não existirem
INSERT INTO role (name)
SELECT 'ADMINISTRADOR' FROM DUAL WHERE NOT EXISTS (SELECT * FROM role WHERE name = 'ADMINISTRADOR');
INSERT INTO role (name)
SELECT 'GERENCIAL' FROM DUAL WHERE NOT EXISTS (SELECT * FROM role WHERE name = 'GERENCIAL');
INSERT INTO role (name)
SELECT 'OPERACIONAL' FROM DUAL WHERE NOT EXISTS (SELECT * FROM role WHERE name = 'OPERACIONAL');
INSERT INTO role (name)
SELECT 'OPERADOR' FROM DUAL WHERE NOT EXISTS (SELECT * FROM role WHERE name = 'OPERADOR');

-- Criar tabela 'user' se não existir
CREATE TABLE IF NOT EXISTS user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Criar tabela intermediária 'user_roles' se não existir
CREATE TABLE IF NOT EXISTS user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT FK_user_roles_user FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    CONSTRAINT FK_user_roles_role FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);