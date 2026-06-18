DROP DATABASE IF EXISTS db_lavacao;
CREATE DATABASE IF NOT EXISTS db_lavacao;
USE db_lavacao;

CREATE TABLE parametros(
    id int not null auto_increment primary key,
    pontos int not null,
    percentual_pequeno double,
    percentual_medio double,
    percentual_grande double,
    percentual_moto double,
    percentual_padrao double
) engine=InnoDB;

INSERT INTO parametros(pontos, percentual_pequeno, percentual_medio, percentual_grande, percentual_moto,
                       percentual_padrao) VALUES(20, 0, 5, 10, 7.5, 12);

CREATE TABLE cliente(
    id INT NOT NULL auto_increment,
    nome VARCHAR(50) NOT NULL,
    celular VARCHAR(20),
    email VARCHAR(100),
    dataCadastro DATE,
    CONSTRAINT pk_cliente PRIMARY KEY(id)
) engine=InnoDB;

CREATE TABLE pessoa_fisica(
    id_cliente INT NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    dataNascimento DATE,
    CONSTRAINT pk_pessoa_fisica PRIMARY KEY (id_cliente),
    CONSTRAINT fk_pessoa_fisica_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) engine=InnoDB;

CREATE TABLE pessoa_juridica(
    id_cliente INT NOT NULL,
    cnpj VARCHAR(20) NOT NULL,
    inscricaoEstadual VARCHAR(20),
    CONSTRAINT pk_pessoa_juridica PRIMARY KEY (id_cliente),
    CONSTRAINT fk_pessoa_juridica_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id)
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

CREATE TABLE modelo(
    id INT NOT NULL auto_increment,
    descricao VARCHAR(50),
    id_marca INT NOT NULL,
    categoria ENUM('PEQUENO', 'MÉDIO', 'GRANDE', 'MOTO', 'PADRÃO') NOT NULL DEFAULT 'PADRÃO',
    CONSTRAINT pk_modelo PRIMARY KEY(id),
    CONSTRAINT fk_modelo_marca FOREIGN KEY(id_marca) REFERENCES marca(id)
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
) engine=InnoDB;


CREATE TABLE motor(
    id_modelo INT NOT NULL,
    potencia INT NOT NULL,
    tipoCombustivel ENUM('GASOLINA', 'ETANOL', 'FLEX', 'DIESEL', 'GNV', 'OUTRO') NOT NULL DEFAULT 'GASOLINA',
    CONSTRAINT pk_motor PRIMARY KEY (id_modelo),
    CONSTRAINT fk_motor_modelo FOREIGN KEY (id_modelo) REFERENCES modelo(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
) engine=InnoDB;

CREATE TABLE servico(
    id int NOT NULL auto_increment,
    descricao varchar(40) NOT NULL,
    valor double NOT NULL,
    categoria ENUM('PEQUENO', 'MÉDIO', 'GRANDE', 'MOTO', 'PADRÃO') NOT NULL DEFAULT 'PADRÃO',
    CONSTRAINT pk_servico PRIMARY KEY(id)
) engine=InnoDB;

CREATE TABLE ordem_servico(
    id INT NOT NULL auto_increment,
    numero LONG,
    total DOUBLE,
    agenda DATE,
    desconto DOUBLE,
    status ENUM('FECHADA', 'CANCELADA', 'ABERTA') NOT NULL DEFAULT 'ABERTA',
    id_veiculo INT NOT NULL,
    CONSTRAINT pk_ordem_servico PRIMARY KEY(id),
    CONSTRAINT fk_ordemservico_veiculo FOREIGN KEY (id_veiculo) REFERENCES veiculo(id)
) engine=InnoDB;

CREATE TABLE item_os(
    id INT NOT NULL auto_increment,
    valor_servico DOUBLE,
    observacoes VARCHAR(128),
    id_servico INT NOT NULL,
    id_ordemservico INT NOT NULL,
    CONSTRAINT pk_item_os PRIMARY KEY(id),
    CONSTRAINT fk_itemos_servico FOREIGN KEY (id_servico) REFERENCES servico(id),
    CONSTRAINT fk_itemos_ordem_servico FOREIGN KEY (id_ordemservico) REFERENCES ordem_servico(id)
) engine=InnoDB;

CREATE TABLE pontuacao(
    id_cliente INT NOT NULL,
    quantidade INT DEFAULT 0,
    CONSTRAINT pk_pontuacao PRIMARY KEY(id_cliente),
    CONSTRAINT fk_pontuacao_cliente FOREIGN KEY(id_cliente) REFERENCES cliente(id)
) engine=InnoDB;

#####################
#POVOAÇÃO DAS TABELAS
#####################

INSERT INTO servico (descricao, valor, categoria) VALUES
    ('Lavação Completa', 150.00, 'PEQUENO'),
    ('Lavação interna', 120.00, 'GRANDE');

INSERT INTO cliente (nome, celular, email, dataCadastro) VALUES
    ('Carlos Silva', '(11) 98888-1111', 'carlos@gmail.com', '2026-01-10'),
    ('Ana Oliveira', '(21) 98888-2222', 'ana@gmail.com', '2026-01-15'),
    ('Marcos Pisching', '(31) 98888-3333', 'marcos@gmail.com', '2026-02-01'),
    ('Gabriel Ribeiro', '(11) 4002-8922', 'gabriel@gmail.com', '2026-02-10'),
    ('Jonas Silva', '(11) 4004-1234', 'jonas@gmail.com', '2026-02-20');

INSERT INTO pessoa_fisica (id_cliente, cpf, dataNascimento) VALUES
    (1, '111.222.333-44', '1985-05-12'),
    (2, '222.333.444-55', '1992-08-24'),
    (3, '333.444.555-66', '1979-03-02');


INSERT INTO pessoa_juridica (id_cliente, cnpj, inscricaoEstadual) VALUES
    (4, '12.345.678/0001-99', '111.222.333.444'),
    (5, '98.765.432/0001-11', '555.666.777.888');


INSERT INTO pontuacao (quantidade, id_cliente) VALUES
    (100, 1),
    (20, 2),
    (0, 3),
    (160, 4),
    (400, 5);

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


INSERT INTO modelo (descricao, id_marca, categoria) VALUES
    ('Onix 1.0', 1,  'PEQUENO');
INSERT INTO motor (id_modelo, potencia, tipoCombustivel) VALUES
    ((SELECT max(id) FROM modelo), 110, 'FLEX');

INSERT INTO modelo (descricao, id_marca, categoria) VALUES
    ('Mobi 1.0', 2,  'PADRÃO');
INSERT INTO motor (id_modelo, potencia, tipoCombustivel) VALUES
    ((SELECT max(id) FROM modelo), 75, 'GASOLINA');

INSERT INTO modelo (descricao, id_marca, categoria) VALUES
    ('Gol 1.6', 3,  'MÉDIO');
INSERT INTO motor (id_modelo, potencia, tipoCombustivel) VALUES
    ((SELECT max(id) FROM modelo), 170, 'DIESEL');

INSERT INTO modelo (descricao, id_marca, categoria) VALUES
    ('Hilux 2.8', 4,  'GRANDE');
INSERT INTO motor (id_modelo, potencia, tipoCombustivel) VALUES
    ((SELECT max(id) FROM modelo), 85, 'GNV');

INSERT INTO modelo (descricao, id_marca, categoria) VALUES
    ('Yamaha 2.0', 2,  'MOTO');
INSERT INTO motor (id_modelo, potencia, tipoCombustivel) VALUES
    ((SELECT max(id) FROM modelo), 90, 'ETANOL');

INSERT INTO veiculo (placa, observacoes, id_cliente, id_cor, id_modelo) VALUES
    ('ABC-1234', 'Carro de uso diário', 1, 1, 1),
    ('XYZ-9876', 'Carro da esposa', 1, 2, 2),
    ('MNO-4567', 'Apenas para viagens', 2, 3, 3),
    ('DEF-1122', 'Sem observações', 2, 4, 4),
    ('GHI-3344', 'Necessita revisão', 3, 1, 4),
    ('JKL-5566', 'Carro de trabalho', 3, 2, 1),
    ('QWE-7788', 'Carro reserva 1', 4, 3, 5),
    ('RTY-9900', 'Carro de suporte', 4, 4, 3),
    ('UOP-1133', 'Frota Locação A', 5, 2, 5),
    ('VBN-4455', 'Frota Locação B', 5, 1, 2);

INSERT INTO ordem_servico (numero, total, agenda, desconto, status, id_veiculo) VALUES
    (1001, 150.00, '2026-03-01', 0.00, 'FECHADA', 1),
    (1002, 120.00, '2026-03-02', 10.00, 'FECHADA', 3),
    (1003, 270.00, '2026-03-05', 0.00, 'ABERTA', 4),
    (1004, 150.00, '2026-03-06', 15.00, 'CANCELADA', 5),
    (1005, 120.00, '2026-03-07', 0.00, 'ABERTA', 7);

INSERT INTO item_os (valor_servico, observacoes, id_servico, id_ordemservico) VALUES
    (150.00, 'Lavação completa padrão', 1, 1),
    (120.00, 'Focar na limpeza dos bancos traseiros', 2, 2),
    (150.00, 'Lavação externa', 1, 3),
    (120.00, 'Lavação interna detalhada', 2, 3),
    (150.00, 'Cliente desistiu antes de iniciar', 1, 4),
    (120.00, 'Cuidado com os espelhos da moto', 2, 5);
