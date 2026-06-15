<?php
  class Admin{
    public $login;
    public $senha;

    function definirCredenciaisAdmin($conexao, $tabelaAdmin){
      $this->login = "admin";
      $senha = "123";
      $senhaCriptografada = password_hash($senha, PASSWORD_ARGON2I);
      $this->senha = $senhaCriptografada;

      $sql = "INSERT $tabelaAdmin VALUES(
              null,
              '$this->login',
              '$this->senha')";
              
      $conexao->query($sql) OR die($conexao->error);
    }
  }