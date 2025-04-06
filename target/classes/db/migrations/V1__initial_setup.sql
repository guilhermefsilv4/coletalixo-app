CREATE SEQUENCE seq_agendamentos START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE seq_usuarios START WITH 1 INCREMENT BY 1;

CREATE TABLE tbl_agendamento
(
    id               INTEGER DEFAULT seq_agendamentos.nextval NOT NULL,
    data_agendamento date,
    data_coleta      TIMESTAMP,
    email            VARCHAR2(255),
    localizacao      VARCHAR2(255),
    status           VARCHAR2(255) DEFAULT 'PENDENTE',
    data_criacao     TIMESTAMP,
    data_atualizacao TIMESTAMP,
    CONSTRAINT pk_tbl_agendamento PRIMARY KEY (id)
);

CREATE TABLE tbl_usuario
(
    id    INTEGER DEFAULT seq_usuarios.nextval NOT NULL,
    nome  VARCHAR2(255),
    email VARCHAR2(255),
    senha VARCHAR2(255),
    role  VARCHAR2(255) DEFAULT 'USER',
    CONSTRAINT pk_tbl_usuario PRIMARY KEY (id)
);

ALTER TABLE tbl_usuario
    ADD CONSTRAINT uc_tbl_usuario_email UNIQUE (email);

-- admin password admin123
INSERT INTO tbl_usuario (id, nome, email, senha, role)
VALUES (seq_usuarios.NEXTVAL, 'admin', 'admin@gmail.com', '$2a$10$iJzb9uptEgUZQnLcyrhVtOaiJUDzZB9ty.XvXHZRs8hVS3Ze/oQke', 'ADMIN');