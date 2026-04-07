CREATE DATABASE IF NOT EXISTS db_lavacao;
USE db_lavacao;

CREATE TABLE cor(
   id int NOT NULL auto_increment,
   nome varchar(20) NOT NULL,
   CONSTRAINT pk_cor
      PRIMARY KEY(id)
) engine=InnoDB;

CREATE TABLE marca(
    id int NOT NULL auto_increment,
    nome varchar(20) NOT NULL,
    CONSTRAINT pk_marca
        PRIMARY KEY(id)
) engine=InnoDB;

CREATE TABLE servico(
    id int NOT NULL auto_increment,
    descricao varchar(40) NOT NULL,
    valor float NOT NULL,
    CONSTRAINT pk_servico
        PRIMARY KEY(id)
) engine=InnoDB;

CREATE TABLE parametros_do_sistema(
    pontos int
) engine=InnoDB;


INSERT INTO cor(nome) VALUES('azul');
INSERT INTO cor(nome) VALUES('preto');
INSERT INTO cor(nome) VALUES('branco');

INSERT INTO marca(nome) VALUES('fiat');
INSERT INTO marca(nome) VALUES('peugeot');
INSERT INTO marca(nome) VALUES('volkswagen');

INSERT INTO servico(descricao, valor) VALUES('Lavação externa', 80);
INSERT INTO servico(descricao, valor) VALUES('Lavação interna', 100);
INSERT INTO servico(descricao, valor) VALUES('Lavação completa', 120);

INSERT INTO parametros_do_sistema(pontos) VALUES(20);