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

CREATE TABLE marca(
                      id INT NOT NULL auto_increment,
                      nome VARCHAR(20) NOT NULL,
                      CONSTRAINT pk_marca PRIMARY KEY (id)
) engine=InnoDB;

CREATE TABLE motor(
                      id INT NOT NULL auto_increment,
                      potencia INT NOT NULL,
                      tipoCombustivel ENUM('GASOLINA', 'ETANOL', 'FLEX', 'DIESEL', 'GNV', 'OUTRO') NOT NULL DEFAULT 'GASOLINA',
                      CONSTRAINT pk_motor PRIMARY KEY (id)
) engine=InnoDB;

CREATE TABLE modelo(
                       id INT NOT NULL auto_increment,
                       descricao VARCHAR(50),
                       id_marca INT NOT NULL,
                       id_motor INT NOT NULL,
                       categoria ENUM('PEQUENO', 'MÉDIO', 'GRANDE', 'MOTO', 'PADRÃO') NOT NULL DEFAULT 'PADRÃO',
                       CONSTRAINT pk_modelo PRIMARY KEY(id),
                       CONSTRAINT fk_modelo_marca FOREIGN KEY(id_marca) REFERENCES marca(id),
                       CONSTRAINT fk_modelo_motor foreign key(id_motor) references motor(id)
                           ON DELETE CASCADE
                           ON UPDATE CASCADE
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
                        CONSTRAINT fk_veiculo_modelo FOREIGN KEY(id_modelo) REFERENCES modelo(id)
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

INSERT INTO parametros_do_sistema(pontos) VALUES(20);

INSERT INTO cor (nome) VALUES
('Preto'),
('Branco'),
('Prata'),
('Vermelho');

INSERT INTO marca (nome) VALUES
('Chevrolet'),
('Fiat'),
('Volkswagen'),
('Toyota');

INSERT INTO motor (potencia, tipoCombustivel) VALUES
(110, 'FLEX'),
(75, 'GASOLINA'),
(170, 'DIESEL'),
(85, 'GNV'),
(90, 'ETANOL');

INSERT INTO modelo (descricao, id_marca, id_motor, categoria) VALUES
('Onix 1.0', 1, 1, 'PEQUENO'),
('Mobi 1.0', 2, 2, 'PADRÃO'),
('Gol 1.6', 3, 5, 'MÉDIO'),
('Hilux 2.8', 4, 3, 'GRANDE'),
('Yamaha 2.0', 2, 4, 'MOTO');


INSERT INTO cliente (nome, celular, email, dataCadastro) VALUES
('Carlos Silva', '(11) 98888-1111', 'carlos@gmail.com', '2026-01-10'),
('Ana Oliveira', '(21) 98888-2222', 'ana@gmail.com', '2026-01-15'),
('Marcos Pisching', '(31) 98888-3333', 'marcos@gmail.com', '2026-02-01'),
('Gabriel Ribeiro', '(11) 4002-8922', 'gabriel@gmail.com', '2026-02-10'),
('Jonas Silva', '(11) 4004-1234', 'jonas@gmail.com', '2026-02-20');

INSERT INTO pessoaFisica (id_cliente, cpf, dataNascimento) VALUES
(1, '111.222.333-44', '1985-05-12'),
(2, '222.333.444-55', '1992-08-24'),
(3, '333.444.555-66', '1979-03-02');


INSERT INTO pessoaJuridica (id_cliente, cnpj, inscricaoEstadual) VALUES
(4, '12.345.678/0001-99', '111.222.333.444'),
(5, '98.765.432/0001-11', '555.666.777.888');


INSERT INTO pontuacao (quantidade, id_cliente) VALUES
(100, 1),
(20, 2),
(0, 3),
(160, 4),
(400, 5);


INSERT INTO servico (descricao, valor) VALUES
('Lavação Completa', 150.00),
('Lavação interna', 120.00);


INSERT INTO veiculo (placa, observacoes, id_cliente, id_cor, id_modelo) VALUES
('ABC-1234', 'Carro de uso diário', 1, 1, 1),
('XYZ-9876', 'Carro da esposa', 1, 2, 2),
('MNO-4567', 'Apenas para viagens', 2, 3, 3),
('DEF-1122', 'Sem observações', 2, 4, 1),
('GHI-3344', 'Necessita revisão', 3, 1, 2),
('JKL-5566', 'Carro de trabalho', 3, 2, 5),
('QWE-7788', 'Carro reserva 1', 4, 3, 3),
('RTY-9900', 'Carro de suporte', 4, 4, 4),
('UOP-1133', 'Frota Locação A', 5, 2, 1),
('VBN-4455', 'Frota Locação B', 5, 1, 4);