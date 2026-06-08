<?php
 //esta include cria a classe que encapsula todo o comportamento da conexão do PHP com o banco de dados
 class BancoDeDados 
  {
  public $nomeDoBanco;
  public $nomeDaTabela;
  public $servidor;
  public $usuario;
  public $senha;

  //método construtor da classe
  function __construct($servidorBanco, $usuarioBanco, $senhaBanco, $nomeBanco, $nomeTabela)
   {
   $this->servidor     = $servidorBanco;
   $this->usuario      = $usuarioBanco;
   $this->senha        = $senhaBanco;
   $this->nomeDoBanco  = $nomeBanco;
   $this->nomeDaTabela = $nomeTabela;
   }

  //método que estabelece a ligação virtual entre o nosso código PHP e o servidor MySQL (uqe o PHP "converse" com o MySQL)
  function criarConexao()
   {
   $conexao = new mysqli($this->servidor, $this->usuario, $this->senha) OR die($conexao->error);
   return $conexao;
   }

  //método para criar o banco de dados no servidor
  function criarBanco($conexao)
   {
   $sql = "CREATE DATABASE IF NOT EXISTS $this->nomeDoBanco";
   $conexao->query($sql) or die($conexao->error);
   }

  //método para abrir a base de dados criada anteriormente
  function abrirBanco($conexao)
   {
   $conexao->select_db($this->nomeDoBanco);
   }

  //método para definir, no MySQL, a tabela utf-8 como tabela padrão de caracteres especiais
  function definirCharset($conexao)
   {
   $conexao->set_charset("utf8");
   }

  //método para criar a tabela no banco de dados
  function criarTabela($conexao)
   {
   $sql = "CREATE TABLE IF NOT EXISTS $this->nomeDaTabela (
            matricula VARCHAR(20) PRIMARY KEY,
            aluno VARCHAR(300),
            media DECIMAL(3,1)
           ) ENGINE=innoDB;";

   $conexao->query($sql) OR die($conexao->error);
   }

  //método para fechar a conexão entre o PHP e o MySQL
  function desconectar($conexao)
   {
   $conexao->close();
   }
  }