<?php
 class Administradores {
  public $idUsuario;
  public $ocupacao;
 
  function receberDadosForm($conexao)
   {
   $this->ocupacao = trim($conexao->escape_string($_POST["ocupacao"]));
   }

  function cadastrar($conexao, $nomeDaTabelaAdmin, $idUsuarioGerado)
   {
   $this->idUsuario = $idUsuarioGerado;

   $sql = "INSERT $nomeDaTabelaAdmin VALUES(
            '$this->idUsuario',
            '$this->ocupacao')";

   $conexao->query($sql) or die($conexao->error);
   } 
 }
?>