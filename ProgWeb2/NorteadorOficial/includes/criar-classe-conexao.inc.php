<?php
 class BancoDeDados {
  public $bancoLavacao;
  public $tabelaClientes;
  public $tabelaVeiculos;
  public $tabelaAdmin;
  public $servidor;
  public $usuario;
  public $senha;

  function __construct($servidorBanco, $usuarioBanco, $senhaBanco, $bancoLavacao, $tabelaClientes, $tabelaVeiculos, $tabelaAdmin){
   $this->servidor         = $servidorBanco;
   $this->usuario          = $usuarioBanco;
   $this->senha            = $senhaBanco;
   $this->bancoLavacao     = $bancoLavacao;
   $this->tabelaClientes   = $tabelaClientes;
   $this->tabelaVeiculos   = $tabelaVeiculos;
   $this->tabelaAdmin      = $tabelaAdmin;
  }

  function criarConexao(){
   $conexao = new mysqli($this->servidor, $this->usuario, $this->senha) OR die($conexao->error);
   return $conexao;
  }

  function criarBanco($conexao){
   $sql = "CREATE DATABASE IF NOT EXISTS $this->bancoLavacao";
   $conexao->query($sql) or die($conexao->error);
  }

  function abrirBanco($conexao){
   $conexao->select_db($this->bancoLavacao);
  }

  function definirCharset($conexao){
   $conexao->set_charset("utf8");
  }


  function criarTabelaClientes($conexao){
   $sql = "CREATE TABLE IF NOT EXISTS $this->tabelaClientes (
             ID INT PRIMARY KEY AUTO_INCREMENT,
             nome VARCHAR(300),
             endereco VARCHAR(300),
             email VARCHAR(300),
             telefone VARCHAR(300),
             usuario VARCHAR(100),
             senha VARCHAR(128)) ENGINE=innoDB;";

   $conexao->query($sql) OR die($conexao->error);
  }

  function criarTabelaVeiculos($conexao){
   $sql = "CREATE TABLE IF NOT EXISTS $this->tabelaVeiculos(
            ID INT PRIMARY KEY AUTO_INCREMENT,
            fabricante VARCHAR(300),
            modelo VARCHAR(300),
            placa VARCHAR(100)) ENGINE=innoDB";
   $conexao->query($sql) OR die($conexao->error);
  }

  function criarTabelaAdmin($conexao){
   $sql = "CREATE TABLE IF NOT EXISTS $this->tabelaAdmin (
             ID INT PRIMARY KEY AUTO_INCREMENT,
             login_admin VARCHAR(100),
             senha_admin VARCHAR(128)) ENGINE=innoDB;";

   $conexao->query($sql) OR die($conexao->error);
  }

  function testarSessao(){
    session_start();
    if(!isset($_SESSION["conectado"]) OR $_SESSION["conectado"] != true){
      exit("<p> Você não está logado no sistema: <a href='../php/login-admin.php> Logar </a> </p>");
    }
  }

  function logout(){
    session_start();
    $_SESSION = [];
    session_destroy();
    $this->redirecionarPagina("../php/login-cliente.php");
  }
 
  function redirecionarPagina($endereco){
    header("location: $endereco");
  }

  function terminarConexao($conexao){
   $conexao->close();
  }
}