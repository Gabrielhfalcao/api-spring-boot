CREATE TABLE pacientes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    endereco_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_pacientes_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);