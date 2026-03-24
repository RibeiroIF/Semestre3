CREATE DATABASE IF NOT EXISTS db_vendas;
USE db_vendas;

CREATE TABLE cor(
   id int NOT NULL auto_increment,
   nome varchar(10) NOT NULL,
   CONSTRAINT pk_cor
      PRIMARY KEY(id)
) engine=InnoDB;


INSERT INTO cor(nome) VALUES('branco');
INSERT INTO cor(nome) VALUES('preto');