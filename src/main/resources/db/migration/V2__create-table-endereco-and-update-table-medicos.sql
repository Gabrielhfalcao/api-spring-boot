CREATE TABLE endereco (
    id BIGINT NOT NULL AUTO_INCREMENT,
    logradouro VARCHAR(100) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    complemento VARCHAR(100),
    numero VARCHAR(20),
    uf CHAR(2) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE medicos ADD COLUMN endereco_id BIGINT;

INSERT INTO endereco (logradouro, bairro, cep, complemento, numero, uf, cidade)
SELECT DISTINCT logradouro, bairro, cep, complemento, numero, uf, cidade FROM medicos;

UPDATE medicos m
JOIN endereco e ON
    m.logradouro = e.logradouro AND
    m.bairro = e.bairro AND
    m.cep = e.cep AND
    (m.complemento = e.complemento OR (m.complemento IS NULL AND e.complemento IS NULL)) AND
    (m.numero = e.numero OR (m.numero IS NULL AND e.numero IS NULL)) AND
    m.uf = e.uf AND
    m.cidade = e.cidade
SET m.endereco_id = e.id;

ALTER TABLE medicos DROP COLUMN logradouro;
ALTER TABLE medicos DROP COLUMN bairro;
ALTER TABLE medicos DROP COLUMN cep;
ALTER TABLE medicos DROP COLUMN complemento;
ALTER TABLE medicos DROP COLUMN numero;
ALTER TABLE medicos DROP COLUMN uf;
ALTER TABLE medicos DROP COLUMN cidade;

ALTER TABLE medicos ADD CONSTRAINT fk_medicos_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id);


