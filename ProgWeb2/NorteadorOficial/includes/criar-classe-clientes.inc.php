<?php
 class Clientes {
  public $nome;
  public $endereco;
  public $email;
  public $telefone;
  public $usuario;
  public $senha;

  function receberDadosForm($conexao){
   $this->nome         = trim($conexao->escape_string($_POST["nome"]));
   $this->endereco     = trim($conexao->escape_string($_POST["endereco"]));
   $this->email        = trim($conexao->escape_string($_POST["email"]));
   $this->telefone     = trim($conexao->escape_string($_POST["telefone"]));
   $this->usuario      = trim($conexao->escape_string($_POST["login"]));
   $senha              = trim($conexao->escape_string($_POST["senha"]));
   $senhaCriptografada = password_hash($senha, PASSWORD_ARGON2I);
   $this->senha        = $senhaCriptografada;
  }

  function cadastrar($conexao, $tabelaClientes){
   $sql = "INSERT $tabelaClientes VALUES(
            null,
            '$this->nome',
            '$this->endereco',
            '$this->email',
            '$this->telefone',
            '$this->usuario',
            '$this->senha')";

   $conexao->query($sql) or die($conexao->error);
  }

  function logar($conexao, $tabelaClientes){

   $login = trim($conexao->escape_string($_POST["login"]));
   $senha = trim($conexao->escape_string($_POST["senha"]));
   $senhaCriptografada = password_hash($senha, PASSWORD_ARGON2I);

   $sql = "SELECT senha FROM $tabelaClientes WHERE usuario='$login'";
   $resultado = $conexao->query($sql) or die($conexao->error);

   $senhaDoBanco = false;

   if($conexao->affected_rows != 0){
    $vetorRegistro = $resultado->fetch_array();
    $senhaCriptografada = $vetorRegistro[0];
    $senhaDoBanco = password_verify($senha, $senhaCriptografada);
   }

   if($senhaDoBanco){
    session_start();
    $_SESSION["conectado"] = true;
    header("../php/homepage.php");
   }
   else{
    echo "<p> Informações de usuário incorretas. </p>";
   }
  }

 }