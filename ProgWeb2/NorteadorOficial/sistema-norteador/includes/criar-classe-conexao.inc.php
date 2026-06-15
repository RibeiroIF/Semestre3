<?php
 class BancoDeDados 
  {
  public $nomeDoBanco;
  public $nomeDaTabelaClientes;
  public $nomeDaTabelaVeiculos;
  public $nomeDaTabelaAdmin;
  public $servidor;
  public $usuario;
  public $senha;

  function __construct($servidorBanco, $usuarioBanco, $senhaBanco, $nomeBanco, $nomeTabela1, $nomeTabela2, $nomeTabela3)
   {
   $this->servidor     = $servidorBanco;
   $this->usuario      = $usuarioBanco;
   $this->senha        = $senhaBanco;
   $this->nomeDoBanco  = $nomeBanco;
   $this->nomeDaTabelaClientes  = $nomeTabela1;
   $this->nomeDaTabelaVeiculos  = $nomeTabela2;
   $this->nomeDaTabelaAdmin     = $nomeTabela3;
   }

  function criarConexao()
   {
   $conexao = new mysqli($this->servidor, $this->usuario, $this->senha) OR die($conexao->error);
   return $conexao;
   }

  function criarBanco($conexao)
   {
   $sql = "CREATE DATABASE IF NOT EXISTS $this->nomeDoBanco";
   $conexao->query($sql) or die($conexao->error);
   }

  function abrirBanco($conexao)
   {
   $conexao->select_db($this->nomeDoBanco);
   }

  function definirCharset($conexao)
   {
   $conexao->set_charset("utf8");
   }


  function criarTabelaClientes($conexao)
   {
   $sql = "CREATE TABLE IF NOT EXISTS $this->nomeDaTabelaClientes (
             ID INT PRIMARY KEY AUTO_INCREMENT,
             nome VARCHAR(300),
             endereco VARCHAR(300),
             email VARCHAR(300),
             celular VARCHAR(300),
             usuario VARCHAR(100),
             senha VARCHAR(128)) ENGINE=innoDB;";

   $conexao->query($sql) OR die($conexao->error);
   }

  function criarTabelaVeiculos($conexao)
   {
   $sql = "CREATE TABLE IF NOT EXISTS $this->nomeDaTabelaVeiculos(
            ID INT PRIMARY KEY AUTO_INCREMENT,
            fabricante VARCHAR(300),
            modelo VARCHAR(300),
            placa VARCHAR(100)) ENGINE=innoDB";
   $conexao->query($sql) OR die($conexao->error);
   }
 
  function terminarConexao($conexao)
   {
   $conexao->close();
   }
  }