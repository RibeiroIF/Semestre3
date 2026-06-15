<?php
 class Clientes {
  public $nome;
  public $endereco;
  public $email;
  public $celular;
  public $usuario;
  public $senha;

  function receberDadosForm($conexao)
   {
   $this->nome         = trim($conexao->escape_string($_POST["cliente"]));
   $this->endereco     = trim($conexao->escape_string($_POST["endereco"]));
   $this->email        = trim($conexao->escape_string($_POST["email"]));
   $this->celular      = trim($conexao->escape_string($_POST["celular"]));
   $this->usuario      = trim($conexao->escape_string($_POST["login"]));
   $senha              = trim($conexao->escape_string($_POST["senha"]));
   $senhaCriptografada = password_hash($senha, PASSWORD_ARGON2I);
   $this->senha        = $senhaCriptografada;
   }

  function cadastrar($conexao, $nomeDaTabela)
   {
   $sql = "INSERT $nomeDaTabela VALUES(
            null,
            '$this->nome',
            '$this->endereco',
            '$this->email',
            '$this->celular',
            '$this->usuario',
            '$this->senha')";

   $conexao->query($sql) or die($conexao->error);
   }
 }