<?php
  class Exemplos{
    public $nome;

    function receberDadosForm($conexao){
      $this->nome = trim($conexao->escape_string($_POST["nome"]));
    }
  }
?>