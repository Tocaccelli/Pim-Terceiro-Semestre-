-- Criar tabela 'role' com enum para roles se não existir
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[role]') AND type in (N'U'))
BEGIN
    CREATE TABLE role (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        name VARCHAR(50) NOT NULL
    );
END;

-- Inserir valores iniciais no enum 'role' se ainda não existirem
IF NOT EXISTS (SELECT * FROM role WHERE name = 'ADMINISTRADOR')
BEGIN
    INSERT INTO role (name) VALUES
    ('ADMINISTRADOR'),
    ('GERENCIAL'),
    ('OPERACIONAL'),
    ('OPERADOR');
END;

-- Criar tabela 'user' se não existir
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[user]') AND type in (N'U'))
BEGIN
    CREATE TABLE [user] (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        username VARCHAR(50) NOT NULL,
        password VARCHAR(255) NOT NULL
    );
END;

-- Criar tabela intermediária 'user_roles' se não existir
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[user_roles]') AND type in (N'U'))
BEGIN
    CREATE TABLE user_roles (
        user_id BIGINT NOT NULL,
        role_id BIGINT NOT NULL,
        PRIMARY KEY (user_id, role_id),
        CONSTRAINT FK_user_roles_user FOREIGN KEY (user_id) REFERENCES [user](id) ON DELETE CASCADE,
        CONSTRAINT FK_user_roles_role FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
    );
END;