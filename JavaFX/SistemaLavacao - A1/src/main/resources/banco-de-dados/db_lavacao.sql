DROP DATABASE IF EXISTS db_lavacao;
CREATE DATABASE IF NOT EXISTS db_lavacao;
USE db_lavacao;

CREATE TABLE parametros_do_sistema(
                        pontos int not null
) engine=InnoDB;

CREATE TABLE cliente(
                        id INT NOT NULL auto_increment,
                        nome VARCHAR(50) NOT NULL,
                        celular VARCHAR(20),
                        email VARCHAR(100),
                        dataCadastro DATE,
                        CONSTRAINT pk_cliente PRIMARY KEY(id)
) engine=InnoDB;

CREATE TABLE pessoaFisica(
                             id_cliente INT NOT NULL,
                             cpf VARCHAR(20) NOT NULL,
                             dataNascimento DATE,
                             CONSTRAINT pk_pessoaFisica PRIMARY KEY (id_cliente),
                             CONSTRAINT fk_pessoaFisica_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id)
                                 ON DELETE CASCADE
                                 ON UPDATE CASCADE
) engine=InnoDB;

CREATE TABLE pessoaJuridica(
                               id_cliente INT NOT NULL,
                               cnpj VARCHAR(20) NOT NULL,
                               inscricaoEstadual VARCHAR(20),
                               CONSTRAINT pk_pessoaJuridica PRIMARY KEY (id_cliente),
                               CONSTRAINT fk_pessoaJuridica_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id)
                                   ON DELETE CASCADE
                                   ON UPDATE CASCADE
) engine=InnoDB;

CREATE TABLE cor(
                    id INT NOT NULL auto_increment,
                    nome VARCHAR(20) NOT NULL,
                    CONSTRAINT pk_cor PRIMARY KEY (id)
) engine=InnoDB;

CREATE TABLE veiculo(
                        id INT NOT NULL auto_increment,
                        placa VARCHAR(16) NOT NULL,
                        observacoes VARCHAR(100),
                        id_cliente INT NOT NULL,
                        id_cor INT NOT NULL,
                        id_modelo INT NOT NULL,
                        CONSTRAINT pk_veiculo PRIMARY KEY(id),
                        CONSTRAINT fk_veiculo_cor FOREIGN KEY(id_cor) REFERENCES cor(id),
                        CONSTRAINT fk_veiculo_cliente FOREIGN KEY(id_cliente) REFERENCES cliente(id),
                        CONSTRAINT fk_veiculo_modelo FOREIGN KEY(id_modelo) REFERENCES cliente(id)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE
) engine=InnoDB;

CREATE TABLE marca(
                      id INT NOT NULL auto_increment,
                      nome VARCHAR(20) NOT NULL,
                      CONSTRAINT pk_marca PRIMARY KEY (id)
) engine=InnoDB;

CREATE TABLE motor(
                      id INT NOT NULL auto_increment,
                      potencia INT NOT NULL,
                      id_tipoCombustivel ENUM('GASOLINA', 'ETANOL', 'FLEX', 'DIESEL', 'GNV', 'OUTRO') NOT NULL DEFAULT 'GASOLINA',
                      CONSTRAINT pk_motor PRIMARY KEY (id)
) engine=InnoDB;

CREATE TABLE modelo(
                       id INT NOT NULL auto_increment,
                       descricao VARCHAR(50),
                       id_marca INT NOT NULL,
                       id_motor INT NOT NULL,
                       CONSTRAINT pk_modelo PRIMARY KEY(id),
                       CONSTRAINT fk_modelo_marca FOREIGN KEY(id_marca) REFERENCES marca(id),
                       CONSTRAINT fk_modelo_motor foreign key(id_motor) references motor(id)
                           ON DELETE CASCADE
                           ON UPDATE CASCADE
) engine=InnoDB;


CREATE TABLE servico(
                        id int NOT NULL auto_increment,
                        descricao varchar(40) NOT NULL,
                        valor float NOT NULL,
                        CONSTRAINT pk_servico
                            PRIMARY KEY(id)
) engine=InnoDB;

CREATE TABLE pontuacao(
                          id INT NOT NULL auto_increment,
                          quantidade INT DEFAULT 0,
                          id_cliente INT NOT NULL,
                          CONSTRAINT pk_pontuacao PRIMARY KEY(id),
                          CONSTRAINT fk_pontuacao_cliente FOREIGN KEY(id_cliente) REFERENCES cliente(id)
                              ON DELETE CASCADE
                              ON UPDATE CASCADE
) engine=InnoDB;

INSERT INTO cliente(nome, celular, email, dataCadastro) VALUES('Marcos','(11)1111-1111', 'marcos@gremio.edu.br', '2026-07-23');
INSERT INTO pessoaJuridica(id_cliente, cnpj, inscricaoEstadual) VALUES ((SELECT max(id) FROM cliente), '111-1111','12345678');

INSERT INTO cliente(nome, celular, email, dataCadastro) VALUES('Gabriel','(22)2222-2222', 'gabriel@ifsc.edu.br', '2026-10-08');
INSERT INTO pessoaFisica(id_cliente, cpf, dataNascimento) VALUES ((SELECT max(id) FROM cliente), '111.111.111-11','2000-10-10');

INSERT INTO cliente(nome, celular, email, dataCadastro) VALUES('Clara','(11)3333-3333', 'clara@ifsc.edu.br', '2026-05-18');
INSERT INTO pessoaFisica(id_cliente, cpf, dataNascimento) VALUES ((SELECT max(id) FROM cliente), '222.222.222-22','2000-05-05');

INSERT INTO cor(nome) VALUES ('Azul');
INSERT INTO cor(nome) VALUES ('Preto');
INSERT INTO cor(nome) VALUES ('Vermelho');


INSERT INTO marca(nome) VALUES ('Ford');
INSERT INTO marca(nome) VALUES ('BYD');
INSERT INTO marca(nome) VALUES ('Renault');

INSERT INTO motor(potencia) VALUES (180);
INSERT INTO motor(potencia) VALUES (200);
INSERT INTO motor(potencia) VALUES (230);

INSERT INTO modelo(descricao, id_marca, id_motor) VALUES ('Ford KA', 1, 1);
INSERT INTO modelo(descricao, id_marca, id_motor) VALUES ('BYD Dolphin', 2, 2);
INSERT INTO modelo(descricao, id_marca, id_motor) VALUES ('Sandero', 3, 3);

INSERT INTO veiculo(placa, observacoes, id_cliente, id_cor, id_modelo) VALUES ('XXX-1111', 'Carro pequeno', 1, 1, 1);
INSERT INTO veiculo(placa, observacoes, id_cliente, id_cor, id_modelo) VALUES ('YYY-2222', 'Carro médio', 2, 2, 2);
INSERT INTO veiculo(placa, observacoes, id_cliente, id_cor, id_modelo) VALUES ('ZZZ-3333', 'Carro grande', 3, 3, 3);

INSERT INTO pontuacao(id_cliente) VALUES (1);
INSERT INTO pontuacao(id_cliente) VALUES (2);
INSERT INTO pontuacao(id_cliente) VALUES (3);

INSERT INTO servico(descricao, valor) VALUES('Lavação externa', 80);
INSERT INTO servico(descricao, valor) VALUES('Lavação interna', 100);
INSERT INTO servico(descricao, valor) VALUES('Lavação completa', 120);

INSERT INTO parametros_do_sistema(pontos) VALUES(20);