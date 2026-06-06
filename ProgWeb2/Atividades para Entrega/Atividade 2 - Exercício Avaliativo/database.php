<?php

class BancoDeDados{
  public $bancoAtividade;
  public $projetos;
  public $servidor;
  public $usuario;
  public $senha;

  function __construct($servidorBanco, $usuarioBanco, $senhaBanco, $bancoAtividade, $projetos){
    $this->servidor = $servidorBanco;
    $this->usuario = $usuarioBanco;
    $this->senha = $senhaBanco;
    $this->bancoAtividade = $bancoAtividade;
    $this->projetos = $projetos;
  }

  function criarConexao(){
    $conexao = new mysqli($this->servidor, $this->usuario, $this->senha) OR die($conexao->error);
    return $conexao;
  }

  function criarBanco($conexao){
   $sql = "CREATE DATABASE IF NOT EXISTS $this->bancoAtividade";
    $conexao->query($sql) or die($conexao->error);
  }

  function abrirBanco($conexao){
    $conexao->select_db($this->bancoAtividade);
  }

  function definirCharset($conexao){
    $conexao->set_charset("utf8");
  }

  function criarTabelaProjetos($conexao){
    $sql = "CREATE TABLE IF NOT EXISTS $this->projetos (
            id INT NOT NULL PRIMARY KEY,
            orcamento DECIMAL(9, 2),
            data_inicio DATE,
            horas INT NOT NULL
           ) ENGINE=innoDB;";

    $conexao->query($sql) OR die($conexao->error);
  }

  function desconectar($conexao){
    $conexao->close();
  }
}