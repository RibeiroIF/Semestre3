<?php

class BancoDedados{
 
   public $nomeDoBanco;
   public $nomeDaTabela;
   public $servidor;
   public $usuario;
   public $senha;

   function __construct($servidorBanco, $usuarioBanco, $senhaBanco, $nomeBanco, $nomeTabela) {
      $this->servidor = $servidorBanco;
      $this->usuario = $usuarioBanco;
      $this->senha = $senhaBanco;
      $this->nomeDoBanco = $nomeBanco;
      $this->nomeDaTabela = $nomeTabela;
   }

}
