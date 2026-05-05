DROP DATABASE IF EXISTS db_lavacao_of;
CREATE DATABASE IF NOT EXISTS db_lavacao_of;
USE db_lavacao_of;

/*TABELAS PARA COMPORTAR O CONCEITO DE HERANÇA - 1 TABELA POR ENTIDADE 
TABELA FORNECEDOR
TABELAS NACIONAL E INTERNACIONAL
*/

/* TABELAS NOVAS */
CREATE TABLE cliente(
    id INT NOT NULL auto_increment,
    nome VARCHAR(50) NOT NULL,
    celular VARCHAR(20),
    email VARCHAR(100),
    dataCadastro DATE,
    CONSTRAINT pk_fornecedor PRIMARY KEY(id)
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

CREATE TABLE veiculo(
    id INT NOT NULL auto_increment,
    placa VARCHAR(16) NOT NULL,
    observacoes VARCHAR(100),
    id_cliente INT NOT NULL,
    CONSTRAINT pk_veiculo PRIMARY KEY(id),
    CONSTRAINT fk_veiculo_cliente FOREIGN KEY(id_cliente) REFERENCES cliente(id)
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
    id_veiculo INT NOT NULL,
    CONSTRAINT pk_produto PRIMARY KEY(id),
    CONSTRAINT fk_produto_marca FOREIGN KEY(id_marca) REFERENCES marca(id),
    CONSTRAINT fk_produto_veiculo FOREIGN KEY(id_veiculo) REFERENCES veiculo(id)
) engine=InnoDB;

CREATE TABLE motor(
    id_modelo INT NOT NULL REFERENCES modelo(id),
    id INT NOT NULL auto_increment,
    potencia INT NOT NULL,
    id_tipoCombustivel ENUM('GASOLINA', 'ETANOL', 'FLEX', 'DIESEL', 'GNV', 'OUTRO') NOT NULL DEFAULT 'GASOLINA',
    CONSTRAINT pk_motor PRIMARY KEY (id),
    CONSTRAINT fk_motor_modelo FOREIGN KEY (id_modelo) REFERENCES modelo(id)
        ON DELETE CASCADE
) engine=InnoDB;

CREATE TABLE pontuacao(
    id INT NOT NULL auto_increment,
    quantidade INT DEFAULT 0,
    id_cliente INT NOT NULL,
    CONSTRAINT pk_pontuacao PRIMARY KEY(id),
    CONSTRAINT fk_pontuacao_cliente FOREIGN KEY(id_cliente) REFERENCES cliente(id)
        ON DELETE CASCADE
) engine=InnoDB;

INSERT INTO cliente(nome, celular, email, dataCadastro) VALUES('Marcos','(11)
    1111-1111', 'marcos@gremio.edu.br', '2026-07-23');
INSERT INTO pessoaJuridica(id_cliente, cnpj, inscricaoEstadual) VALUES ((SELECT max(id) FROM cliente), '111-1111','12345678');

INSERT INTO cliente(nome, celular, email, dataCadastro) VALUES('Gabriel','(22)
    2222-2222', 'gabriel@ifsc.edu.br', '2026-10-08');
INSERT INTO pessoaFisica(id_cliente, cpf, dataNascimento) VALUES ((SELECT max(id) FROM cliente), '111.111.111-11','2000-10-10');

INSERT INTO cliente(nome, celular, email, dataCadastro) VALUES('Clara','(11)
    3333-3333', 'clara@ifsc.edu.br', '2026-05-18');
INSERT INTO pessoaFisica(id_cliente, cpf, dataNascimento) VALUES ((SELECT max(id) FROM cliente), '222.222.222-22','2000-05-05');

INSERT INTO veiculo(placa, observacoes, id_cliente) VALUES ('XXX-1111', 'Carro pequeno', 1);
INSERT INTO veiculo(placa, observacoes, id_cliente) VALUES ('YYY-2222', 'Carro médio', 2);
INSERT INTO veiculo(placa, observacoes, id_cliente) VALUES ('ZZZ-3333', 'Carro grande', 3);

INSERT INTO marca(nome) VALUES ('Ford');
INSERT INTO marca(nome) VALUES ('BYD');
INSERT INTO marca(nome) VALUES ('Renault');

INSERT INTO modelo(descricao, id_marca, id_veiculo) VALUES ('Ford KA', 1, 1);
INSERT INTO modelo(descricao, id_marca, id_veiculo) VALUES ('BYD Dolphin', 2, 2);
INSERT INTO modelo(descricao, id_marca, id_veiculo) VALUES ('Sandero', 3, 3);

INSERT INTO motor(id_modelo, potencia) VALUES (1, 180);
INSERT INTO motor(id_modelo, potencia) VALUES (2, 200);
INSERT INTO motor(id_modelo, potencia) VALUES (3, 230);

INSERT INTO pontuacao(id_cliente) VALUES (1);
INSERT INTO pontuacao(id_cliente) VALUES (2);
INSERT INTO pontuacao(id_cliente) VALUES (3);

/* TABELAS ANTIGAS */
CREATE TABLE fornecedor(
	id int NOT NULL auto_increment,
    nome varchar(50) NOT NULL,
    email varchar(100),
    fone varchar(20),
    CONSTRAINT pk_fornecedor PRIMARY KEY(id)
) engine=InnoDB;

CREATE TABLE nacional(
	id_fornecedor INT NOT NULL,
    cnpj VARCHAR(20) NOT NULL,
    CONSTRAINT pk_nacional PRIMARY KEY (id_fornecedor),
    CONSTRAINT fk_nacional_fornecedor FOREIGN KEY (id_fornecedor) REFERENCES fornecedor(id) 
		ON DELETE CASCADE
        ON UPDATE CASCADE
) engine=InnoDB;

CREATE TABLE internacional(
	id_fornecedor INT NOT NULL,
    nif VARCHAR(20) NOT NULL,
    pais VARCHAR(30) NOT NULL,
    CONSTRAINT pk_internacional PRIMARY KEY (id_fornecedor),
    CONSTRAINT fk_internacional_fornecedor FOREIGN KEY (id_fornecedor) REFERENCES fornecedor(id) 
		ON DELETE CASCADE
        ON UPDATE CASCADE
) engine=InnoDB;

CREATE TABLE categoria(
   id int NOT NULL auto_increment,
   descricao  varchar(50) NOT NULL,
   CONSTRAINT pk_categoria
      PRIMARY KEY(id)
) engine=InnoDB;

CREATE TABLE produto(
   id int NOT NULL auto_increment,
   nome varchar(50) NOT NULL,
   descricao varchar(200),
   preco decimal(10,2) NOT NULL,
   id_categoria int NOT NULL,
   id_fornecedor int NOT NULL,
   CONSTRAINT pk_produto
      PRIMARY KEY(id),
   CONSTRAINT fk_produto_categoria
      FOREIGN KEY(id_categoria)
      REFERENCES categoria(id),
   CONSTRAINT fk_produto_fornecedor
      FOREIGN KEY(id_fornecedor)
      REFERENCES fornecedor(id)      
) engine=InnoDB;

/*TABELA ESTOQUE COM RELACIONAMENTO 1:1 PARA PRODUTO*/
CREATE TABLE estoque(
	id_produto INT NOT NULL REFERENCES produto(id),
    quantidade INT NOT NULL DEFAULT 0,
    qtd_minima INT DEFAULT 0,
    qtd_maxima INT DEFAULT 0,
    situacao ENUM('ATIVO', 'INATIVO', 'BLOQUEADO') NOT NULL DEFAULT 'INATIVO',
    CONSTRAINT pk_estoque PRIMARY KEY (id_produto),
    CONSTRAINT fk_estoque_produto FOREIGN KEY (id_produto) REFERENCES produto(id) ON DELETE CASCADE
) engine=InnoDB;

CREATE TABLE clienteA(
   id int NOT NULL auto_increment,
   nome varchar(50) NOT NULL,
   cpf varchar(50) NOT NULL,
   telefone varchar(50) NOT NULL,
   email varchar(100),
   endereco varchar(100),
   data_nascimento date,
   CONSTRAINT pk_cliente
      PRIMARY KEY(id)
) engine=InnoDB;

INSERT INTO clienteA(nome, cpf, telefone, email, endereco, data_nascimento) VALUES('Edgar','111.111.111-11','(11) 1111-1111', 'edgar@ifsc.edu.br', 'av. mauro ramos', '1970-04-20');
INSERT INTO clienteA(nome, cpf, telefone, email, endereco, data_nascimento) VALUES('Marilene','222.222.222-22','(22) 2222-2121', 'marilene@ifsc.edu.br', 'av. mauro ramos', '1979-10-18');
INSERT INTO clienteA(nome, cpf, telefone, email, endereco, data_nascimento) VALUES('Carla','333.333.333-33','(33) 3333-3333', 'carla@ifsc.edu.br', 'av. mauro ramos', '1986-12-12');

INSERT INTO fornecedor(nome, email, fone) VALUES('FORNEXPRESS', 'contact@fornexpress.com', '56564343');
INSERT INTO internacional(id_fornecedor, nif, pais) VALUES((SELECT max(id) FROM fornecedor), '123321', 'EUA');
INSERT INTO fornecedor(nome, email, fone) VALUES('SULBRAS', 'contact@sulbras.com', '11-2345-5432');
INSERT INTO nacional(id_fornecedor, cnpj) VALUES((SELECT max(id) FROM fornecedor), '11.719.809/0001-94');
INSERT INTO fornecedor(nome, email, fone) VALUES('CLIPS', 'contact@clips.com.br', '48-8877-8888');
INSERT INTO nacional(id_fornecedor, cnpj) VALUES((SELECT max(id) FROM fornecedor), '11.061.852/0001-72');


INSERT INTO categoria(descricao) VALUES('Eletrônicos');
INSERT INTO categoria(descricao) VALUES('Vestuário');

INSERT INTO produto(nome, descricao, preco, id_categoria, id_fornecedor) VALUES('TV 32 LG', 'TV Tela Plana 32 4k', '2000.00', '1', '2');
INSERT INTO estoque(id_produto) (SELECT max(id) FROM produto);
INSERT INTO produto(nome, descricao, preco, id_categoria, id_fornecedor) VALUES('TV 40 SAMSUNG', 'TV Tela Plana 42 4k', '3000.00', '1', '1');
INSERT INTO estoque(id_produto) (SELECT max(id) FROM produto);
INSERT INTO produto(nome, descricao, preco, id_categoria, id_fornecedor) VALUES('TÊNIS NIKE', 'Tênis Nike Tri Fusion Run 40', '550.50', '2', '1');
INSERT INTO estoque(id_produto) (SELECT max(id) FROM produto);
INSERT INTO produto(nome, descricao, preco, id_categoria, id_fornecedor) VALUES('TÊNIS ADIDAS', 'Tênis Adidas Run Ultraboost', '750.00', '2', '3');
INSERT INTO estoque(id_produto) (SELECT max(id) FROM produto);

UPDATE estoque SET quantidade=10, qtd_minima=2, qtd_maxima=100, situacao='ATIVO' WHERE id_produto = 1;
UPDATE estoque SET quantidade=5, qtd_minima=2, qtd_maxima=150, situacao='INATIVO' WHERE id_produto = 2;
UPDATE estoque SET quantidade=100, qtd_minima=2, qtd_maxima=500, situacao='BLOQUEADO' WHERE id_produto = 3;
UPDATE estoque SET quantidade=300, qtd_minima=50, qtd_maxima=1000, situacao='ATIVO' WHERE id_produto = 4;

/* POR SE TRATAR DE UMA RELAÇÃO 1:1, UMA TENTATIVA DE INSERÇÃO DE UM NOVO ESTOQUE PARA UM PRODUTO DARÁ ERRO */
/*INSERT INTO estoque(id_produto, quantidade, qtd_minima, qtd_maxima, situacao) VALUES (1, 20, 2, 100, 'ATIVO');*/
/* A TENTATIVA DE INSERIR UM ESTOQUE SEM QUE O PRODUTO EXISTA TAMBÉM CAUSARÁ ERRO*/
/*INSERT INTO estoque(id_produto, quantidade, qtd_minima, qtd_maxima, situacao) VALUES (4, 20, 2, 100, 'ATIVO');*/
/*DELETE FROM produto WHERE id = 4;*/