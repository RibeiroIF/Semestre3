<?php

 class BancoDeDados 
  {
  public $bancoProva;
  public $jogos;
  public $servidor;
  public $usuario;
  public $senha;

  function __construct($servidorBanco, $usuarioBanco, $senhaBanco, $bancoProva, $jogos)
   {
   $this->servidor     = $servidorBanco;
   $this->usuario      = $usuarioBanco;
   $this->senha        = $senhaBanco;
   $this->bancoProva   = $bancoProva;
   $this->jogos        = $jogos;
   }

  function criarConexao()
   {
   $conexao = new mysqli($this->servidor, $this->usuario, $this->senha) OR die($conexao->error);
   return $conexao;
   }

  function criarBanco($conexao)
   {
   $sql = "CREATE DATABASE IF NOT EXISTS $this->bancoProva";
   $conexao->query($sql) or die($conexao->error);
   }

  function abrirBanco($conexao)
   {
   $conexao->select_db($this->bancoProva);
   }

  function definirCharset($conexao)
   {
   $conexao->set_charset("utf8");
   }

  function criarTabela($conexao)
   {
   $sql = "CREATE TABLE IF NOT EXISTS $this->jogos (
            id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
            nome VARCHAR(50),
            preco DECIMAL(5, 2),
            genero VARCHAR(20)
           ) ENGINE=innoDB;";

   $conexao->query($sql) OR die($conexao->error);
   }

  function desconectar($conexao)
   {
   $conexao->close();
   }
  }